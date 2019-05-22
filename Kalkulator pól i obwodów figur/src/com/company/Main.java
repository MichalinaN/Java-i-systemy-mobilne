package com.company;

import java.util.Scanner;
import java.lang.String;

public class Main {

    public static void main(String[] args) {
        boolean dothings = true;
        Scanner scanner = new Scanner(System.in);

        while (dothings) {
            System.out.println("Choose option from the following: \n 1. I want choose a figure \n 2. Exit");
            byte chooseThing = scanner.nextByte();
            switch (chooseThing) {
                case 1:
                    System.out.println("Choose figure: " + "\n1. Triangle" + "\n2. Square" + "\n3. Circle");
                    byte choosefigure = scanner.nextByte();
                    switch (choosefigure) {
                        case 1:
                            System.out.println("Enter the base length");
                            double base = scanner.nextDouble();
                            if (base <= 0) {
                                System.out.println("Length has to be bigger than 0");
                                break;
                            }
                            System.out.println("Enter the side lengths");
                            double arm1 = scanner.nextDouble();
                            if (arm1 <= 0) {
                                System.out.println("Length has to be bigger than 0");
                                break;
                            }
                            double arm2 = scanner.nextDouble();
                            if (arm2 <= 0) {
                                System.out.println("Length has to be bigger than 0");
                                break;
                            }
                            if (base < arm1 + arm2) {
                                Triangle triangle = new Triangle(base, arm1, arm2);
                                triangle.print();
                            } else {
                                System.out.println("Triangle can not be created");
                            }
                            break;
                        case 2:
                            System.out.println("Enter the side length");
                            double side = scanner.nextDouble();
                            if (side <= 0) {
                                System.out.println("Length has to be bigger than 0");
                                break;
                            }
                            Square square = new Square(side);
                            square.print();
                            break;
                        case 3:
                            System.out.println("Enter the length of the radius");
                            double radius = scanner.nextDouble();
                            if (radius <= 0) {
                                System.out.println("Length has to be bigger than 0");
                                break;
                            }
                            Circle circle = new Circle(radius);
                            circle.print();
                            break;
                        default:
                            System.out.println("Wrong choice");
                            break;
                    }
                break;
                case 2:
                    System.out.println("Exit");
                    dothings = false;
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
    }


}

