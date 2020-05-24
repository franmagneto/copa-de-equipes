package org.fneto.copadeequipes.util;

import java.text.Collator;
import java.util.Comparator;

// Adaptado de https://github.com/farbodsafaei/alphanumeric-comparator
public class ComparadorAlfanumerico implements Comparator<String> {

    private final Collator collator = Collator.getInstance();

    @Override
    public int compare(String s1, String s2) {
        int s1pos = 0, s2pos = 0;
        while (s1pos < s1.length() && s2pos < s2.length()) {
            int result = 0;
            String s1fatia = fatia(s1, s1pos);
            String s2fatia = fatia(s2, s2pos);

            s1pos += s1fatia.length();
            s2pos += s2fatia.length();

            if (Character.isDigit(s1fatia.charAt(0)) && Character.isDigit(s2fatia.charAt(0))) {
                result = Integer.parseInt(s1fatia) - Integer.parseInt(s2fatia);
            } else {
                result = collator.compare(s1fatia, s2fatia);
            }
            if (result != 0) {
                return result;
            }
        }
        return Integer.signum(s1.length() - s2.length());
    }

    private String fatia(String string, int index) {
        StringBuilder builder = new StringBuilder();
        if (Character.isDigit(string.charAt(index))) {
            while (index < string.length() && Character.isDigit(string.charAt(index))) {
                builder.append(string.charAt(index));
                index++;
            }
        } else {
            builder.append(string.charAt(index));
        }
        return builder.toString();
    }
}
