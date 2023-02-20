/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.util;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnataArisa
 * @param <T>
 */
public class FileMangement<T> implements IFileManage<T> {

    private String filePath;
    private List<T> dataList = new ArrayList();

    @Override
    public void setPath(String path) {
        this.filePath = path;
    }

    @Override
    public List<T> getList() {
        return this.dataList;
    }

    @Override
    public boolean loadFromFile() {
        Handle handle = new Handle();
        File f = new File(filePath);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String data;
            while ((data = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data, "|");
                T temp = handle.fileToObject(st);
                if(temp != null) dataList.add(temp);
            }
            return true;
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean uploadToFile() {
        File file = new File(filePath);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (T data : dataList) {
                bw.write(data + "\n");
            }
            return true;
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public void setList(List<T> list) {
        this.dataList = list;
    }
    
    private class Handle{
        public T fileToObject(StringTokenizer a){
            if(a.countTokens() == 4){
                Customer temp = new Customer(a.nextToken(),a.nextToken(),a.nextToken(),a.nextToken());
                return (T)temp;
            }
            if(a.countTokens() == 5){
                Product temp = new Product(a.nextToken(),a.nextToken(),a.nextToken(),a.nextToken(),Integer.parseInt(a.nextToken()));
                return (T)temp;
            }
            if(a.countTokens() == 6){
                Order temp = new Order(a.nextToken(),a.nextToken(),a.nextToken(),Integer.parseInt(a.nextToken()),a.nextToken(),Boolean.parseBoolean(a.nextToken()));
                return (T) temp;
            }
            return null;
        }
    }

}
