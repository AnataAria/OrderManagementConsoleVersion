/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import my.util.Validate;

/**
 *
 * @author AnataArisa
 */
public class Menu {
    private ArrayList<String> menu = new ArrayList();
    
    public void add(String [] data){
        menu.addAll(Arrays.asList(data));
    }
    public void add(ArrayList<String > data){
        menu.addAll(data);
    }
    
    public int printMenu(){
        for(int i = 0; i < menu.size();i ++){
            System.out.println((i + 1) + ". " + menu.get(i));
        }
        int choice = Validate.intValidation("Please choice function: ", 1, menu.size());
        return choice;
    }
}
