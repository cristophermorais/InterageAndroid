package com.interage.app.DTO;

import com.interage.app.model.Usuario;

/**
 * Created by alexa on 10/26/2017.
 */

public class LoginDTO {
    public String Email;
    public String Senha;

    public LoginDTO(Usuario usuario) {
        this.Email = usuario.getEmail();
        this.Senha = usuario.getSenha();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }
}
