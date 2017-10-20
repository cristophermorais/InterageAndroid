package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.interage.app.model.Endereco;

public class ComplementoActivity extends AppCompatActivity {

    private Endereco endereco;
    private TextView textViewCompl1;
    private TextView textViewCompl2;
    private Button button;
    private CheckBox checkBox;
    private EditText editTextCompl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complemento);

        textViewCompl1 = (TextView) findViewById(R.id.textViewCompl1);
        textViewCompl2 = (TextView) findViewById(R.id.textViewCompl2);
        button = (Button) findViewById(R.id.bt_compl);
        checkBox = (CheckBox) findViewById(R.id.checkBox_compl);
        editTextCompl = (EditText) findViewById(R.id.editTextCompl);

        endereco = (Endereco) getIntent().getSerializableExtra("endereco");

        textViewCompl1.setText(endereco.getEndereco() + ", " + endereco.getNumero());
        textViewCompl2.setText(endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getUf());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox.isChecked()) {
                    endereco.setComplemento(editTextCompl.getText().toString());
                }
                backToCep();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    button.setEnabled(true);
                    editTextCompl.setEnabled(false);
                } else {
                    editTextCompl.setEnabled(true);
                    String aux = editTextCompl.getText().toString().trim();
                    if (aux.length() < 1 || aux.isEmpty()) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }
            }
        });

        editTextCompl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String aux = charSequence.toString().trim();

                if (aux.length() < 1 || aux.isEmpty()) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void backToCep() {
        Intent intent = new Intent();
        intent.putExtra("endereco", endereco);
        setResult(0, intent);
        super.finish();
    }
}
