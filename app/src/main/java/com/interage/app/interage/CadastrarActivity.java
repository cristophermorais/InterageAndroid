package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class CadastrarActivity extends AppCompatActivity {

    CardView promotor;
    CardView padrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        promotor = (CardView) findViewById(R.id.cadastrar_promotor);
        padrao = (CardView) findViewById(R.id.cadastrar_padrao);

        promotor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startCadastrarPromotor();
            }
        });

        padrao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startCadastrarPadrao();
            }
        });
    }

    public void startCadastrarPromotor(){
        Intent nextActivity = new Intent(this, CadastrarPromotorActivity.class);
        startActivity(nextActivity);
    }

    public void startCadastrarPadrao(){
        Intent nextActivity = new Intent(this, CadastrarPadraoActivity.class);
        startActivity(nextActivity);
    }
}
