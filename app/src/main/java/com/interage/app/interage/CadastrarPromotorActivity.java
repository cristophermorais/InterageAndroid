package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.interage.app.model.Endereco;

public class CadastrarPromotorActivity extends AppCompatActivity {

    CardView cardViewEndereco;
    Button btCadastrar;
    EditText nome;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_promotor);
        nome = (EditText) findViewById(R.id.nome_promotor);

        cardViewEndereco = (CardView) findViewById(R.id.card_view_endereco);
        btCadastrar = (Button) findViewById(R.id.button_cadastrar_promotor);

        cardViewEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCepActivity();
            }
        });


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCadastro();
            }
        });

    }

    private void checkCadastro() {
        sendCadastro();
    }

    private void sendCadastro() {

    }

    private void startCepActivity() {
        Intent intent = new Intent(this, CepActivity.class);
        startActivity(intent);
    }
}
