package com.interage.app.retrofit;

/**
 * Created by alexa on 10/24/2017.
 */

public abstract class Client {
    private String BaseURL = "http://192.168.1.101:8080/";

    public String getBaseURL() {
        return this.BaseURL;
    }
}
