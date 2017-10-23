package com.interage.app.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by alexa on 10/23/2017.
 */

public interface DiversosAPI {
    @POST("/api/recuperarSenha")
    Call<Void> recuperarSenha(String email);
}
