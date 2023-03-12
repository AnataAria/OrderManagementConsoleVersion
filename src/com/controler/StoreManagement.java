/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controler;

import com.dao.*;
import com.model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import my.util.FileMangement;
import my.util.IFileManage;
import my.util.Validate;

/**
 *
 * @author AnataArisa
 */
public class StoreManagement {

    private final IDAO<Customer> cDAO;
    private final IDAO<Order> oDAO;
    private final IDAO<Product> pDAO;
    private Thread updateCFile;
    private Thread updateOFile;
    private Thread updatePFile;
    private DatabaseManager dm = new DatabaseManager();
    private final String customerMenu = String.format("|%-4s|%-20s|%-20s|%-13s|", "ID", "Name", "Address", "Phone number");
    private final String productMenu = String.format("|%-4s|%-20s|%-20s|%-13s|%-15s|", "ID", "Product name", "Origin", "Unit", "Price");
    private final String orderMenu = String.format("|%-4s|%-4s|%-4s|%-5s|%-13s|%-14s|", "OID", "CID", "PID", "Quan", "Date Order", "Status");

    public StoreManagement() {
        cDAO = new CustomerDAO(dm.getCustomerList());
        oDAO = new OrderDAO(dm.getOrderList());
        pDAO = new ProductDAO(dm.getProductList());
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
                    System.out.println(customerMenu);
                    view(customer);
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
                    System.out.println(productMenu);
                    view(product);
                    check = false;
                } else {
                    System.out.println("Skiped !!!");
                }
            }
        } while (check);
    }

    public void createOrder() {
        boolean check = true;
        do {
            String ID = Validate.regexValidate("O[\\d]{3}", "Enter Order ID: ");
            Order order = oDAO.read(ID);
            if (order == null) {
                order = new Order(ID);
                traverserCustomer();
                ID = returnCustomerID();
                Customer temp = cDAO.read(ID);
                if (temp != null) {
                    System.out.println(temp);
                    order.setcID(ID);
                    traverserProduct();
                    ID = returnProductID();
                    Product product = pDAO.read(ID);
                    if (product != null) {
                        System.out.println(product);
                        order.setpID(ID);
                        order.importOrder();
                        if (Validate.booleanValidationCanSkip("Are you sure want to import this ?")) {
                            oDAO.create(order);
                            System.out.println("Add order Success!!! ");
                            System.out.println(orderMenu);
                            view(order);
                        } else {
                            System.out.println("Skiped !!!");
                        }
                    }
                }
            }
            if (!Validate.booleanValidation("Do you want to add more?: ")) {
                check = false;
            }
        } while (check == true);

    }

    private String returnCustomerID() {
        do {
            String ID = Validate.regexValidate("C[\\d]{3}", "Enter Customer ID: ");
            if (isCustomerExist(ID)) {
                return ID;
            } else {
                System.out.println("Your customer ID is not in the database, please try again !!!");
            }
        } while (true);
    }

    public void searchProduct() {
        Product temp = (Product) search("Enter product ID", "P[\\d]{3}");
        if (temp != null) {
            System.out.println(temp);
        }
        System.out.println("Can't found the product you needed!");
    }

    public void searchOrder() {
        Order temp = (Order) search("Enter order ID: ", "O[\\d]{3}");
        if (temp != null) {
            System.out.println(temp);
        } else {
            System.out.println("Can't found order you need");
        }
    }

    public void searchCustomer() {
        Customer temp = (Customer) search("Enter customer ID: ", "C[\\d]{3}");
        if (temp != null) {
            System.out.println(customerMenu);
            view(temp);
        } else {
            System.out.println("Can't found customer !!!");
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
            do {
                String name = Validate.stringValidationCanSkip("Enter new customer name: ");
                if (name.isEmpty()) {
                    System.out.println("Skiped !!!");
                    break;
                } else {
                    temp.setcName(name);
                    break;
                }
            } while (true);
            do {
                String address = Validate.stringValidationCanSkip("Enter new customer address: ");
                if (address.isEmpty()) {
                    System.out.println("Skiped !!!");
                    break;
                } else {
                    temp.setcAddress(address);
                    break;
                }
            } while (true);
            do {
                String phoneNum = Validate.phoneValidationCanSkip("Enter new customer phone number: ");
                if (phoneNum.isEmpty()) {
                    System.out.println("Skiped !!!");
                    break;
                } else {
                    temp.setcPhone(phoneNum);
                    break;
                }
            } while (true);
//            temp.importCustomer();
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
            do {
                traverserCustomer();
                ID = Validate.regexValidateCanSkip("C[\\d]{3}", "Enter new customer ID: ");
                if (ID.isEmpty()) {
                    break;
                }
                if (isCustomerExist(ID)) {
                    temp.setcID(ID);
                    break;
                }
            } while (true);
            do {
                traverserProduct();
                ID = Validate.regexValidateCanSkip("P[\\d]{3}", "Enter new product ID: ");
                if (ID.isEmpty()) {
                    break;
                }
                if (isProductExist(ID)) {
                    temp.setpID(ID);
                    break;
                }
            } while (true);
            int quantity = Validate.intValidationCanSkip("Enter new order quantity: ", 1, Integer.MAX_VALUE);
            if (quantity != -1) {
                temp.setOrderQuantity(quantity);
            }
            String date;
            if ((date = Validate.dateTimeValidateCanSkip("Enter new order date: ", "dd-MM-yyyy")) != null) {
                temp.setOrderDate(date);
            }
            temp.setStatus(Validate.booleanValidation("Enter new status: "));
            oDAO.update(temp);
        }
    }

    public void deleteCustomer() {
        Customer temp = (Customer) search("Enter customer ID", "C[\\d]{3}");
        if (temp != null) {
            cDAO.delete(temp);
            System.out.println("Delete successful !!!");
            System.out.println(temp);
        } else {
            System.out.println("Not found customer ID !!!");
        }
    }

    public void deleteProduct() {

    }

    public void deleteOrder() {
        Order order = (Order) search("Enter order ID: ", "O[\\d]{3}");
        if (order != null) {
            if (oDAO.delete(order) != null) {
                System.out.println("Delete successfull");
                System.out.println(order);
            }
        } else {
            System.out.println("Not found order ID !!!");
        }
    }

