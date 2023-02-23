/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;
import my.util.Validate;

/**
 *
 * @author AnataArisa
 */
public class Product implements Comparable<Product>,IOutput {
    private String pID;
    private String pName;
    private String unit;
    private String origin;
    private int price;
    
    public Product(String pID){
        this.pID = pID.toUpperCase().trim();
    }
    public Product(){
        
    }

    public Product(String pID, String pName, String origin, String unit, int price) {
        this.pID = pID;
        this.pName = pName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID.trim().toUpperCase();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return this.pID.trim().compareTo(o.getpID().trim());
    }
    
    public void importProduct(){
        setpName(Validate.stringValidation("Enter product name: "));
        setOrigin(Validate.stringValidation("Enter product origin: "));
        setUnit(Validate.stringValidation("Enter product unit: "));
        setPrice(Validate.intValidation("Enter product price: ", 1));
    }
    
    @Override
    public String toString(){
        return getpID() + "|" + getpName() + "|" +getOrigin()+ "|" +getUnit()+ "|" +getPrice();
    }
    
    public String visit(){
        return getpID() + "|" + getpName() + "|" +getOrigin()+ "|" +getUnit()+ "|" +getPrice();
    }
    
    @Override
    public String output(){
        return String.format("|%-4s|%-20s|%-20s|%-13s|%-15d|", getpID(),getpName(),getOrigin(),getUnit(),getPrice());
    }
}
