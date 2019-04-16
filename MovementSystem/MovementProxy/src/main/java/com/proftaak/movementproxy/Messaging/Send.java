package com.proftaak.movementproxy.Messaging;

import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.log4j.BasicConfigurator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

@Singleton
@Startup
public class Send {
    private final static String QUEUE_NAME = "MovementProxy_To_MovementRegistration";

    @PostConstruct
    public void main() {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Message queue initialized between MovementProxy and MovementRegistration.";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(message);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}