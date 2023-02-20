/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controler;

import com.dao.*;
import com.model.*;
import java.util.Scanner;
import my.util.FileMangement;
import my.util.IFileManage;
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
                } else {
                    System.out.println("Skiped !!!");
                }
            }
        } while (check);
    }

    public void createProduct() {
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
                } else {
                    System.out.println("Skiped !!!");
                }
            }
        } while (check);
    }

    public void createOrder() {
        String ID = Validate.regexValidate("O[\\d]{3}", "Enter Order ID: ");
        Order order = oDAO.read(ID);
        if (order == null) {
            order = new Order(ID);
            ID = Validate.regexValidate("C[\\d]{3}", "Enter Customer ID: ");
            Customer temp = cDAO.read(ID);
            if (temp != null) {
                System.out.println(temp);
                order.setcID(ID);
                ID = Validate.regexValidate("P[\\d]{3}", "Enter publisher ID: ");
                Product product = pDAO.read(ID);
                if (product != null) {
                    System.out.println(product);
                    order.setpID(ID);
                    order.importOrder();
                    if (Validate.booleanValidation("Are you sure want to import this ?")) {
                        oDAO.create(order);
                        System.out.println("Add order Success!!! ");
                        System.out.println(product.toString());
                    } else {
                        System.out.println("Skiped !!!");
                    }
                }
            }
        }
    }

    public void searchProduct() {
        Product temp = (Product) search("Enter product ID", "P[\\d]{3}");
        if (temp != null) {
            System.out.println(temp);
        }
    }

    public void searchOrder() {
        Order temp = (Order) search("Enter order ID: ", "O[\\d]{3}");
        if (temp != null) {
            System.out.println(temp);
        }
    }

    public void searchCustomer() {
        Customer temp = (Customer) search("Enter customer ID: ", "C[\\d]{3}");
        if (temp != null) {
            System.out.println(temp);
        }
    }

    private Object search(String message, String regex) {
        String ID = Validate.regexValidate(regex, message);
        if (regex.charAt(0) == 'C') {
            Customer customer = cDAO.read(ID);
            if (customer != null) {
                return customer;
            }
        } else {
            if (regex.charAt(0) == 'P') {
                Product product = pDAO.read(ID);
                if (product != null) {
                    return product;
                }
            } else {
                if (regex.charAt(0) == 'O') {
                    Order order = oDAO.read(ID);
                    if (order != null) {
                        return order;
                    }
                }
            }
        }
        return null;
    }

    public void updateProduct() {
        String ID = Validate.regexValidate("P[\\d]{3}", "Enter product ID: ");
        Product temp = pDAO.read(ID);
        if (temp != null) {
            System.out.println(temp);
            temp.importProduct();
            pDAO.update(temp);
            System.out.println("Update product with PID: " + temp.getpID() + " Success !!!");
            System.out.println(temp);
        }
    }

    public void updateCustomer() {
        String ID = Validate.regexValidate("C[\\d]{3}", "Enter customer ID: ");
        Customer temp = cDAO.read(ID);
        if (temp != null) {
            System.out.println(temp);
            temp.importCustomer();
            cDAO.update(temp);
            System.out.println("Update product with CID: " + temp.getcID() + " Success !!!");
            System.out.println(temp);
        }
    }

    public void updateOrder() {
        String ID = Validate.regexValidate("O[\\d]{3}", "Enter order ID: ");
        Order temp = oDAO.read(ID);
        if (temp != null) {
            System.out.println(temp);
            ID = Validate.regexValidateCanSkip("C[\\d]{3}", "Enter customer ID: ");
            if (ID != null) {
                temp.setcID(ID);
                ID = Validate.regexValidateCanSkip("P[\\d]{3}", "Enter product ID");
                if (ID != null) {
                    temp.setpID(ID);
                    int quantity = Validate.intValidationCanSkip("Enter order quantity: ", 1, Integer.MAX_VALUE);
                    if (quantity != -1) {
                        temp.setOrderQuantity(quantity);
                        String date;
                        if ((date = Validate.dateTimeValidateCanSkip("Enter order date: ", "dd-MM-YYYY")) != null) {
                            temp.setOrderDate(date);
                            temp.setStatus(Validate.booleanValidation("Enter status: "));
                            oDAO.update(temp);
                        } else {
                            System.out.println("Skiped !!!");
                        }
                        temp.setStatus(true);
                    } else {
                        System.out.println("Skiped !!!");
                    }
                } else {
                    System.out.println("Skiped !!!");
                }
            } else {
                System.out.println("Skiped !!!");
            }
        }
    }

    public void deleteCustomer() {
        Customer temp = (Customer) search("Enter customer ID", "C[\\d]{3}");
        if (temp != null) {
            cDAO.delete(temp);
            System.out.println("Delete successful !!!");
            System.out.println(temp);
        }
    }

    public void deleteProduct() {

    }

    public void deleteOrder() {
        Order order = (Order) search("Enter order ID: ", "O[\\d]{3}");
        if (order != null) {
            if (oDAO.delete(order) != null) {
                System.out.println("Delete successfull");
            }
        }
    }

    public void readOrderFile() {
        IFileManage<Order> orderManage = new FileMangement();
        orderManage.setPath("src\\data\\order.txt");
        orderManage.loadFromFile();
        if (oDAO.getAll() == null) {
            for (Order order : orderManage.getList()) {
                oDAO.create(order);
            }
            return;
        }
        for (Order order : orderManage.getList()) {
            if (!isOrderExist(order.getoID())) {
                oDAO.create(order);
            }
        }
    }

    public void readProductFile() {
        IFileManage<Product> productManage = new FileMangement();
        productManage.setPath("src\\data\\product.txt");
        productManage.loadFromFile();
        if (pDAO.getAll() == null) {
            for (Product product : productManage.getList()) {
                pDAO.create(product);
            }
            return;
        }
        for (Product product : productManage.getList()) {
            if (!isProductExist(product.getpID())) {
                pDAO.create(product);
            }
        }
    }

    public void readCustomerFile() {
        IFileManage<Customer> customerManage = new FileMangement();
        customerManage.setPath("src\\data\\customer.txt");
        customerManage.loadFromFile();
        if (cDAO.getAll() == null) {
            for (Customer customer : customerManage.getList()) {
                cDAO.create(customer);
            }
            return;
        }
        for (Customer customer : customerManage.getList()) {
            if (!isCustomerExist(customer.getcID())) {
                cDAO.create(customer);
            }
        }
    }

    public void saveOrderToFile() {
        readOrderFile();
        if (oDAO.getAll() == null) {
            return;
        }
        IFileManage<Order> order = new FileMangement();
        order.setPath("src\\data\\order.txt");
        order.setList(oDAO.getAll());
        order.uploadToFile();
    }

    public void saveCustomerToFile() {
        readCustomerFile();
        if (cDAO.getAll() == null) {
            return;
        }
        IFileManage<Customer> customer = new FileMangement();
        customer.setPath("src\\data\\customer.txt");
        customer.setList(cDAO.getAll());
        customer.uploadToFile();
    }

    public void saveProductToFile() {
        readProductFile();
        if (pDAO.getAll() == null) {
            return;
        }
        IFileManage<Product> product = new FileMangement();
        product.setPath("src\\data\\product.txt");
        product.setList(pDAO.getAll());
        product.uploadToFile();
    }

    public void traverserCustomer() {
        cDAO.traverser();
    }

    public void traverserOrder() {
        oDAO.traverser();
    }

    public void traverserProduct() {
        pDAO.traverser();
    }

    public void traverserPendingOrder() {
        if (oDAO.getAll() == null) {
            System.out.println("Empty list");
            return;
        }
        for (Order order : oDAO.getAll()) {
            if (order.isStatus() == false) {
                System.out.print(order.getoID() + " | " + order.getcID() + " | " + order.getpID() + "|" + order.getOrderQuantity() + " | " + order.getOrderDate() + "|");
                System.out.println(order.isStatus() ? "Delivered" : "Not Delivered");
            }
        }
    }

    private boolean isCustomerExist(String ID) {
        Customer customer = cDAO.read(ID);
        return customer != null;
    }

    private boolean isOrderExist(String ID) {
        Order order = oDAO.read(ID);
        return order != null;
    }

    private boolean isProductExist(String ID) {
        Product product = pDAO.read(ID);
        return product != null;
    }

    private void sortProduct() {
        if (pDAO.getAll() == null) {
            return;
        }
        int size = pDAO.getAll().size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Product o1 = pDAO.getAll().get(i);
                Product o2 = pDAO.getAll().get(j);
                if (o1.getpID().compareTo(o2.getpID()) == 1) {
                    pDAO.getAll().set(i, o2);
                    pDAO.getAll().set(j, o1);
                }
            }
        }
    }

    private void sortOrderByName() {
        if (oDAO.getAll() == null) {
            return;
        }
        int size = oDAO.getAll().size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Order o1 = oDAO.getAll().get(i);
                Order o2 = oDAO.getAll().get(j);
                Customer co1 = cDAO.read(o1.getcID());
                Customer co2 = cDAO.read(o2.getcID());
                if (co1 != null && co2 != null) {
                    if(co1.getcName().compareTo(co2.getcName()) == 1){
                        oDAO.getAll().set(i, o2);
                        oDAO.getAll().set(j, o1);
                    }
                }
            }
        }
    }

    private void sortCustomer() {
        if (cDAO.getAll() == null) {
            return;
        }
        int size = cDAO.getAll().size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Customer o1 = cDAO.getAll().get(i);
                Customer o2 = cDAO.getAll().get(j);
                if (o1.getcID().compareTo(o2.getcID()) == 1) {
                    cDAO.getAll().set(i, o2);
                    cDAO.getAll().set(j, o1);
                }
            }
        }
    }

    private void orderMenu(){
        System.out.println("|Order ID \t| Customer name \t|Product ID \t|");
    }
}
