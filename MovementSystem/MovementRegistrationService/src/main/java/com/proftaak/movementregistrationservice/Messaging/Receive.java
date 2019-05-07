package com.proftaak.movementregistrationservice.Messaging;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.proftaak.movementregistrationservice.Dao.DaoImplementation.RegistrationDaoImplementation;
import com.proftaak.movementregistrationservice.Dao.RegistrationDao;
import com.proftaak.movementregistrationservice.config.Config;
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
    private Send send = new Send();

    @Inject
    private RegistrationDao dao;

    @PostConstruct
    public void main() {
        JMSConsumer consumer = new JMSConsumer();
        Channel channel = consumer.getChannel();

        System.out.println("Channel received: " + channel);

        //Currently still suboptimal since the endpoint method requires a list of points and we are only receiving single onces at the moment.
        Consumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // String message = new String(body, "UTF-8");
                // System.out.println(" [x] received '" + message + "'");

                try{
                    // Get location from received message
                    MovementMessage movementMessage = gson.fromJson(new String(body, "UTF-8"), MovementMessage.class);

                    // Get last location from user
                    LocationPoint lastLocation = dao.getTrackedById(movementMessage.getTrackerId()).getMostRecentLocationPoint();

                    if (lastLocation != null) {
                        // Calculate distance from two points using some super duper complex math
                        double distance = calculateDistance(
                                lastLocation.getLongitude(),
                                lastLocation.getLatitude(),
                                movementMessage.getCoordinate().getLongitude(),
                                movementMessage.getCoordinate().getLatitude());

                        // If distance is a negative number, convert to positive so it can be compared with value in config file
//                        if (distance < 0) {
//                            distance = Math.abs(-1);
//                        }

                        // If the new received position exceeds this maximum allowed distance, then the MovementMessage should be send back as 'invalid'
                        if (distance > Double.valueOf(new Config().getProperty("maxMovementMessageDistance"))) {
                            // Maximum allowed distance exceeded! Send message back to proxy
                            send.sendMessage(new String(body, "UTF-8"));
                        } else {
                            // Save location to db
                            dao.addTrackerLocationPiont(
                                    new LocationPoint(
                                            movementMessage.getCoordinate().getLongitude(),
                                            movementMessage.getCoordinate().getLatitude(),
                                            new Date()), movementMessage.getTrackerId());
                        }
                    } else {
                        dao.addTrackerLocationPiont(
                                new LocationPoint(
                                        movementMessage.getCoordinate().getLongitude(),
                                        movementMessage.getCoordinate().getLatitude(),
                                        new Date()), movementMessage.getTrackerId());
                    }
                } catch (JsonSyntaxException e){
                    System.out.println(e);
                }
            }
        };

        consumer.startReceiving(defaultConsumer, QUEUE_NAME);
    }

    private double calculateDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        return Math.sqrt(Math.pow(longitude2 - longitude1, 2) + Math.pow(latitude2 - latitude1, 2));
    }
}
