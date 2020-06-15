package com.company;

//2.Create a console program where the user enters the properties needed to define different Shapes, like:
//	a. com.company.Circle (radius)
//	b. com.company.Rectangle (base, height)
//	c. com.company.Triangle (base, height)
//
//In the end, print all the shapes ordered by their Area.


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Shape> shapes = new ArrayList<>();
        int i = 0;
        int radius,base,height;
        int forma;
        do {
            System.out.println("\n Cfare forme? \n");
            forma=scanner.nextInt();
            switch (forma) {
                case 1:
                    System.out.println("Vendos rrezen");
                    radius=scanner.nextInt();
                    Circle circle = new Circle(radius);
                    shapes.add(circle);
                    break;
                case 2:
                    System.out.println("Vendos bazen, lartesine");
                    base=scanner.nextInt();
                    height=scanner.nextInt();
                    Rectangle rectangle = new Rectangle(base,height);
                    shapes.add(rectangle);
                    break;
                case 3:
                    System.out.println("Vendos bazen, lartesine");
                    base=scanner.nextInt();
                    height=scanner.nextInt();
                    Triangle triangle = new Triangle(base,height);
                    shapes.add(triangle);
                    break;
            }
            i++;

        } while (i != 5);

        Collections.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return Double.compare(o1.getArea(), o2.getArea());
            }
        });
        System.out.println(shapes);
    }
}
