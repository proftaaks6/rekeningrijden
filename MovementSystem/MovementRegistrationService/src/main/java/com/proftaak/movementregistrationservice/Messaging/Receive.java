package com.proftaak.movementregistrationservice.Messaging;


import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
