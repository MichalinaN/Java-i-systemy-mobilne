package com.company;

import java.util.ArrayList;

import static com.company.ItemCondition.NEW;

public class Main {

    public static void main(String[] args) {
        FulfillmentCenter myStore = new FulfillmentCenter("Magazyn", 50);
        Item phone = new Item("Phone", NEW, 0.4, 1);
        Item computer = new Item("Computer", com.company.ItemCondition.NEW, 1.5, 1);
        Item guitar = new Item("Guitar", ItemCondition.REFURBISHED, 3.5, 3);
        Item electricguitar = new Item("ElectricGuitar", NEW, 3, 2);
        Item iphone = new Item("IPhone", ItemCondition.USED, 0.4, 1);

        System.out.println("\nWyswietlanie informacji o produktach: ");
        phone.print();
        computer.print();
        guitar.print();

        myStore.addProduct(phone);
        myStore.addProduct(computer);
        myStore.addProduct(guitar);

        System.out.println("\nProdukty ktore zostaly dodane: ");
        System.out.println(myStore.listOfProducts.get(0).name);
        System.out.println(myStore.listOfProducts.get(1).name);
        System.out.println(myStore.listOfProducts.get(2).name);

        int result1 = myStore.countByCondition(NEW);
        System.out.println("\nIlosc nowych produktow: ");
        System.out.println(result1);

        Item k = myStore.search("Phone");
        System.out.println("\nSzukanie produktu o okreslonej nazwie: ");
        System.out.println(k.name);

        myStore.removeProduct(phone);
        System.out.println("\nZostaje usuniety produkt o okreslonej nazwie, zostaja: ");
        System.out.println(myStore.listOfProducts.get(0).name);
        System.out.println(myStore.listOfProducts.get(1).name);

        myStore.getProduct(computer);
        System.out.println("\nZmieniajszamy ilosc produktu lub go calkowice usuwamy, zostaje: ");
        System.out.println(myStore.listOfProducts.get(0).name);

        myStore.addProduct(electricguitar);
        myStore.addProduct(iphone);

        System.out.println("\nNowe produkty zostaly dodane, lista wyglada nastepujaco: ");
        System.out.println(myStore.listOfProducts.get(0).name);
        System.out.println(myStore.listOfProducts.get(1).name);
        System.out.println(myStore.listOfProducts.get(2).name);

        Item myitem[] = myStore.searchPartial("Gui");
        System.out.println("\nWyszukiwanie wszystkich produktow majacych w nazwie Gui");
        for (Item i : myitem) {
            System.out.println(i.name);
        }

        int result2 = myStore.countByCondition(NEW);
        System.out.println("\nLiczymy ile teraz jest nowych produktow: ");
        System.out.println(result2);

        System.out.println("\nWyswietlamy podsumowanie: ");
        myStore.summary();

        ArrayList<Item> items;
        items = myStore.sortByName();
        System.out.println("\nSortowanie alfabetycznie po nazwie: ");
        for (Item i : items) {
            System.out.println(i.name);
        }

        ArrayList<Item> myitems;
        myitems = myStore.sortByAmount();
        System.out.println("\nSortowanie wedlug ilosci, malejaco: ");
        for(Item i:myitems) {
            System.out.println(i.name);
        }

        System.out.println("\nProdukt, ktorego jest najwiecej: ");
        Item result3 = myStore.max();
        System.out.println(result3.name);

        FulfillmentCenterContainer myStores = new FulfillmentCenterContainer();

        myStores.addCenter("Google shop",90);
        myStores.addCenter("Apple shop", 80);
        myStores.addCenter("Electronic shop", 100);

        ArrayList<FulfillmentCenter> mycenters;
        mycenters = myStores.findEmpty();
        System.out.println("\nWyswietlanie pustych magazynow: ");
        for(FulfillmentCenter i : mycenters){
            System.out.println(i.storeName);
        }

        Item samsungPhone = new Item("Samsung", ItemCondition.USED, 0.4, 23);
        myStores.stores.get("Google shop").addProduct(samsungPhone);

        Item iPhone7 = new Item("iPhone7", ItemCondition.NEW, 0.4, 30);
        myStores.stores.get("Apple shop").addProduct(iPhone7);

        Item iPhoneXE = new Item("iPhoneXE", ItemCondition.NEW, 0.4, 4);
        myStores.stores.get("Apple shop").addProduct(iPhoneXE);

        myStores.removeCenter("Electronic shop");

        myStores.summary();
    }
}
