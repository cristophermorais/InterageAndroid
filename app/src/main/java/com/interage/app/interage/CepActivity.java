package com.interage.app.interage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.interage.app.model.Endereco;
import com.interage.app.utils.EnderecoUtils;
import com.interage.app.utils.MaskEditTextChangedListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CepActivity extends AppCompatActivity implements EnderecoDialogFragment.EnderecoDialogListener {

    private Endereco endereco;
    Button btCep;
    EditText editTextCep;


    MaskEditTextChangedListener maskCEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);

        btCep = (Button) findViewById(R.id.bt_cep);


        btCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCep();
            }
        });

        editTextCep = (EditText) findViewById(R.id.edit_text_cep);
        maskCEP = new MaskEditTextChangedListener("#####-###", editTextCep);
        editTextCep.addTextChangedListener(maskCEP);

    }

    private void checkCep() {
        String cep = editTextCep.getText().toString();

        if (cep.length() < 9) {
            editTextCep.setError("CEP inválido");
        } else {
            btCep.setEnabled(false);
            buscaEndereco(cep);
        }

    }

    private void buscaEndereco(String cep) {
        new EnderecoUtils().getEndereco(cep, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String json = response.body().string();
                endereco = new Gson().fromJson(json, Endereco.class);

                if (endereco.getCidade().equals("") || endereco.getUf().equals("") ||
                        endereco.getBairro().equals("") || endereco.getEndereco().equals("")) {

                    CepActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editTextCep.setError("CEP inválido");
                            btCep.setEnabled(true);
                        }
                    });
                } else {
                    showEnderecoDialog(endereco);
                }
            }
        });
    }


    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    private void showEnderecoDialog(Endereco endereco) {
        // Create an instance of the dialog fragment and show it
        EnderecoDialogFragment dialog = new EnderecoDialogFragment();

        dialog.setEndereco(endereco);
        dialog.show(getSupportFragmentManager(), "EnderecoDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        String s_numero = ((EnderecoDialogFragment) dialog).getNumero();
        int i_numero;
        try {
            i_numero = Integer.parseInt(s_numero);
            endereco.setNumero(i_numero);
            startComplementoActivity();
        } catch (NumberFormatException exception) {
            Toast.makeText(getApplicationContext(), "Informe o número", Toast.LENGTH_LONG).show();
            btCep.setEnabled(true);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        btCep.setEnabled(true);
        Toast.makeText(getApplicationContext(), "Cancel!", Toast.LENGTH_LONG).show();
    }

    public void startComplementoActivity() {
        Intent intent = new Intent(this, ComplementoActivity.class);
        intent.putExtra("endereco", endereco);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        endereco = (Endereco) data.getSerializableExtra("endereco");
        Intent intent = new Intent();
        intent.putExtra("endereco", endereco);
        setResult(0, intent);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(1, intent);
        super.onBackPressed();
    }
}
