package com.interage.app.model;

/**
 * Created by alexa on 10/21/2017.
 */

public class Usuario {
    private String Nome;
    private String Senha;
    private String Email;
    private String CPF;
    private Endereco endereco;

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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return Nome + " - " + Email + " - " + Senha + " - " + CPF;
    }
}
