package com.example.inventoryrestapi;

public class Product extends AbstractItem {

    private String name;
    private double price;
    private int barcode;

    public Product() {}


    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getBarcode(){
        return barcode;
    }



    public void setPrice(double price){
        if(price < 0){
            System.out.println("price cannot be negative");
        } else {
            this.price = price;
        }
    }
    public void setName(String name){
        if(name != null){
            this.name = name;
        } else {
            System.out.println("name have to be not null");
        }
    }

    public void setBarcode(int barcode){
        this.barcode = barcode;
    }


    @Override
    public String toString() {
        return "Product: " + name + ", Price: " + price + ", id: " + getId();
    }
    @Override
    public String shortInfo() {
        return "Product: " + name + ", price=" + price;
    }
}