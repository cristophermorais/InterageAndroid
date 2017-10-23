package com.interage.app.model;

/**
 * Created by alexa on 10/21/2017.
 */

public class Usuario {
    private String Nome;
    private String Senha;
    private String Email;
    private int CodUsuario;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        CodUsuario = codUsuario;
    }

    @Override
    public String toString() {
        return CodUsuario + " - " + Nome + " - " + Email + " - " + Senha;
    }
}
