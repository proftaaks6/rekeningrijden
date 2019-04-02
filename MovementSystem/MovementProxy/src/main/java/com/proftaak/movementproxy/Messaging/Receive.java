package com.proftaak.movementproxy.Messaging;

import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Singleton
@Startup
public class Receive {
    private final static String QUEUE_NAME = "MovementRegistration_To_MovementProxy";

    @PostConstruct
    public void main() {
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        ConnectionFactory factory = new ConnectionFactory();
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
