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
public class Order implements Comparable<Order>,IOutput {
    private String oID;
    private String cID;
    private String pID;
    private int orderQuantity;
    private String orderDate;
    private boolean status;

    public Order(String oID, String cID, String pID, int orderQuantity, String orderDate, boolean status) {
        this.oID = oID;
        this.cID = cID;
        this.pID = pID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }
    public Order(String ID){
        this.oID = ID.trim();
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID.trim();
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID.trim();
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID.trim();
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate.trim();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(Order o) {
        return getoID().trim().compareTo(o.getoID().trim());
    }
    
    public void importOrder(){
        setOrderQuantity(Validate.intValidation("Enter quantity: ", 1));
        setOrderDate(Validate.dateTimeValidate("Enter order date: ", "dd-MM-yyyy"));
        setStatus(Validate.booleanValidation("Enter status: "));
    }
    
    @Override
    public String toString(){
        return getoID()+ "|" +getcID()+ "|" +getpID()+ "|" +getOrderQuantity()+ "|" +getOrderDate()+"|"+isStatus();
    }
    
    @Override
    public String output(){
        String status = (isStatus())? "Delivered":"Not Delivered";
        return String.format("|%-4s|%-4s|%-4s|%-5d|%-13s|%-14s|",getoID(),getcID(),getpID(),getOrderQuantity(),getOrderDate(),status);
    }

}
