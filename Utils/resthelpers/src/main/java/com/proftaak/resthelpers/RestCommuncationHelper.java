package com.proftaak.resthelpers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

public class RestCommuncationHelper {

    private RestCommuncationHelper(){}

    public static String getResponseString(String url, String requestMethode) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(requestMethode);
        con.setRequestProperty("User-Agent", USER_AGENT);
        return getResponseFromConnection(con);
    }

    public static String postRequest(String url, String data) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        return getResponseFromConnection(con);
    }

    private static String getResponseFromConnection(HttpURLConnection con) throws IOException {
        return ResponseHelper.getResponseFromConnection(con);
    }


}
