package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> stores = new HashMap<>();

    public void addCenter(String mystring, int mycapacity) {
        FulfillmentCenter store;
        stores.put(mystring, store = new FulfillmentCenter(mystring, mycapacity));
    }

    public void removeCenter(String mystring) {
        stores.remove(mystring);
    }

    public ArrayList<FulfillmentCenter> findEmpty() {
        ArrayList<FulfillmentCenter> mycenters = new ArrayList<>();
        for (Map.Entry i : stores.entrySet()) {
            if (stores.get(i.getKey()).actualCapacity == 0) {
                mycenters.add(stores.get(i.getKey()));
            }
        }
        return mycenters;
    }

    public void summary() {
        System.out.println("\n");
        for (Map.Entry i : stores.entrySet()) {
            System.out.println(stores.get(i.getKey()).storeName + ": " + (stores.get(i.getKey()).actualCapacity / stores.get(i.getKey()).maxCapacity * 100) + "%");
        }
    }

}
