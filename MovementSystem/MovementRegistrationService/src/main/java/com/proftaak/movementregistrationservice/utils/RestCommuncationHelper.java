package com.proftaak.movementregistrationservice.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

public class RestCommuncationHelper {

    private static Logger LOG = Logger.getLogger(RestCommuncationHelper.class.getName());


    public static String getResponseString(String url, String requestMethode) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(requestMethode);
        con.setRequestProperty("User-Agent", USER_AGENT);
        return getResponseFromConnection(con);
    }

    public static String postRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(data);
//        wr.flush();
//        wr.close();

        return getResponseFromConnection(con);
    }

    private static String getResponseFromConnection(HttpURLConnection con) throws IOException {
        return ResponseHelper.getResponseFromConnection(con);
    }


}
