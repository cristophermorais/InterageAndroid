package com.interage.app.utils;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Utils {
    public static boolean validaEmail(String email) {
        if (email == null) {
            return false;
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    //Retorna NULL se a senha for valida, ou uma mensagem de erro caso nao seja
    public static String validaSenha(String senha) {
        if (senha.contains(" ")) {
            return ("A senha não deve conter espaços em branco");
        } else if (senha.length() < 8) {
            return ("A senha é muito curta");
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
                return ("A senha deve conter pelo menos:\n\t1 número\n\t1 letra maiúscula\n\t1 letra minúscula");
            } else {
                return null;
            }
        }
    }

    //Retorna NULL se o nome for valido, ou uma mensagem de erro caso nao seja
    public static String validaNome(String s_nome) {
        if (s_nome.isEmpty()) {
            return ("Campo vazio");
        } else if (!s_nome.contains(" ")) {
            return ("Informe nome completo");
        } else if (s_nome.length() > 300) {
            return ("Nome muito longo");
        } else {
            return null;
        }
    }

    public static boolean validaCPF(String cpf) {
        cpf = MaskUtils.unmaskCPF(cpf);

        if (cpf.length() < 11 || !Utils.isValidCPF(cpf)) {
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

    public static void enableButton(Button button, AppCompatActivity activity) {
        button.setEnabled(true);
//        button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.textEnabledButton));
//        button.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.backgroundEnabledButton));
    }

    public static void disableButton(Button button, AppCompatActivity activity) {
        button.setEnabled(false);
//        button.setTextColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.textDisabledButton));
//        button.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.backgroundDisabledButton));
    }

}
