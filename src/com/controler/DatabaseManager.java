/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controler;

import com.model.*;
import java.util.ArrayList;
import java.util.List;
import my.util.*;

/**
 *
 * @author AnataArisa
 */
public class DatabaseManager {

    private IFileManage<Customer> cFileManager;
    private IFileManage<Order> oFileManager;
    private IFileManage<Product> pFileManager;
//    private final List<Customer> customerDeleteList = new ArrayList();
//    private final List<Order> orderDeleteList = new ArrayList();
//    private final List<Product> productDeleteList = new ArrayList();
    private List<Customer> customerList;
    private List<Order> orderList;
    private List<Product> productList;
    private Thread updateCFile;
    private Thread updateOFile;
    private Thread updatePFile;
    private boolean threadStatus = true;

    public DatabaseManager() {
        cFileManager = new FileMangement<>("src\\data\\customer.txt");
        oFileManager = new FileMangement<>("src\\data\\order.txt");
        pFileManager = new FileMangement<>("src\\data\\product.txt");
        customerList = cFileManager.getList();
        orderList = oFileManager.getList();
        productList = pFileManager.getList();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void uploadToFile() {
        oFileManager.uploadToFile();
        pFileManager.uploadToFile();
        cFileManager.uploadToFile();
    }
    
    public void loadFromFile(){
        oFileManager.loadFromFile();
        pFileManager.loadFromFile();
        cFileManager.loadFromFile();
    }
    
    public void saveCustomerToFile(){
        cFileManager.uploadToFile();
    }
    
    public void saveOrderToFile(){
        oFileManager.uploadToFile();
    }
    
    public void saveProductToFile(){
        pFileManager.uploadToFile();
    }

//    public void updateList() {
//        updateCus();
//        updateOr();
//        updatePro();
//    }

    public void startAutoUpdate() {
        loadFromFile();
        threadProduct();
        threadCustomer();
        threadOrder();
    }

    public void stopAutoUpdate() {
        threadStatus = false;
    }
    
    public List<Order> getOrderFile(){
        IFileManage<Order> temp = new FileMangement<>("src\\data\\order.txt");
        temp.loadFromFile();
        return temp.getList();
    }
    
    public List<Product> getProductFile(){
        IFileManage<Product> temp = new FileMangement<>("src\\data\\product.txt");
        temp.loadFromFile();
        return temp.getList();
    }

    public List<Customer> getCustomerFile(){
        IFileManage<Customer> temp = new FileMangement<>("src\\data\\customer.txt");
        temp.loadFromFile();
        return temp.getList();
    }
//    public void addCustomerDeleteList(Customer data) {
//        customerDeleteList.add(data);
//    }
//
//    public void addOrderDeleteList(Order data) {
//        orderDeleteList.add(data);
//    }
//
//    public void addPublisherDeleteList(Product data) {
//        productDeleteList.add(data);
//    }

    private void threadCustomer() {
        Runnable cus;
        cus = () -> {
            int size = customerList.size();
//            updateCus();
            do {
                if (size != customerList.size()) {
//                    updateCus();
                    cFileManager.uploadToFile();
                    size = customerList.size();
                }
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                }
            } while (threadStatus);
        };
        updateCFile = new Thread(cus);
        updateCFile.start();
    }

    private void threadProduct() {
        Runnable pro;
        pro = () -> {
//            updatePro();
            int size = productList.size();
            do {
//                if (size != productList.size()) {
////                    updatePro();
//                    pFileManager.uploadToFile();
//                    size = productList.size();
//                }
                pFileManager.clear();
                pFileManager.loadFromFile();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                }
            } while (threadStatus);
        };
        updatePFile = new Thread(pro);
        updatePFile.start();
    }

    private void threadOrder() {
        Runnable or;
        or = () -> {
//            updateOr();
            int size = orderList.size();
            do {
                if (size != orderList.size()) {
//                    updateOr();
                    oFileManager.uploadToFile();
                    size = orderList.size();
                }
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                }
            } while (threadStatus);
        };
        updateOFile = new Thread(or);
        updateOFile.start();
    }

//    private void updateCus() {
//        IFileManage<Customer> temp = new FileMangement<>("src\\data\\customer.txt");
//        temp.loadFromFile();
//        List<Customer> tmp = temp.getList();
//        
////        if (customerList.isEmpty()) {
////            cFileManager.loadFromFile();
////        }
////        int sizeMain = customerList.size();
////        int sizeTemp = tmp.size();
////        if (sizeMain > sizeTemp) {
////            for (int i = sizeMain; i < sizeTemp; i++) {
////                customerDeleteList.add(customerList.get(i));
////                customerList.remove(i);
////            }
////        } else {
////            for (Customer cus : tmp) {
////                if (!checkCusExist(cus.getcID()) && isCustomerDeleted(cus.getcID())) {
////                    customerList.add(cus);
////                }
////            }
////        }
//
//    }
//
//    private void updatePro() {
//        IFileManage<Product> temp = new FileMangement<>("src\\data\\product.txt");
//        temp.loadFromFile();
//        List<Product> tmp = temp.getList();
//        int sizeMain = productList.size();
//        int sizeTemp = tmp.size();
//        if (sizeMain > sizeTemp) {
//            for (int i = sizeMain; i < sizeTemp; i++) {
//                customerDeleteList.add(customerList.get(i));
//                customerList.remove(i);
//            }
//        }else{
//            for (Product pro : tmp) {
//                if (!checkProductExist(pro.getpID()) && !isProductDeleted(pro.getpID())) {
//                    productList.add(pro);
//                }
//            }
//        }
//    }
//    
//
//    private void updateOr() {
//        IFileManage<Order> temp = new FileMangement<>("src\\data\\order.txt");
//        temp.loadFromFile();
//        List<Order> tmp = temp.getList();
//        for (Order or : tmp) {
//            if (!checkOrderExist(or.getoID()) && !isOrderDeleted(or.getoID())) {
//                orderList.add(or);
//            }
//        }
//    }

    private boolean checkCusExist(String ID) {
        for (Customer cus : customerList) {
            if (cus.getcID().equalsIgnoreCase(ID)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkProductExist(String ID) {
        for (Product pro : productList) {
            if (pro.getpID().equalsIgnoreCase(ID)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkOrderExist(String ID) {
        for (Order or : orderList) {
            if (or.getoID().equalsIgnoreCase(ID)) {
                return true;
            }
        }
        return false;
    }

//    private boolean isOrderDeleted(String ID) {
//        for (Order od : orderDeleteList) {
//            if (od.getoID().equalsIgnoreCase(ID)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isCustomerDeleted(String ID) {
//        for (Customer cd : customerDeleteList) {
//            if (cd.getcID().equalsIgnoreCase(ID)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isProductDeleted(String ID) {
//        for (Product cd : productDeleteList) {
//            if (cd.getpID().equalsIgnoreCase(ID)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
