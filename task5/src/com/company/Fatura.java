package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Fatura {
    private ArrayList<ProduktIZgjedhur> produktet;

    private double totaliFillim;
    private double totaleRregull1;
    private double totaleRregull2;


    public Fatura(ArrayList<ProduktIZgjedhur> produktet) {
        this.produktet = produktet;
        setTotaliFillim();
    }

    public void setTotaliFillim() {
        for(int i=0;i<=produktet.size()-1;i++) {
            totaliFillim+=produktet.get(i).getCmimi()*produktet.get(i).getNr();
        }
    }

    public double getTotaliFillim() {
        return totaliFillim;
    }


    public void llogaritTotalin(int n1, int n2, int n3, int n4, Rules rregullat) {
        if (n1 == 1) llogaritUlje1(rregullat);
        if (n1 == 2) llogaritUlje2(rregullat);
        if (n1 == 3) llogaritUlje3(rregullat);
        if (n1 == 4) llogaritUlje4(rregullat);
        if (n2 == 1) llogaritUlje1(rregullat);
        if (n2 == 2) llogaritUlje2(rregullat);
        if (n2 == 3) llogaritUlje3(rregullat);
        if (n2 == 4) llogaritUlje4(rregullat);
        if (n3 == 1) llogaritUlje1(rregullat);
        if (n3 == 2) llogaritUlje2(rregullat);
        if (n3 == 3) llogaritUlje3(rregullat);
        if (n3 == 4) llogaritUlje4(rregullat);
        if (n4 == 1) llogaritUlje1(rregullat);
        if (n4 == 2) llogaritUlje2(rregullat);
        if (n4 == 3) llogaritUlje3(rregullat);
        if (n4 == 4) llogaritUlje4(rregullat);
    }


    public void llogaritUlje1( Rules rregullat) {
        Iterator hmIterator;
        double vleraPara=0;
        for (int i = 0; i <= produktet.size()-1; i++) {
            hmIterator = rregullat.getDiscountPerPrice().entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                double vlera = (double)mapElement.getKey();
                if(produktet.get(i).getCmimi()<vlera)
                    {  produktet.get(i).setUlja(vleraPara,1);
                        break;}
                else{
                    vleraPara=(double)mapElement.getValue();
                    continue;
                }
            }
            totaleRregull1+=produktet.get(i).getUlja()*produktet.get(i).getNr();
        }
        totaliFillim=totaliFillim-totaleRregull1;
        System.out.println(totaliFillim);
    }

    public void llogaritUlje2( Rules rregullat) {
        double vleraPara=0;
        Iterator hmIterator = rregullat.getPercentageDiscountPerPrice().entrySet().iterator();
        for (int i = 0; i <= produktet.size()-1; i++) {
            hmIterator = rregullat.getPercentageDiscountPerPrice().entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                double vlera = (double)mapElement.getKey();
                if(produktet.get(i).getCmimi()<vlera)
                    break;
                else{
                    vleraPara=(double)mapElement.getValue();
                    continue;
                }
            }
            produktet.get(i).setUlja(vleraPara,2);
            totaleRregull2+=produktet.get(i).getUlja()*produktet.get(i).getNr();
        }
        System.out.println(totaliFillim-totaleRregull2);
        totaliFillim=totaliFillim-totaleRregull2;
    }

    public void llogaritUlje3(Rules rregullat) {
        double vleraPara=0;
        Iterator hmIterator = rregullat.getDiscountPerBundle().entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            double vlera = (double)mapElement.getKey();
            if(totaliFillim<vlera)
                break;
            else{
                if((double)mapElement.getValue()>=vleraPara)
                vleraPara=(double)mapElement.getValue();
                continue;
            }
        }
            totaliFillim=totaliFillim-vleraPara;
        System.out.println(totaliFillim);
    }

    public void llogaritUlje4(Rules rregullat) {
        int nr=0;
        Iterator hmIterator=rregullat.getDiscountPerCount().entrySet().iterator();;
        double vleraPara=0;
        for (int i = 0; i <= produktet.size()-1; i++) {
            nr += produktet.get(i).getNr();
            }
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            double vlera = (double)mapElement.getKey();
            if(nr<vlera)
            { break;}
            else{
                if((double)mapElement.getValue()>=vleraPara)
                vleraPara=(double)mapElement.getValue();
                continue;
            }
        }
        totaliFillim=totaliFillim-vleraPara;
        System.out.println(totaliFillim);

    }
}
