package com.company;

public class Circle extends Shape {
    private int radius;

    public Circle(int radius) {
        this.radius=radius;
    }

    @Override
    public String toString() {
        return "Rreth[rreze=" + radius +  "]";
    }

    @Override
    public double getArea() {
        return 3.14*radius*radius;
    }
}