package com.interage.app.interage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.interage.app.utils.MaskEditTextChangedListener;
import com.interage.app.utils.Utils;

public class CadastrarPadraoActivity extends AppCompatActivity {

    Button btCadastrar;
    EditText nome;
    EditText cpf;
    EditText email;
    EditText senha;
    EditText conf_senha;
    MaskEditTextChangedListener maskCPF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_padrao);

        btCadastrar = (Button) findViewById(R.id.button_cadastrar);
        nome = (EditText) findViewById(R.id.nome_padrao);
        cpf = (EditText) findViewById(R.id.cpf_padrao);
        email = (EditText) findViewById(R.id.email_padrao);
        senha = (EditText) findViewById(R.id.senha_padrao);
        conf_senha = (EditText) findViewById(R.id.conf_senha_padrao);


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
        } else if (!senha.getText().toString().equals(conf_senha.getText().toString())) {
            conf_senha.setError("Confirmação de senha diferente da senha");
            isValid = false;
        }

        if (isValid) {
            sendCadastro();
        }
    }

    private void sendCadastro() {
        Toast.makeText(getApplicationContext(), "Dados OK!", Toast.LENGTH_LONG).show();
    }
}
