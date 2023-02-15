/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.util.List;

/**
 *
 * @author AnataArisa
 */
public interface IDAO<T> {
    //CRUD
    public List<T> getAll();
    public boolean create(T data);
    public T read(T data);
    public T read(String ID);
    public boolean update(T data);
    public T delete(T data);
    public T delete(String ID);
    public void traverser();
}
