/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnataArisa
 */
public class CustomerDAO implements IDAO<Customer> {

    private List<Customer> cList;

    public CustomerDAO() {
        cList = new ArrayList();
    }

    @Override
    public List<Customer> getAll() {
        if (cList.isEmpty()) {
            return null;
        }
        return cList;
    }

    @Override
    public boolean create(Customer data) {
        if (data == null) {
            return false;
        }
        if (getAll() == null) {
            cList.add(data);
        } else {
            for (Customer customer : getAll()) {
                if (customer.compareTo(data) == 0) {
                    return false;
                }
            }
            getAll().add(data);
        }
        return true;
    }

    @Override
    public Customer read(Customer data) {
        if (data == null) {
            return null;
        }
        if (getAll() == null) {
            return null;
        }
        for (Customer customer : getAll()) {
            if (customer.compareTo(data) == 0) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer read(String ID) {
        if (ID == null || ID.isEmpty()) {
            return null;
        }
        Customer temp = new Customer(ID);
        return read(temp);
    }

    @Override
    public boolean update(Customer data) {
        if (data == null) {
            return false;
        }
        if (getAll() == null) {
            return false;
        }
        for (Customer customer : getAll()) {
            if (customer.compareTo(data) == 0) {
                customer.setcName(data.getcName());
                customer.setcAddress(data.getcAddress());
                customer.setcPhone(data.getcPhone());
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer delete(Customer data) {
        if (data == null) {
            return null;
        }
        if (getAll() == null) {
            return null;
        }
        for (int i = 0; i < getAll().size(); i++) {
            Customer temp = getAll().get(i);
            if (temp.compareTo(data) == 0) {
                getAll().remove(i);
                return temp;
            }
        }
        return null;
    }

    @Override
    public Customer delete(String ID) {
        if (ID == null || ID.isEmpty()) {
            return null;
        }
        Customer temp = new Customer(ID);
        return delete(temp);
    }

    @Override
    public void traverser() {
        if (getAll() == null) {
            System.out.println("Empty list !!!");
            return;
        }
        for (Customer customer : getAll()) {
            visit(customer);
        }
        System.out.println("--- This is the end of the list ---");
    }

    public void visit(Object data) {
        System.out.println(data);
    }
}
