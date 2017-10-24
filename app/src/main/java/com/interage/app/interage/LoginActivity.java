package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.interage.app.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    EditText usuario;
    EditText senha;
    Button login;
    TextView recuperarSenha;
    TextView cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        login = (Button) findViewById(R.id.login);
        cadastrar = (TextView) findViewById(R.id.cadastrar);
        recuperarSenha = (TextView) findViewById(R.id.recuperarSenha);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        recuperarSenha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startRecuperarSenha();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startCadastrar();
            }
        });
    }

    private void doLogin() {
        boolean logged = true;

        if (!Utils.validaEmail(usuario))
            logged = false;

        if (!Utils.validaSenha(senha))
            logged = false;

        if (logged) {
            postLogin();
        }
    }

    private void postLogin() {
        Intent nextActivity = new Intent(this, MainActivity.class);
        startActivity(nextActivity);
    }

    private void startRecuperarSenha() {
        Intent nextActivity = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(nextActivity);
    }

    private void startCadastrar() {
        Intent nextActivity = new Intent(this, EscolherCadastroActivity.class);
        startActivity(nextActivity);
    }
}
