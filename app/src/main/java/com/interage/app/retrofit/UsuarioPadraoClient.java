package com.interage.app.retrofit;

import com.interage.app.model.UsuarioPadrao;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexa on 10/21/2017.
 */

public class UsuarioPadraoClient {
    private String API_BASE_URL = "http://192.168.1.5:8080/";
    private UsuarioPadraoAPI client;

    public UsuarioPadraoClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        client = retrofit.create(UsuarioPadraoAPI.class);
    }

    public void listUsuarios() {
        Call<List<UsuarioPadrao>> call = client.listUsuarios();
        call.enqueue(callbackListUsuariosPadroes);
    }

    public void postUsuario(UsuarioPadrao usuarioPadrao) {
        Call<UsuarioPadrao> call = client.saveUser(usuarioPadrao);
        call.enqueue(callbackPostUsuarioPadrao);
    }

    Callback<List<UsuarioPadrao>> callbackListUsuariosPadroes = new Callback<List<UsuarioPadrao>>() {
        @Override
        public void onResponse(Call<List<UsuarioPadrao>> call, Response<List<UsuarioPadrao>> response) {
            if (response.isSuccessful()) {
                List<UsuarioPadrao> list = response.body();
                for (UsuarioPadrao usuario : list
                        ) {
                    System.out.println(usuario.toString());
                }
            } else {
                System.out.println(response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<List<UsuarioPadrao>> call, Throwable t) {
            System.out.println(t.getStackTrace().toString());
        }
    };

    Callback<UsuarioPadrao> callbackPostUsuarioPadrao = new Callback<UsuarioPadrao>() {
        @Override
        public void onResponse(Call<UsuarioPadrao> call, Response<UsuarioPadrao> response) {
            if (response.isSuccessful()) {
                UsuarioPadrao usuarioPadrao = response.body();
                System.out.println(usuarioPadrao.toString());

            } else {
                try {
                    System.out.println(response.message());
                    System.out.println(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<UsuarioPadrao> call, Throwable t) {
            System.out.println(t.getStackTrace().toString());
        }
    };
}
