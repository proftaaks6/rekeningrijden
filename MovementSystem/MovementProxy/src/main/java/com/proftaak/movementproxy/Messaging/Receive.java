package com.proftaak.movementproxy.Messaging;

import com.google.gson.Gson;
import com.proftaak.movementproxy.dao.ProxyDao;
import com.proftaak.movementproxy.dao.daoImplementation.ProxyDaoImplementation;
import com.proftaak.movementproxy.models.InvalidData;
import com.proftaak.movementregistrationservice.shared.JMSConsumer;
import com.proftaak.movementregistrationservice.shared.MovementMessage;
import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
@Startup
public class Receive {
    private final static String QUEUE_NAME = "MovementRegistration_To_MovementProxy";
    private final static String QUEUE_NAME2 = "Simulation_To_MovementProxy";
    private Gson gson = new Gson();
    private Send send;
    private JMSConsumer consumer;

    @Inject
    private ProxyDao dao;


    @PostConstruct
    public void main() {
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        this.send = new Send();
        initQueue(QUEUE_NAME, false);
        initQueue(QUEUE_NAME2, true);
    }

    private void initQueue(String queueName, boolean simulation){
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        Channel channel = null;
        consumer = new JMSConsumer();
        channel = consumer.getChannel();
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //Open queue
        if(simulation){
            try {
                connection = factory.newConnection();
                channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
            startReceivingSimulation(consumer, channel, queueName);
        }else{
            try {
                connection = factory.newConnection();
                channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
            startReceiving(consumer, channel, queueName);
        }
    }

    private void startReceiving(JMSConsumer jmsConsumer, Channel channel, String queueName){
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] received '" + message + "'");
                MovementMessage movementMessage =  gson.fromJson(message, MovementMessage.class);

                //Test code
                //Check if message is valid.
                if(validateMessage(movementMessage)){
                    //Not sending data back to registration.
                    //send.sendMessage(message);
                }else{
                    //Push invalid data to invalid data database.
                    dao.addInvalidData(new InvalidData(message));
                }
            }
        };
        //channel.basicConsume(queueName, true, consumer);
        jmsConsumer.startReceiving(consumer, queueName);
    }

    private void startReceivingSimulation(JMSConsumer jmsConsumer, Channel channel, String queueName){
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                MovementMessage movementMessage =  gson.fromJson(message, MovementMessage.class);
                System.out.println(movementMessage.toString());

                //Check if message is valid.
                if(validateMessage(movementMessage)){
                    //Send message to registration service.
                    send.sendMessage(message);
                }else{
                    //Push invalid data to invalid data database.
                    dao.addInvalidData(new InvalidData(message));
                }
            }
        };
        jmsConsumer.startReceiving(consumer, queueName);
    }

    private boolean validateMessage(MovementMessage movementMessage){

        try{
            if(movementMessage.getCoordinate() == null){
                System.out.println("No coordinates found in received location message from Tracker.");
                return false;
            }
            if(movementMessage.getTrackerId() < 0){
                System.out.println("No Tracker ID found in received location message from Tracker.");
                return false;
            }
        }catch (Exception e){
            System.out.println("Message validation failed.");
            return false;
        }

        return true;
    }
}
