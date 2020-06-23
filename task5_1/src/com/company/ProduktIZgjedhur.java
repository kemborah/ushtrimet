package com.company;

public class ProduktIZgjedhur {
    private int id;
    private int nr;
    private double cmimi;
    private double ulja;
    private double totaliPasUljes=cmimi;

    public ProduktIZgjedhur(int id, int nr, double cmimi) {
        this.id = id;
        this.nr = nr;
        this.cmimi = cmimi;
    }

    public int getNr() {
        return nr;
    }

    public double getCmimi() {
        return cmimi;
    }

    public double getUlja() {
        return ulja;
    }



    public void setUlja(double ulja, int a) {
        this.ulja = ulja;
        setTotaliPasUljes(ulja, a);
    }

    public void setTotaliPasUljes(double ulja, int a) {
        if(a==1) this.totaliPasUljes = cmimi-ulja;
        if(a==2) this.totaliPasUljes = cmimi-(ulja/100)*cmimi;
    }
}
