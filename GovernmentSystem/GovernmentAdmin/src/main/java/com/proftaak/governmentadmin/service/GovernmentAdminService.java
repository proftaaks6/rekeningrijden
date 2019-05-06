package com.proftaak.governmentadmin.service;

import com.proftaak.usersystem.shared.ClientUser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import sun.net.www.http.HttpClient;

@Stateless
public class GovernmentAdminService
{
    public ClientUser addUser(String name,
                              String address,
                              String residence,
                              String email) throws IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8083/deploy/v1/usersystem/userInfo");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("residence", residence));
        params.add(new BasicNameValuePair("address", address));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // return entity
            }
        }

        return null;
    }

    public List<ClientUser> getUsers() throws IOException
    {
        if (true) {
            return new ArrayList<ClientUser>(Arrays.asList(new ClientUser(1, "name", "address", "residence", new ArrayList<Integer>(), "email"),
                    new ClientUser(2, "name2", "address2", "residence2", new ArrayList<Integer>(), "email2")));
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8083/deploy/v1/usersystem/users");

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // TODO: return entities
            }
        }

        return null;
    }

    public boolean linkCar(int userId, int vehicleId) throws IOException
    {
        if (true) {
            return true;
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8083/deploy/v1/usersystem/linkcar");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("vehicleId", Integer.toString(vehicleId)));
        params.add(new BasicNameValuePair("userId", Integer.toString(userId)));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // return success
            }
        }

        return false;
    }
}
