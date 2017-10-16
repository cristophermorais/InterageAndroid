package com.interage.app.utils;

import java.util.HashSet;
import java.util.Set;

public class MaskUtils {
    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("[" + symbol + "]", "");

        return s;
    }

    public static String mask(String format, String text) {
        String maskedText = "";
        int i = 0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }

    public static String unmaskCPF(String cpf) {
        Set<String> replace = new HashSet<String>();
        replace.add(".");
        replace.add("-");

        return unmask(cpf, replace);
    }
}