//    public void readOrderFile() {
//        IFileManage<Order> orderManage = new FileMangement();
//        orderManage.setPath("src\\data\\order.txt");
//        orderManage.loadFromFile();
//        if (oDAO.getAll() == null) {
//            for (Order order : orderManage.getList()) {
//                oDAO.create(order);
//            }
//            return;
//        }
//        for (Order order : orderManage.getList()) {
//            if (!isOrderExist(order.getoID())) {
//                oDAO.create(order);
//            }
//        }
//    }
//
//    public void readProductFile() {
//        IFileManage<Product> productManage = new FileMangement();
//        productManage.setPath("src\\data\\product.txt");
//        productManage.loadFromFile();
//        if (pDAO.getAll() == null) {
//            for (Product product : productManage.getList()) {
//                pDAO.create(product);
//            }
//            return;
//        }
//        for (Product product : productManage.getList()) {
//            if (!isProductExist(product.getpID())) {
//                pDAO.create(product);
//            }
//        }
//    }
//
//    public void readCustomerFile() {
//        IFileManage<Customer> customerManage = new FileMangement();
//        customerManage.setPath("src\\data\\customer.txt");
//        customerManage.loadFromFile();
//        if (cDAO.getAll() == null) {
//            for (Customer customer : customerManage.getList()) {
//                cDAO.create(customer);
//            }
//            return;
//        }
//        for (Customer customer : customerManage.getList()) {
//            if (!isCustomerExist(customer.getcID())) {
//                cDAO.create(customer);
//            }
//        }
//    }
//
//    public void saveOrderToFile() {
//        readOrderFile();
//        if (oDAO.getAll() == null) {
//            return;
//        }
//        IFileManage<Order> order = new FileMangement();
//        order.setPath("src\\data\\order.txt");
//        order.setList(oDAO.getAll());
//        order.uploadToFile();
//    }
//
//    public void saveCustomerToFile() {
//        readCustomerFile();
//        if (cDAO.getAll() == null) {
//            return;
//        }
//        IFileManage<Customer> customer = new FileMangement();
//        customer.setPath("src\\data\\customer.txt");
//        customer.setList(cDAO.getAll());
//        customer.uploadToFile();
//    }
//
//    public void saveProductToFile() {
//        readProductFile();
//        if (pDAO.getAll() == null) {
//            return;
//        }
//        IFileManage<Product> product = new FileMangement();
//        product.setPath("src\\data\\product.txt");
//        product.setList(pDAO.getAll());
//        product.uploadToFile();
//    }
    public void traverserCustomer() {
        if (cDAO.getAll() == null) {
            System.out.println("Empty List !!!");
            return;
        }
        sortCustomer();
        view(cDAO.getAll(), customerMenu);
    }

    public void traverserProduct() {
        List<Product> temp = dm.getProductFile();
        if (temp.isEmpty()) {
            System.out.println("Empty list !!!");
            return;
        }
        Comparator<Product> pSort = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getpID().compareTo(o2.getpID());
            }
        };
        Collections.sort(temp, pSort);
        view(pDAO.getAll(), productMenu);
    }

    public void traverserOrder() {
        List<Order> temp = dm.getOrderFile();
        if (temp.isEmpty()) {
            System.out.println("Empty list !!!");
            return;
        }
        Comparator<Order> orderSort = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getoID().compareTo(o2.getoID());
            }
        };
        Collections.sort(temp, orderSort);
        view(temp, orderMenu);
    }

    public void trarverseOrderPendingFile() {
        List<Order> temp = dm.getOrderFile();
        if (temp == null) {
            System.out.println("Empty file !!!");
            return;
        } else {
            Comparator<Order> orderSort = new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getoID().compareTo(o2.getoID());
                }
            };
            Collections.sort(temp, orderSort);
            System.out.println(orderMenu);
            for (Order order : oDAO.getAll()) {
                if (order.isStatus() == false) {
                    view(order);
                }
            }
        }
    }
