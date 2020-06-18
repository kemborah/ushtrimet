package com.company;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        ArrayList<ProduktIZgjedhur> produktet = new ArrayList<>();
        produktet.add(new ProduktIZgjedhur(1,1,100));
        produktet.add(new ProduktIZgjedhur(2,1,200));
        produktet.add(new ProduktIZgjedhur(3,1,800));

        int n1,n2,n3,n4;
        Scanner in = new Scanner(new FileReader("./input.txt"));

        Rules rules = new Rules(in.nextLine(),in.nextLine(),in.nextLine(),in.nextLine());
        n1=rules.getRulesOrder().get(1);
        n2=rules.getRulesOrder().get(2);
        n3=rules.getRulesOrder().get(3);
        n4=rules.getRulesOrder().get(4);
        Fatura fatura = new Fatura(produktet);
        System.out.println(fatura.getTotaliFillim());

        fatura.llogaritTotalin(n1,n2,n3,n4,rules);
    }
}
