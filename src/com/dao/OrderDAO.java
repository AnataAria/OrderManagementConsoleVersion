/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnataArisa
 */
public class OrderDAO implements IDAO<Order> {

    private List<Order> oList;

    public OrderDAO() {
        this.oList = new ArrayList();
    }

    public OrderDAO(List<Order> oList) {
        this.oList = oList;
    }
    
    
    
    @Override
    public List<Order> getAll() {
        if(oList.isEmpty()) return null;
        else return oList;
    }

    @Override
    public boolean create(Order data) {
        if(data == null) return false;
        if(getAll() == null){
            oList.add(data);
        }else{
            for(Order order: getAll()){
                if(order.compareTo(data) == 0){
                    return false;
                }
            }
            getAll().add(data);
        }
        return true;
    }

    @Override
    public Order read(Order data) {
       if(data == null) return null;
       if(getAll() == null) return null;
       for(Order order: getAll()){
           if(data.compareTo(order) == 0){
               return order;
           }
       }
       return null;
    }

    @Override
    public Order read(String ID) {
       if(ID == null || ID.isEmpty()) return null;
       Order temp = new Order(ID);
       return read(temp);
    }

    @Override
    public boolean update(Order data) {
        if(data == null) return false;
        if(getAll() == null) return false;
        for(Order order: getAll()){
            if(order.compareTo(data)  == 0){
                order.setpID(data.getpID());
                order.setcID(data.getcID());
                order.setOrderDate(data.getOrderDate());
                order.setOrderQuantity(data.getOrderQuantity());
                order.setStatus(data.isStatus());
                return true;
            }
        }
        return false;
    }

    @Override
    public Order delete(Order data) {
        if(data == null) return null;
        if(getAll() == null) return null;
        for(int i = 0; i < getAll().size(); i++){
            Order temp = getAll().get(i);
            if(temp.compareTo(data) == 0){
                getAll().remove(i);
                return temp;
            }
        }
        return null;
    }

    @Override
    public Order delete(String ID) {
        if(ID == null || ID.isEmpty()) return null;
        return delete(new Order(ID));
    }
    
    @Override
    public void traverser() {
        if (getAll() == null) {
            System.out.println("Empty list !!!");
            return;
        }
        for (Order order : getAll()) {
            visit(order);
        }
        System.out.println("--- This is the end of the list ---");
    }

    public void visit(Object data) {
        System.out.println(data);
    }
    
}