//    public void traverserCustomer() {
//        cDAO.traverser();
//    }
//
//    public void traverserOrder() {
//        oDAO.traverser();
//    }
//
//    public void traverserProduct() {
//        pDAO.traverser();
//    }

    public void traverserPendingOrder() {
        if (oDAO.getAll() == null) {
            System.out.println("Empty list");
            return;
        }
        sortOrder();
        System.out.println(orderMenu);
        for (Order order : oDAO.getAll()) {
            if (order.isStatus() == false) {
                view(order);
            }
        }
    }

    public void traverserAscOrder() {
        List<Order> tempOrder = new ArrayList();
        tempOrder.addAll(sortAscOrder());
        System.out.printf("|%-4s|%-20s|%-4s|%-5s|%-20s|%-13s|%-14s|\n", "OID", "Name", "PID", "Quan","Sell Quan", "Date Order", "Status");
        for (Order order : tempOrder) {
            String status = order.isStatus() ? "Delivered" : "Not Delivered";
            System.out.printf("|%-4s|%-20s|%-4s|%-5d|%-20f|%-13s|%-14s|\n", order.getoID(), cDAO.read(order.getcID()).getcName().trim(), order.getpID(), order.getOrderQuantity(),(float)(order.getOrderQuantity()*50.0/100.0) ,order.getOrderDate(), status);
        }
    }

    public void shutdownAutoUpdate() {
        dm.stopAutoUpdate();
    }

    public void startAutoUpdate() {
        dm.startAutoUpdate();
    }

    public void saveOrderToFile() {
        dm.saveOrderToFile();
    }

    public void saveCustomerToFile() {
        dm.saveCustomerToFile();
    }

    public void saveProductToFile() {
        dm.saveProductToFile();
    }

    public void loadAll() {
        dm.loadFromFile();
    }

    // PRIVATE ZONE : HERE IS THE PLACE STORE LOCAL METHOD
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
        if (pDAO.getAll() != null) {
            pDAO.getAll().sort(new Product());
        }
    }

    private void sortCustomer() {
        if (pDAO.getAll() != null) {
            cDAO.getAll().sort(new Customer());
        }
    }

    private void sortOrder() {
        if (oDAO.getAll() != null) {
            oDAO.getAll().sort(new Order());
        }
    }

    private List<Order> sortAscOrder() {
        List<Order> temp = dm.getOrderFile();
        int size = temp.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Order o1 = temp.get(i);
                Order o2 = temp.get(j);
                Date date1 = null;
                Date date2 = null;
                try{
                    date1 = new SimpleDateFormat("dd-MM-yyyy").parse(o1.getOrderDate());
                    date2 = new SimpleDateFormat("dd-MM-yyyy").parse(o2.getOrderDate());
                }catch(Exception e){
                    e.printStackTrace();
                }

                if (date1.compareTo(date2) < 0) {
                    temp.set(i, o2);
                    temp.set(j, o1);
                }
//                Customer co1 = cDAO.read(o1.getcID());
//                Customer co2 = cDAO.read(o2.getcID());
//                if (co1 != null || co2 != null) {
//                    if (co1.getcName().compareTo(co2.getcName()) < 0) {
//                        temp.set(i, o2);
//                        temp.set(j, o1);
//                    }
//                }
            }
        }
        return temp;
    }

    private String returnOrderID() {
        do {
            String ID = Validate.regexValidate("O[\\d]{3}", "Enter Order ID: ");
            if (isOrderExist(ID)) {
                return ID;
            } else {
                System.out.println("Your order ID is not in the database, please try again !!!");
            }
        } while (true);
    }

    private String returnProductID() {
        do {
            String ID = Validate.regexValidate("P[\\d]{3}", "Enter product ID: ");
            if (isProductExist(ID)) {
                return ID;
            } else {
                System.out.println("Your product ID is not in the database, please try again !!!");
            }
        } while (true);
    }

    private <T extends IOutput> void view(T data) {
        System.out.println(data.output());
    }

    private <T extends IOutput> void view(List<T> listData, String menuFormat) {
        System.out.println(menuFormat);
        for (T data : listData) {
            view(data);
        }
    }
}
