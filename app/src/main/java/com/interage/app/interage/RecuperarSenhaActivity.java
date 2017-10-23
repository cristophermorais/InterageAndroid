package com.interage.app.interage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.interage.app.utils.Utils;

public class RecuperarSenhaActivity extends AppCompatActivity {
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        email = (EditText) findViewById(R.id.emailRecup);

        if (Utils.validaEmail(email)) {
            postRecup();
        }
    }

    private void postRecup() {

    }
}
