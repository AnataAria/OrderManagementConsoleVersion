/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controler;

import com.dao.*;
import com.model.*;
import java.util.Scanner;
import my.util.Validate;

/**
 *
 * @author AnataArisa
 */
public class StoreManagement {

    private IDAO<Customer> cDAO;
    private IDAO<Order> oDAO;
    private IDAO<Product> pDAO;
    private Scanner sc;

    public StoreManagement() {
        cDAO = new CustomerDAO();
        oDAO = new OrderDAO();
        pDAO = new ProductDAO();
    }

    public void createCustomer() {
        boolean check = true;
        do {
            String ID = Validate.regexValidate("C[\\d]{3}", "Enter customer ID: ");
            Customer customer = cDAO.read(ID);
            if (customer == null) {
                customer = new Customer(ID);
                customer.importCustomer();
                if (Validate.booleanValidation("Are you sure want to import this ?")) {
                    cDAO.create(customer);
                    System.out.println("Add Customer with ID " + ID + " :  Success!!! ");
                    System.out.println(customer.toString());
                    check = false;
                }else{
                    System.out.println("Skiped !!!");
                }
            }
        }while (check);
    }
    public void createOrder(){
        
    }
    
    public void createProduct(){
        boolean check = true;
        do {
            String ID = Validate.regexValidate("P[\\d]{3}", "Enter product ID: ");
            Product product = pDAO.read(ID);
            if (product == null) {
                product = new Product(ID);
                product.importProduct();
                if (Validate.booleanValidation("Are you sure want to import this ?")) {
                    pDAO.create(product);
                    System.out.println("Add product with ID " + ID + " :  Success!!! ");
                    System.out.println(product.toString());
                    check = false;
                }else{
                    System.out.println("Skiped !!!");
                }
            }
        }while (check);
    }
}
