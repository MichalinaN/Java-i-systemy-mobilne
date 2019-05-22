package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FulfillmentCenter extends Item implements Comparator<String> {
    String storeName;
    ArrayList<Item> listOfProducts = new ArrayList<>();
    double maxCapacity;
    double actualCapacity = 0;

    public FulfillmentCenter(String storeName, int maxCapacity) {
        this.storeName = storeName;
        this.maxCapacity = maxCapacity;
    }

    public void addProduct(Item myitem) {
        if ((actualCapacity + (myitem.weight * myitem.counter)) <= maxCapacity) {
            boolean isAdd = false;
            for (Item i : listOfProducts) {
                if (i.compareTo(myitem) == 0) {
                    i.counter += myitem.counter;
                    actualCapacity += myitem.counter * myitem.weight;
                    isAdd = true;
                }
            }
            if (!isAdd) {
                listOfProducts.add(myitem);
                actualCapacity += myitem.counter * myitem.weight;
            }
        } else
            System.err.println("Capacity is exceeded");
    }

    public void getProduct(Item myitem) {
        for (Item i : listOfProducts) {
            if (i.compareTo(myitem) == 0) {
                if (i.counter > 1) {
                    i.counter--;
                    actualCapacity -= i.weight;
                } else {
                    actualCapacity -= i.weight;
                    listOfProducts.remove(i);
                    break;
                }
            }
        }
    }

    public void removeProduct(Item myitem) {
        for (Item i : listOfProducts) {
            if (i.compareTo(myitem) == 0) {
                actualCapacity -= i.weight * i.counter;
                listOfProducts.remove(i);
                break;
            }
        }
    }

    public Item search(String mystring) {
        Item item = null;
        for (Item i : listOfProducts) {
            if ((compare(i.name, mystring)) == 0) {
                item = i;
            }
        }
        return item;
    }

    public Item[] searchPartial(String mystring) {
        int localCounter = 0;
        for (Item i : listOfProducts) {
            if (i.name.contains(mystring)) {
                localCounter++;
            }
        }
        Item[] myitems = new Item[localCounter];
        int j = 0;
        for (Item i : listOfProducts) {
            if (i.name.contains(mystring)) {
                myitems[j] = i;
                j++;
            }
        }
        return myitems;
    }

    public int countByCondition(ItemCondition cond) {
        int localCounter = 0;
        for (Item i : listOfProducts) {
            if (cond == i.condition) {
                localCounter++;
            }
        }
        return localCounter;
    }

    public void summary() {
        for (Item i : listOfProducts) {
            System.out.println("\n" + i.name + " " + i.counter + " " + i.weight + " " + i.condition);
        }
    }

    public ArrayList<Item> sortByName() {
        ArrayList<Item> mySortList = new ArrayList<>();
        for (Item i : listOfProducts) {
            mySortList.add(i);
        }
        Collections.sort(mySortList);
        return mySortList;
    }

    public ArrayList<Item> sortByAmount() {
        ArrayList<Item> mySortList2 = new ArrayList<>();
        for (Item i : listOfProducts) {
            mySortList2.add(i);
        }
        mySortList2.sort(Comparator.comparing(Item::getCounter));
        Collections.reverse(mySortList2);
        return mySortList2;
    }

    public Item max() {
        Item myitem = Collections.max(listOfProducts, Comparator.comparing(Item::getCounter));
        return myitem;
    }

    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
