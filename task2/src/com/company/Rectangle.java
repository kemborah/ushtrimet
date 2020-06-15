package com.company;

public class Rectangle extends Shape {
    private int base, height;

    public Rectangle(int length, int width) {
        this.base = length;
        this.height = width;
    }

    @Override
    public String toString() {
        return "Drejtkendesh[length=" + base + ",width=" + height + "," + "]";
    }

    @Override
    public double getArea() {
        return base * height;
    }
}