package com.company;

public class EmailValidator extends StringOfCharsValidator{
    private static final String regex = "^(.+)@(.+)$";

    public static boolean validate(String stringa) {
        return (stringa.matches(regex)&&stringa.length()<=50);
    }
}
