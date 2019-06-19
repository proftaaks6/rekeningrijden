package com.proftaak.resthelpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseHelper {

    private ResponseHelper(){}

    static Logger log = Logger.getLogger(ResponseHelper.class.getName());
    public static String getResponseFromConnection(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();

        log.log(Level.INFO,"Made response to : %s" , con.getURL());
        log.log(Level.INFO,"Made response with : %s" , con.getRequestMethod());
        log.log(Level.INFO,"Response code : %s" , responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String result = response.toString();

            log.log(Level.INFO,"Response string : %s" , result);
            return result;
        } else {
            return null;
        }
    }
}
