package com.proftaak.movementproxy.Messaging;

import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;

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

    @PostConstruct
    public void main() {
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        initQueue(QUEUE_NAME);
        initQueue(QUEUE_NAME2);
    }

    private void initQueue(String queueName){
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        Channel channel = null;
        try {

            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            startReceiving(channel);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void startReceiving(Channel channel){
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] received '" + message + "'");
            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
