package com.company;

public class Word {
    private String fjala;
    private int saHere;

    public Word(String fjala) {
        this.fjala = fjala;
        this.saHere=1;
    }

    public String getFjala() {
        return fjala;
    }

    @Override
    public String toString() {
        return fjala+", "+saHere;
    }

    public int getSaHere() {
        return saHere;
    }

    public void inrkemento(){
        saHere++;
    }
}
