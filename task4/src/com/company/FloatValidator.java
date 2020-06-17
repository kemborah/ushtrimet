package com.company;

public class FloatValidator {
    public static boolean validate(String stringa){
        return stringa.matches("[0-9]*\\.?[0-9]+");
    }
}
