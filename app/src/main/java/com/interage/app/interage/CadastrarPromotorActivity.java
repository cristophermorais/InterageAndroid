package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.interage.app.model.Endereco;

public class CadastrarPromotorActivity extends AppCompatActivity {

    CardView cardViewEndereco;
    Button btCadastrar;
    EditText nome;
    TextView textViewEnd;
    TextView textViewCompl;
    TextView textViewCidUf;
    private Endereco endereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_promotor);
        nome = (EditText) findViewById(R.id.nome_promotor);

        cardViewEndereco = (CardView) findViewById(R.id.card_view_endereco);
        btCadastrar = (Button) findViewById(R.id.button_cadastrar_promotor);
        textViewEnd = (TextView) findViewById(R.id.textViewEnd);
        textViewCompl = (TextView) findViewById(R.id.textViewCompl);
        textViewCidUf = (TextView) findViewById(R.id.textViewCidUf);

        textViewEnd.setVisibility(View.GONE);
        textViewCompl.setVisibility(View.GONE);
        textViewCidUf.setVisibility(View.GONE);


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
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            endereco = (Endereco) data.getSerializableExtra("endereco");
            ((TextView) findViewById(R.id.textView)).setVisibility(View.GONE);


            textViewEnd.setVisibility(View.VISIBLE);
            textViewCidUf.setVisibility(View.VISIBLE);

            if (endereco.getComplemento() != null && !endereco.getComplemento().trim().isEmpty()) {
                textViewCompl.setVisibility(View.VISIBLE);
                textViewCompl.setText(endereco.getComplemento().isEmpty() ? "" : endereco.getComplemento());
            }

            textViewEnd.setText(endereco.getEndereco() + ", " + endereco.getNumero());
            textViewCidUf.setText(endereco.getCidade() + " - " + endereco.getUf());
        }
    }
}
