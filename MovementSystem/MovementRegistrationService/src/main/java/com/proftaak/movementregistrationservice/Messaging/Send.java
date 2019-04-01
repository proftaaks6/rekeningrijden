package com.proftaak.movementregistrationservice.Messaging;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.log4j.BasicConfigurator;

public class Send {
    private final static String QUEUE_NAME = "MovementRegistration_To_MovementProxy";
    public static void main(String[] argv) throws Exception{
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Message queue initialized between MovementRegistration and MovementProxy.";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(message);
        }
    }
}
