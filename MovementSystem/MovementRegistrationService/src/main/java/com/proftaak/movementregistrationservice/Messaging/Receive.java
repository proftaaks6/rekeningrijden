package com.proftaak.movementregistrationservice.Messaging;


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
    private final static String QUEUE_NAME = "MovementProxy_To_MovementRegistration";

    @PostConstruct
    public void main() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
