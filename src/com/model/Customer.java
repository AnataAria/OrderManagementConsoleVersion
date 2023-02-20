/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.StringTokenizer;
import my.util.Validate;

/**
 *
 * @author AnataArisa
 */
public class Customer implements Comparable<Customer> {
    private String cID;
    private String cName;
    private String cAddress;
    private String cPhone;

    public Customer(String cID, String cName, String cAddress, String cPhone) {
        this.cID = cID;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cPhone = cPhone;
    }
    public Customer(){
        
    }
    
    public Customer(String ID){
        this.cID = ID.trim().toUpperCase();
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID.trim().toUpperCase();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    @Override
    public int compareTo(Customer o) {
        return this.getcID().trim().compareTo(o.getcID().trim());
    }
    
    public void importCustomer(){
        setcName(Validate.stringValidation("Enter customer name: "));
        setcAddress(Validate.stringValidation("Enter customer address: "));
        setcPhone(Validate.phoneValidation("Enter customer phonenumber: "));
    }
    
    @Override
    public String toString(){
        return getcID() + " | " + getcName()+ " | " + getcAddress() + " | " + getcPhone();
    }
}
