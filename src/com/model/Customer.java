/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

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
        this.cID = ID;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
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
        return this.getcID().compareTo(o.getcID());
    }
}
