package com.proftaak.movementregistrationservice.shared;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;

public class JMSConsumer {
    private String QUEUE_NAME = "";
    private Channel channel;

    public JMSConsumer() {
        Channel channel = null;
        Connection connection = null;
        try {
            //Set up consumer
            ConnectionFactory factory = new ConnectionFactory();

            connection = factory.newConnection();
            channel = connection.createChannel();
            //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            this.channel = channel;
        }catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                connection.close();
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public void startReceiving(Consumer consumer, String queueName){
        this.QUEUE_NAME = queueName;
        System.out.println("Channel is not null?");
        System.out.println(channel);
        System.out.println("Consumer is not not null?");
        System.out.println(consumer);
        System.out.println(" [*] Waiting for messages.");
        try {
            this.channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            try {
                System.out.println("Retry, queue was not found");
                Thread.sleep(1000);
                startReceiving(consumer, queueName);
            } catch (InterruptedException ignore) {

            }
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
