package com.interage.app.services;

import com.interage.app.DTO.EmailDTO;
import com.interage.app.DTO.LoginDTO;
import com.interage.app.model.Token;
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

public class UsuarioService extends BaseAPI {
    private UsuarioInterface client;

    public UsuarioService() {
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

    public void login(LoginDTO loginDTO, Callback<Token> callback) {
        Call<Token> call = client.login(loginDTO);
        call.enqueue(callback);
    }

    public void recupSenha(EmailDTO emailDTO, Callback<Void> callback) {
        Call<Void> call = client.recuperaSenha(emailDTO);
        call.enqueue(callback);
    }
}
