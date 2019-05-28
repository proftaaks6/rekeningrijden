package com.proftaak.movementproxy.Messaging;

import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class Send {
    private final static String QUEUE_NAME = "MovementProxy_To_MovementRegistration";
    private Connection connection;
    private Channel channel;

    public Send() {
        try {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        try {
            this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            this.channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent message: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}