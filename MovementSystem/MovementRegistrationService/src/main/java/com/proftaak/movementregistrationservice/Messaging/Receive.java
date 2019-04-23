package com.proftaak.movementregistrationservice.Messaging;


import com.google.gson.Gson;
import com.proftaak.movementregistrationservice.Dao.DaoImplementation.RegistrationDaoImplementation;
import com.proftaak.movementregistrationservice.Dao.RegistrationDao;
import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.shared.JMSConsumer;
import com.proftaak.movementregistrationservice.shared.MovementMessage;
import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
@Startup
public class Receive {
    private final static String QUEUE_NAME = "MovementProxy_To_MovementRegistration";
    private Gson gson = new Gson();
    private JMSConsumer consumer;

    @Inject
    RegistrationDao dao;

    @PostConstruct
    public void main() {
        consumer = new JMSConsumer();
        Channel channel = consumer.getChannel();
        System.out.println("Channel received:" + channel);
        Consumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] received '" + message + "'");
                //Save in database.
                //Currently still suboptimal since the endpoint method requires a list of points and we are only receiving single onces at the moment.
                MovementMessage movementMessage = gson.fromJson(message, MovementMessage.class);
                List<LocationPoint> point = new ArrayList<>();
                point.add(new LocationPoint(movementMessage.getCoordinate().getLatitude(), movementMessage.getCoordinate().getLatitude(), new Date()));
                //Todo: Remove sout.
                System.out.println("ALERT, PUSHING DATA TO DATABASE.");
                dao.editTrackerLocationPoints(point, movementMessage.getTrackerId());
            }
        };
        consumer.startReceiving(defaultConsumer, QUEUE_NAME);

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = null;
//        Channel channel = null;
//        try {
//            connection = factory.newConnection();
//            channel = connection.createChannel();
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//            startReceiving(channel);
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//        }
    }

    private void startReceiving(Channel channel){
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
//                    throws IOException {
//                String message = new String(body, "UTF-8");
//                System.out.println(" [x] received '" + message + "'");
//                //Save in database.
//                //Currently still suboptimal since the endpoint method requires a list of points and we are only receiving single onces at the moment.
//                MovementMessage movementMessage = gson.fromJson(message, MovementMessage.class);
//                List<LocationPoint> point = new ArrayList<>();
//                point.add(new LocationPoint(movementMessage.getCoordinate().getLatitude(), movementMessage.getCoordinate().getLatitude(), new Date()));
//                dao.editTrackerLocationPoints(point, movementMessage.getTrackerId());
//            }
//        };
//        try {
//            channel.basicConsume(QUEUE_NAME, true, consumer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
