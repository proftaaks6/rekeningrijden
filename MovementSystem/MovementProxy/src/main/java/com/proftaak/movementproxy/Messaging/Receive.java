package com.proftaak.movementproxy.Messaging;

import com.google.gson.Gson;
import com.proftaak.movementregistrationservice.shared.MovementMessage;
import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;
import com.sun.media.jfxmedia.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Singleton
@Startup
public class Receive {
    private final static String QUEUE_NAME = "MovementRegistration_To_MovementProxy";
    private final static String QUEUE_NAME2 = "Simulation_To_MovementProxy";
    private Gson gson = new Gson();
    private Send send;


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
        try {

            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            if(simulation){
                startReceivingSimulation(channel, queueName);
            }else{
                startReceiving(channel, queueName);
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void startReceiving(Channel channel, String queueName){
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] received '" + message + "'");

                //Test code
                //Check if message is valid.
                if(validateMessage(new MovementMessage())){
                    //Send message to registration service.
                    send.sendMessage(message);
                }
            }
        };
        try {
            channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReceivingSimulation(Channel channel, String queueName){
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
                    //Todo: Send to other database to invalid data
                }
            }
        };
        try {
            channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
