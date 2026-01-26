package com.example.inventoryrestapi;

public class Supplier extends AbstractItem {

    private String companyName;
    private String companyPhone;
    private String companyCountry;

    public Supplier(String companyName, String companyPhone, String companyCountry, int id){
        super(id);
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.companyCountry = companyCountry;
    }



    public String getCompanyName(){
        return companyName;
    }
    public String getCompanyPhone(){
        return companyPhone;
    }
    public String getCompanyCountry(){
        return companyCountry;
    }


    public void setCompanyName(String companyName) {
        if (companyName != null)
            this.companyName = companyName;
        else
            System.out.println("company name cannot be null");
    }
    public void setCompanyPhone(String companyPhone) {
        if (companyPhone != null)
            this.companyPhone = companyPhone;
        else
            System.out.println("company phone cannot be null");
    }
    public void setCompanyCountry(String companyCountry) {
        if (companyCountry != null)
            this.companyCountry = companyCountry;
        else
            System.out.println("company country cannot be null");
    }


    @Override
    public String toString(){
        return "Company: " + companyName + " " + "Phone: " + companyPhone + " " +  "Country: " + companyCountry;
    }
    @Override
    public String shortInfo() {
        return "Supplier: " + companyName + " " + companyCountry;
    }
}