package com.interage.app.model;

/**
 * Created by alexa on 10/21/2017.
 */

public class UsuarioPadrao extends Usuario {

    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + CPF;
    }
}
