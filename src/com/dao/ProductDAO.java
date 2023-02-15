/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnataArisa
 */
public class ProductDAO implements IDAO<Product> {

    private List<Product> productList;

    public ProductDAO() {
        productList = new ArrayList();
    }

    @Override
    public List<Product> getAll() {
        if (productList.isEmpty()) {
            return null;
        }
        return productList;
    }

    @Override
    public boolean create(Product data) {
        if (data == null) {
            return false;
        }
        if (getAll() == null) {
            this.productList.add(data);
        } else {
            for (Product product : getAll()) {
                if (data.compareTo(product) == 0) {
                    return false;
                }
            }
            getAll().add(data);
        }
        return true;
    }

    @Override
    public Product read(Product data) {
        if (data == null) {
            return null;
        }
        if (getAll() == null) {
            return null;
        }
        for (Product product : getAll()) {
            if (product.compareTo(data) == 0) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product read(String ID) {
        Product temp = new Product(ID);
        return read(temp);
    }

    @Override
    public boolean update(Product data) {
        if (data == null) {
            return false;
        }
        if (getAll() == null) {
            return false;
        }
        for (Product product : getAll()) {
            if (product.compareTo(data) == 0) {
                product.setpName(data.getpName());
                product.setOrigin(data.getOrigin());
                product.setPrice(data.getPrice());
                product.setUnit(data.getUnit());
                return true;
            }
        }
        return false;
    }

    @Override
    public Product delete(Product data) {
        if (data == null) {
            return null;
        }
        if (getAll() == null) {
            return null;
        }
        for (int i = 0; i < getAll().size(); i++) {
            Product temp = getAll().get(i);
            if (temp.compareTo(data) == 0) {
                getAll().remove(i);
                return temp;
            }
        }
        return null;
    }

    @Override
    public Product delete(String ID) {
        if (ID == null || ID.isEmpty()) {
            return null;
        }
        Product temp = new Product(ID);
        return delete(temp);
    }

    @Override
    public void traverser() {
        if (getAll() == null) {
            System.out.println("Empty list !!!");
        }
        for (Product product : getAll()) {
            visit(product);
        }
        System.out.println("--- This is the end of the list ---");
    }

    public void visit(Object data) {
        System.out.println(data);
    }
}
