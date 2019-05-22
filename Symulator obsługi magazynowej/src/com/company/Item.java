package com.company;

public class Item implements Comparable<Item> {
    String name;
    ItemCondition condition;
    double weight;
    int counter = 0;

    public Item(){
    }

    public Item(String name, ItemCondition condition, double weight, int counter) {
        this.name = name;
        this.condition = condition;
        this.weight = weight;
        this.counter = counter;
    }

    public void print() {
        System.out.println("Name: " + this.name + "\nCondition: " + this.condition + "\nWeight: " + this.weight + "\nCounter: " + this.counter);
    }

    public int getCounter(){
        return counter;
    }

    @Override
    public int compareTo(Item item) {
        return name.compareTo(item.name);
    }


}