package com.company;

public class Shape {

    /** Returns a self-descriptive string */
    @Override
    public String toString() {
        return "Shape";
    }

    public double getArea() {
        System.err.println("com.company.Shape unknown! Cannot compute area!");
        return 0;
    }
}