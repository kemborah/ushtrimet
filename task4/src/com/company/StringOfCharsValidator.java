package com.company;

public class StringOfCharsValidator {

    public static boolean validate(String stringa,int length,boolean isLengthNum){
        if(!stringa.matches("\\S+")) return false;
        if(isLengthNum) {
            return stringa.length()<=length;
        }
        else return  stringa.length()==length;
    };
}
