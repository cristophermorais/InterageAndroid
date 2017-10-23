package com.interage.app.retrofit;


import com.interage.app.model.UsuarioPadrao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by alexa on 10/20/2017.
 */

public interface UsuarioPadraoAPI {

    @GET("/api/UsuariosPadroes")
    Call<List<UsuarioPadrao>> listUsuarios();

    @POST("/api/UsuariosPadroes")
    Call<UsuarioPadrao> saveUser(
            @Body UsuarioPadrao usuarioPadrao
    );
}