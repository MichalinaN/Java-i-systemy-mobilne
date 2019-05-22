package com.company;

public class Square extends Figure implements Print {
    double side;

    public Square() {
    }

    public Square(double side) {
        this.side = side;
    }


    @Override
    public double calculateArea() {
        double sarea = side * side;
        return sarea;
    }

    @Override
    public double calculatePerimeter() {
        double perimetr = 4 * side;
        return perimetr;
    }

    @Override
    public void print() {
        System.out.println("\n Square: \n Area: " + calculateArea() + "\n Perimetr: " + calculatePerimeter());
    }
}
