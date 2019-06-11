package com.proftaak.movementregistrationservice.shared;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;

public class JMSConsumer {

    private Channel channel;

    static Logger log = Logger.getLogger(JMSConsumer.class.getName());


    public JMSConsumer() {
        Channel channel = null;
        ConnectionFactory factory = new ConnectionFactory();

        try {
            Connection connection = factory.newConnection();
            //Set up consumer
            channel = connection.createChannel();
            this.channel = channel;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void startReceiving(Consumer consumer, String queueName){
        log.log(Level.INFO, "Channel is not null?");
        log.log(Level.INFO,channel.toString());
        log.log(Level.INFO,"Consumer is not not null?");
        log.log(Level.INFO,consumer.toString());
        log.log(Level.INFO," [*] Waiting for messages.");
        try {

            this.channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            try {
                System.out.println("Retry, queue was not found");
                Thread.sleep(1000);
                startReceiving(consumer, queueName);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
