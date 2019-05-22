package com.company;

public class Circle extends Figure implements Print {
    double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public double calculateArea() {
        double carea = Math.PI * radius * radius;
        return carea;
    }

    @Override
    public double calculatePerimeter() {
        double cPerimetr = 2 * radius * Math.PI;
        return cPerimetr;
    }

    @Override
    public void print() {
        System.out.println("\n Circle: \n Area: " + calculateArea() + "\n Perimetr: " + calculatePerimeter());
    }

}
