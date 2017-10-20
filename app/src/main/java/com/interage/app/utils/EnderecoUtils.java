package com.interage.app.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by alexa on 10/16/2017.
 */


public class EnderecoUtils {

    private String app_key = "ahaKUOeSH5VNwEou2v98dP2mPwzUEGw5";
    private String app_secret = "kmO9e0tuSJxIIHsGcsydlXNXM3B7nGbkFEUONtd0p5ykDJau";
    private String baseUrl = "https://webmaniabr.com/api/1/cep";


    public void getEndereco(String CEP, Callback callback) {
        String url = String.format("%s/%s/?app_key=%s&app_secret=%s", baseUrl, CEP, app_key, app_secret);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
}