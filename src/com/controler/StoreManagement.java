/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controler;

import com.dao.*;
import com.model.*;

/**
 *
 * @author AnataArisa
 */
public class StoreManagement {
    IDAO<Customer> cDAO;
    IDAO<Order> oDAO;
    IDAO<Product> pDAO;
    
    public StoreManagement(){
        cDAO = new CustomerDAO();
        oDAO = new OrderDAO();
        pDAO = new ProductDAO();
    }
    
    public void createCustomer(){
        Customer customer = new Customer();
        
    }
}
