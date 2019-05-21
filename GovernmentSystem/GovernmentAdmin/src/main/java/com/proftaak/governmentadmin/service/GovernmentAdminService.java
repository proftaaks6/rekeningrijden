package com.proftaak.governmentadmin.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proftaak.governmentadmin.dao.UserDao;
import com.proftaak.governmentadmin.models.GovernmentEmployee;
import com.proftaak.governmentadmin.utility.AuthenticationUtils;
import com.proftaak.usersystem.shared.ClientUser;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
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
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

@Stateless
public class GovernmentAdminService
{
    @Inject
    private UserDao userDao;


    private static final Gson gson = new Gson();

    public GovernmentEmployee validateUser(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userDao.validateUser(username, AuthenticationUtils.encodeSHA256(password));
    }

    public String registerUser(GovernmentEmployee user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));

                if (userDao.addUser(user)) {
                    return "User registered with username: " + user.getUsername();
                }

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "Failed to register user with username: " + user.getUsername();
    }


    public ClientUser addUser(String name,
                              String address,
                              String residence,
                              String email) throws Exception
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://user_system:8080/deploy/v1/usersystem/userInfo");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("residence", residence));
        params.add(new BasicNameValuePair("address", address));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception();
        }

        if (entity != null) {
            String json = EntityUtils.toString(entity);
            return gson.fromJson(json, ClientUser.class);
        }

        return null;
    }

    public List<ClientUser> getUsers() throws Exception
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://user_system:8080/deploy/v1/usersystem/users");

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception();
        }

        if (entity != null) {
            String json = EntityUtils.toString(entity);
            return gson.fromJson(json, new TypeToken<List<ClientUser>>(){}.getType());
        }

        return null;
    }

    public boolean linkCar(int userId, String chassisNumber) throws IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://user_system:8080/deploy/v1/usersystem/"+Integer.toString(userId)+"/car/"+chassisNumber);

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(httppost);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
