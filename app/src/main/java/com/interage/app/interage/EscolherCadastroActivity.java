package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class EscolherCadastroActivity extends AppCompatActivity {

    CardView promotor;
    CardView usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_cadastro);

        promotor = (CardView) findViewById(R.id.cadastrar_promotor);
        usuario = (CardView) findViewById(R.id.cadastrar_usuario);

        usuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startCadastrar(1);
            }
        });

        promotor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startCadastrar(2);
            }
        });

    }

    public void startCadastrar(int tipo) {
        Intent intent = new Intent(this, CadastrarActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }
}
