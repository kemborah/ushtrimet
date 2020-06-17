package com.company;

public class ExpDateValidator extends StringOfNumsValidator{


    public static boolean validate(String stringa) {
        if (!stringa.matches(("\\d{4}"))) return false;
        int month = Integer.parseInt(stringa.substring(0,1));
        if (month<1&&month>12) return false;
        int year = Integer.parseInt(stringa.substring(2,4));
        if (year<2020) return false;
        else  return true;
    }
}
