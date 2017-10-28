package com.interage.app.interage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.interage.app.DTO.EmailDTO;
import com.interage.app.services.UsuarioService;
import com.interage.app.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarSenhaActivity extends AppCompatActivity {
    private EditText email;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        email = (EditText) findViewById(R.id.emailRecup);
        enviar = (Button) findViewById(R.id.sendEmail);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Utils.validaEmail(email.getText().toString())) {
                    email.setError("Email inválido");
                } else {
                    enviar.setEnabled(false);
                    postRecup();
                }
            }
        });


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void postRecup() {
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.recupSenha(new EmailDTO(email.getText().toString()), new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Nós te enviamos um e-mail.", Toast.LENGTH_LONG).show();
                    email.setText("");
                    enviar.setEnabled(true);
                } else {
                    if (response.code() == 404) {
                        Toast.makeText(getApplicationContext(), "E-mail não localizado", Toast.LENGTH_LONG).show();
                        enviar.setEnabled(true);
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na conexão com o servidor.", Toast.LENGTH_LONG).show();
                enviar.setEnabled(true);
            }
        });

    }
}
