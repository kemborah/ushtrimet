package com.company;

import java.util.Comparator;

public final class Point{
    private final double x;    // x-coordinate
    private final double y;
    private double distance;// y-coordinate


    public Point() {
        x = 0.0;
        y = 0.0;
        distance = 0;
    }



    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double distance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public double distanceTo(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
