package com.company;


public class Triangle extends Figure implements Print {
    double base, arm1, arm2;

    public Triangle() {
    }

    public Triangle(double base, double arm1, double arm2) {
        this.base = base;
        this.arm1 = arm1;
        this.arm2 = arm2;
    }


    @Override
    double calculateArea() {
        double p = (this.base + this.arm1 + this.arm2) / 2;
        double heron = p * (p - this.base) * (p - this.arm1) * (p - this.arm2);
        return Math.sqrt(heron);
    }

    @Override
    double calculatePerimeter() {
        double perimeter = (this.base + this.arm1 + this.arm2);
        return perimeter;
    }

    @Override
    public void print() {
        System.out.println("\n Triangle: \n Area: " + calculateArea() + "\n Perimetr: " + calculatePerimeter());
    }


}
