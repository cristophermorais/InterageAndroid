package com.interage.app.retrofit;

import com.interage.app.model.Usuario;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexa on 10/21/2017.
 */

public class UsuarioClient extends Client {
    private UsuarioInterface client;

    public UsuarioClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(this.getBaseURL())
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        client = retrofit.create(UsuarioInterface.class);
    }

    public void listUsuarios(Callback<List<Usuario>> callback) {
        Call<List<Usuario>> call = client.listUsuarios();
        call.enqueue(callback);
    }

    public void cadastrarUsuario(Usuario usuarioPadrao, Callback<Usuario> callback) {
        Call<Usuario> call = client.saveUser(usuarioPadrao);
        call.enqueue(callback);
    }
}
