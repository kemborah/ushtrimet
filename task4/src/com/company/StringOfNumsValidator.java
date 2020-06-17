package com.company;

public class StringOfNumsValidator {

    public static boolean validate(String stringa,int length,boolean isLengthVar){
        if(!stringa.matches("\\d+")) return false;
        if(isLengthVar) return stringa.length()<=length;
        else return stringa.length()==length;
    }
}
