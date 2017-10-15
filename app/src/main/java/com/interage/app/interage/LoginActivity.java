package com.interage.app.interage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioLgin;
    EditText senha;
    Button login;
    TextView recuperarSenha;
    TextView cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioLgin = (EditText) findViewById(R.id.usuarioLogin);
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

        recuperarSenha.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startRecuperarSenha();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startCadastrar();
            }
        });
    }

    private void doLogin() {
        boolean logged = true;
        if (logged) {
            postLogin();
        }
    }

    public void postLogin() {
        Intent nextActivity = new Intent(this, MainActivity.class);
        startActivity(nextActivity);
    }

    public void startRecuperarSenha(){
        Intent nextActivity = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(nextActivity);
    }

    public void startCadastrar(){
        Intent nextActivity = new Intent(this, CadastrarActivity.class);
        startActivity(nextActivity);
    }
}
