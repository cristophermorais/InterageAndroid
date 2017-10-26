package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.interage.app.model.Endereco;
import com.interage.app.model.Usuario;
import com.interage.app.services.UsuarioService;
import com.interage.app.utils.MaskEditTextChangedListener;
import com.interage.app.utils.MaskUtils;
import com.interage.app.utils.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarActivity extends AppCompatActivity {

    private Endereco endereco;

    CardView cardViewEndereco;
    TextView textViewTitulo;
    TextView textViewEnd;
    TextView textViewCompl;
    TextView textViewCidUf;
    Button btCadastrar;
    EditText nome;
    EditText cpf;
    EditText email;
    EditText senha;
    MaskEditTextChangedListener maskCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        int tipo = getIntent().getIntExtra("tipo", -1);


        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        cardViewEndereco = (CardView) findViewById(R.id.card_view_endereco);
        btCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        textViewEnd = (TextView) findViewById(R.id.textViewEnd);
        textViewCompl = (TextView) findViewById(R.id.textViewCompl);
        textViewCidUf = (TextView) findViewById(R.id.textViewCidUf);
        nome = (EditText) findViewById(R.id.nome);
        cpf = (EditText) findViewById(R.id.cpf);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);

        textViewEnd.setVisibility(View.GONE);
        textViewCompl.setVisibility(View.GONE);
        textViewCidUf.setVisibility(View.GONE);

        maskCPF = new MaskEditTextChangedListener("###.###.###-##", cpf);
        cpf.addTextChangedListener(maskCPF);


        if (tipo == 1) {
            textViewTitulo.setText("Cadastro de Usuário");
        } else if (tipo == 2) {
            textViewTitulo.setText("Cadastro de Promotor");
        }

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCadastro();
            }
        });

        cardViewEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCepActivity();
            }
        });
    }

    private void checkCadastro() {
        boolean isValid = true;

        if (!Utils.validaNome(nome))
            isValid = false;

        if (!Utils.validaCPF(cpf))
            isValid = false;

        if (!Utils.validaEmail(email))
            isValid = false;

        if (!Utils.validaSenha(senha)) {
            isValid = false;
        }

        if (isValid) {
            sendCadastro();
        }
    }

    private void sendCadastro() {
        Usuario usuarioPadrao = new Usuario();
        usuarioPadrao.setCPF(MaskUtils.unmaskCPF(cpf.getText().toString()));
        usuarioPadrao.setEmail(email.getText().toString().trim());
        usuarioPadrao.setSenha(senha.getText().toString());
        usuarioPadrao.setNome(nome.getText().toString());

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.cadastrarUsuario(usuarioPadrao, new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();
                    System.out.println(usuario.toString());
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

                } else {
                    try {
                        System.out.println(response.message());
                        System.out.println(response.errorBody().string());
                        if (response.code() == 409) {

                            Toast.makeText(getApplicationContext(), "Já existe usuario cadastrado com o CPF/E-mail informado.", Toast.LENGTH_LONG).show();

                        }
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Erro inesperado.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro inesperado..", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na conexão com o servidor.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startCepActivity() {
        Intent intent = new Intent(this, CepActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            endereco = (Endereco) data.getSerializableExtra("endereco");
            ((TextView) findViewById(R.id.textViewSelecionar)).setVisibility(View.GONE);


            textViewEnd.setVisibility(View.VISIBLE);
            textViewCidUf.setVisibility(View.VISIBLE);

            if (endereco.getComplemento() != null && !endereco.getComplemento().trim().isEmpty()) {
                textViewCompl.setVisibility(View.VISIBLE);
                textViewCompl.setText(endereco.getComplemento().isEmpty() ? "" : endereco.getComplemento());
            } else {
                textViewCompl.setVisibility(View.GONE);
            }

            textViewEnd.setText(endereco.getEndereco() + ", " + endereco.getNumero());
            textViewCidUf.setText(endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getUf());

        }
    }
}
