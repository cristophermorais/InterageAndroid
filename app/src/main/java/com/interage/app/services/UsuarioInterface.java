package com.interage.app.services;


import com.interage.app.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by alexa on 10/20/2017.
 */

interface UsuarioInterface {

    @GET("/api/Usuario")
    Call<List<Usuario>> listUsuarios();

    @POST("/api/UsuariosPadroes")
    Call<Usuario> saveUser(
            @Body Usuario usuario
    );
}