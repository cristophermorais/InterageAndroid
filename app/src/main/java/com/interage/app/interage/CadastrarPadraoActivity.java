package com.interage.app.interage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.interage.app.model.UsuarioPadrao;
import com.interage.app.retrofit.UsuarioPadraoClient;
import com.interage.app.utils.MaskEditTextChangedListener;
import com.interage.app.utils.MaskUtils;
import com.interage.app.utils.Utils;

public class CadastrarPadraoActivity extends AppCompatActivity {

    Button btCadastrar;
    EditText nome;
    EditText cpf;
    EditText email;
    EditText senha;
    MaskEditTextChangedListener maskCPF;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_padrao);

        btCadastrar = (Button) findViewById(R.id.button_cadastrar_padrao);
        nome = (EditText) findViewById(R.id.nome_padrao);
        cpf = (EditText) findViewById(R.id.cpf_padrao);
        email = (EditText) findViewById(R.id.email_padrao);
        senha = (EditText) findViewById(R.id.senha_padrao);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCadastro();
            }
        });


        maskCPF = new MaskEditTextChangedListener("###.###.###-##", cpf);
        cpf.addTextChangedListener(maskCPF);
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
        UsuarioPadrao usuarioPadrao = new UsuarioPadrao();
        usuarioPadrao.setCPF(MaskUtils.unmaskCPF(cpf.getText().toString()));
        usuarioPadrao.setEmail(email.getText().toString().trim());
        usuarioPadrao.setSenha(senha.getText().toString());
        usuarioPadrao.setNome(nome.getText().toString());
        usuarioPadrao.setCodUsuario(count);

        UsuarioPadraoClient client = new UsuarioPadraoClient();
        client.postUsuario(usuarioPadrao);
        count++;
    }
}
