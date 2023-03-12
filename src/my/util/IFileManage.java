/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package my.util;
import java.util.List;

/**
 *
 * @author AnataArisa
 * @param <T>
 */
public interface IFileManage<T> {
    public void setPath(String path);
    public List<T> getList();
    public void setList(List<T> list);
    public boolean loadFromFile();
    public boolean uploadToFile();
    public void clear();
}
