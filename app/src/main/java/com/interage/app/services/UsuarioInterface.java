package com.interage.app.services;


import com.interage.app.DTO.EmailDTO;
import com.interage.app.DTO.LoginDTO;
import com.interage.app.model.Token;
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

    @POST("/api/Interage/Autentica")
    Call<Token> login(
            @Body LoginDTO loginDTO
    );

    @POST("/api/Interage/Recupera")
    Call<Void> recuperaSenha(
            @Body EmailDTO emailDTO
    );
}