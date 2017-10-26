package com.interage.app.services;

/**
 * Created by alexa on 10/24/2017.
 */

abstract class BaseAPI {
    private final String BaseURL = "http://interage.ddns.net:8080/";

    String getBaseURL() {
        return this.BaseURL;
    }
}
