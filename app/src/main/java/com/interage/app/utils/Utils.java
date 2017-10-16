package com.interage.app.utils;

import android.widget.EditText;


public class Utils {
    public static boolean validaEmail(EditText editTextEmail) {
        String email = editTextEmail.getText().toString();

        if (email == null) {
            return false;
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            } else {
                editTextEmail.setError("Email inválido");
                return false;
            }
        }
    }

    public static boolean validaSenha(EditText editTextSenha) {
        String senha = editTextSenha.getText().toString();
        if (senha.contains(" ")) {
            editTextSenha.setError("A senha não deve conter espaços em branco");
            return false;
        } else if (senha.length() < 8) {
            editTextSenha.setError("A senha é muito curta");
            return false;
        } else {
            boolean numero = false, minusculo = false, maiusculo = false;

            for (char c : senha.toCharArray()) {
                if (Character.isDigit(c)) {
                    numero = true;
                } else if (Character.isLowerCase(c)) {
                    minusculo = true;
                } else if (Character.isUpperCase(c)) {
                    maiusculo = true;
                }
            }

            if (!numero || !minusculo || !maiusculo) {
                editTextSenha.setError("A senha deve conter:\n\t-Numeros\n\t-Letras maiúsculas\n\t-Letras minúsculas");
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean validaNome(EditText editTextNome) {
        String s_nome = editTextNome.getText().toString().trim();

        if (s_nome.isEmpty()) {
            editTextNome.setError("Campo vazio");
            return false;
        } else if (!s_nome.contains(" ")) {
            editTextNome.setError("Informe nome completo");
            return false;
        } else if (s_nome.length() > 300) {
            editTextNome.setError("Nome muito comprido");
            return false;
        } else {
            return true;
        }
    }

    public static boolean validaCPF(EditText editTextCPF) {
        String cpf = MaskUtils.unmaskCPF(editTextCPF.getText().toString());

        if (cpf.length() < 11 || !Utils.isValidCPF(cpf)) {
            editTextCPF.setError("CPF inválido");
            return false;
        } else {
            return true;
        }
    }

    private static boolean isValidCPF(String str) {
        String aux = "";
        int[] vec = new int[11];
        boolean b1, b2;

        str = CPFtoStringSemMascara(str);


        for (int i = 0; i < vec.length; i++) {
            aux = String.valueOf(str.charAt(i));
            vec[i] = Integer.parseInt(aux);
        }


        b1 = calcula1st(vec);
        b2 = calcula2nd(vec);

        return b1 && b2;
    }

    private static boolean calcula1st(int[] vec) {
        int soma = 0, n = 10, dig;

        for (int i = 0; i < 9; i++)
            soma += vec[i] * n--;

        dig = 11 - (soma % 11);

        if (dig > 9)
            dig = 0;

        return dig == vec[9];
    }

    private static boolean calcula2nd(int[] vec) {
        int soma = 0, n = 11, dig;

        for (int i = 0; i < 10; i++)
            soma += vec[i] * n--;

        dig = 11 - (soma % 11);

        if (dig > 9)
            dig = 0;

        return dig == vec[10];
    }

    private static String CPFtoStringSemMascara(String s) {


        String teste = "";
        int i = 0;
        long unmasked;


        while (i < s.length()) {
            if (s.charAt(i) == '-' || s.charAt(i) == ('.')) {
                i++;
            } else {
                teste += s.charAt(i);
                i++;
            }
        }


        return teste;

    }
}
