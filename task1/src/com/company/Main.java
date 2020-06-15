
//1. Create a console program, where the user enters the coordinates of several points. Each time after a point is entered,
//print the list of the points previously entered, ordered by the distance from this point.
package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int x, y;
        ArrayList<Point> pikat = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n Vendos piken:(x,y) \n");
            x = scanner.nextInt();
            y = scanner.nextInt();
            Point pika=new Point(x, y);
            pikat.add(pika);  // i shton piken vektorit
            for (int i = 0; i < pikat.size(); i++){
                pikat.get(i).setDistance(pika.distanceTo(pikat.get(i)));
            }

            Collections.sort(pikat, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Double.compare(o1.distance(), o2.distance());
                }
            });

            for (int i = 0; i < pikat.size(); i++)
                System.out.print("("+ pikat.get(i).x() + " , "+ pikat.get(i).y()+"),");
        } while (true);


    }
}
