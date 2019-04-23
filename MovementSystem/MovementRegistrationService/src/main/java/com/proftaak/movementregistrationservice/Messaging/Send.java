package com.proftaak.movementregistrationservice.Messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.movementregistrationservice.shared.Coordinate;
import com.proftaak.movementregistrationservice.shared.MovementMessage;
import com.proftaak.rabbitmq.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.log4j.BasicConfigurator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Singleton
@Startup
public class Send {
    private final static String QUEUE_NAME = "MovementRegistration_To_MovementProxy";

    @PostConstruct
    public void main() {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //Todo: Test code, can be removed once validated
            MovementMessage movementMessage = new MovementMessage(-1, new Coordinate(1, 2));
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(movementMessage);

            //Todo: Remove
            for(int i = 0; i < 1000; i++){
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }

            System.out.println(message);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
