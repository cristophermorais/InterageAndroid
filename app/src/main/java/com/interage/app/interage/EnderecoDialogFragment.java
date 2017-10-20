package com.interage.app.interage;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.interage.app.model.Endereco;

/**
 * Created by alexa on 10/16/2017.
 */

public class EnderecoDialogFragment extends DialogFragment {

    public interface EnderecoDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private EnderecoDialogListener mListener;
    private Endereco endereco;
    private View view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EnderecoDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Create the AlertDialog object and return it
        view = inflater.inflate(R.layout.dialog_endereco, null);
        ((TextView) view.findViewById(R.id.textViewRua)).setText(endereco.getEndereco());
        ((TextView) view.findViewById(R.id.textViewBairroCidade)).setText(endereco.getBairro() + ", " + endereco.getCidade());

        builder.setView(view).setTitle("Confirmar numeração")
                // Add action buttons
                .setPositiveButton("Confimar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(EnderecoDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(EnderecoDialogFragment.this);
                        EnderecoDialogFragment.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }

    public int getNumero() {
        String aux = ((EditText) view.findViewById(R.id.editTextNumero)).getText().toString();
        return Integer.parseInt(aux);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}