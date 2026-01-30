package com.example.inventoryrestapi;
import java.util.ArrayList;
public class Inventory {

    private String location;
    private String manager;
    private ArrayList<Product> products = new ArrayList<>();


    public Inventory(String location, String manager){
        this.location = location;
        this.manager = manager;
    }
    public void addProduct(Product p){
        products.add(p);
    }
    public void allProduct(){
        for(Product p : products){
            System.out.println(p);
        }
    }
    public void searchProduct(String name){

        boolean found = false;
        for(Product p : products){
            if(p.getName().equals(name)){
                System.out.println("product was found");
                System.out.println(p);
                found = true;
            }
        }
        if(!found){
            System.out.println("no product with such name");
        }
    }
    public void sortProduct(){
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    }


    public String getLocation(){
        return location;
    }
    public String getManager(){
        return manager;
    }


    public void setLocation(String location) {
        if (location != null)
            this.location = location;
        else
            System.out.println("location cannot be null");
    }
    public void setManager(String manager){
        if(manager != null)
            this.manager = manager;
        else
            System.out.println("manager cannot be null");
    }

    @Override
    public String toString(){
        return "Location: " + location + " " + "Manager: " + manager;
    }
}