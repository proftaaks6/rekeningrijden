package com.proftaak.rabbitmq;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.AddressResolver;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ConnectionFactory extends com.rabbitmq.client.ConnectionFactory {

    public ConnectionFactory(){
        super();
        setUsername("guest");
        setPassword("guest");
        setVirtualHost("/");
    }
    @Override
    public Connection newConnection() throws IOException, TimeoutException {

        Address address1;

        if(System.getenv("environment") != null && System.getenv("environment").equals("production")) {
            address1 = new Address("rabbitmq", 5672);

        } else {
            address1 = new Address("localhost", 5682);
        }
        List<Address> list = new ArrayList<>();
        list.add(address1);

        AddressResolver addressResolver = createAddressResolver(list);
        return newConnection(addressResolver);
    }

}
