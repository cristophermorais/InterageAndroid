package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interage.app.DTO.LoginDTO;
import com.interage.app.model.Token;
import com.interage.app.model.Usuario;
import com.interage.app.services.UsuarioService;
import com.interage.app.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextSenha;
    Button buttonLogin;
    TextView recuperarSenha;
    TextView cadastrar;

    private boolean logged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = (EditText) findViewById(R.id.usuario);
        editTextSenha = (EditText) findViewById(R.id.senha);
        buttonLogin = (Button) findViewById(R.id.login);
        cadastrar = (TextView) findViewById(R.id.cadastrar);
        recuperarSenha = (TextView) findViewById(R.id.recuperarSenha);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
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

    private void checkLogin() {
        boolean isValid = true;
        String resposta = Utils.validaSenha(editTextSenha.getText().toString());

        if (!Utils.validaEmail(editTextEmail.getText().toString())) {
            editTextEmail.setError("Email inválido");
            isValid = false;
        } else if (resposta != null) {
            editTextSenha.setError(resposta);
            isValid = false;
        }

        if (isValid) {
            buttonLogin.setEnabled(false);
            postLogin();
        }
    }

    private void postLogin() {
        Usuario usuario = new Usuario();
        usuario.setEmail(editTextEmail.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());
        LoginDTO loginDTO = new LoginDTO(usuario);

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.login(loginDTO, new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();

                    if (response.code() == 200) {
                        buttonLogin.setEnabled(true);
                        startMain();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Usuário e/ou senha inválido", Toast.LENGTH_LONG).show();
                    buttonLogin.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na conexão com o servidor.", Toast.LENGTH_LONG).show();
                buttonLogin.setEnabled(true);
            }
        });


    }

    private void startMain() {
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
