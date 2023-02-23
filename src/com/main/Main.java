/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main;

import com.controler.StoreManagement;
import com.menu.Menu;

/**
 *
 * @author AnataArisa
 */
public class Main {

    private static final StoreManagement sm = new StoreManagement();

    public static void main(String[] args) {
        Menu menu = new Menu();
        Menu orderMenu = new Menu();
        String[] subOption = {"Update an Order based on its ID", "Delete an Order based on its ID", "Back to main menu"};
        String[] option = {"List all Products", "List all Customers", "Search a Customer based on his/her ID", "Add a Customer", "Update a Customer", "Save Customers to the file, named customers.txt", "List all Orders in ascending order of Customer name", "List all pending Orders", "Add an Order", "Update an Order", "Save Orders to file, named orders.txt", "Quit"};
        menu.add(option);
        orderMenu.add(subOption);
        sm.readCustomerFile();
        sm.readOrderFile();
        sm.readProductFile();
        sm.startAutoSaving();
        boolean mainCheck = true;
        do {
            int choice = menu.printMenu();
            switch(choice){
                case 1:
                    sm.traverserProduct();
                    break;
                case 2:
                    sm.traverserCustomer();
                    break;
                    
                case 3:
                    sm.searchCustomer();
                    break;
                    
                case 4:
                    sm.createCustomer();
                    break;
                    
                case 5:
                    sm.updateCustomer();
                    break;
                    
                case 6:
                    sm.saveCustomerToFile();
                    break;
                    
                case 7:
                    sm.traverserAscOrder();
                    break;
                    
                case 8: 
                    sm.traverserPendingOrder();
                    break;
                    
                case 9:
                    sm.createOrder();
                    break;
                    
                case 10:
                    boolean subCheck = true;
                    do{
                        int subChoice = orderMenu.printMenu();
                        switch(subChoice){
                            case 1:
                                sm.updateOrder();
                                break;
                                
                            case 2:
                                sm.deleteOrder();
                                break;
                                
                            case 3:
                                subCheck = false;
                                break;
                        }
                        
                    }while(subCheck);
                    break;
                    
                case 11:
                    sm.saveOrderToFile();
                    break;
                    
                case 12:
                    mainCheck = false;
                    sm.stopAutoUpdate();
                    break;
            }
        } while (mainCheck);

    }
}
