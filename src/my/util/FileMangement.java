/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.util;

import com.model.IFileObject;
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
public class FileMangement<T extends IFileObject> implements IFileManage<T> {

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
        File f = new File(filePath);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String data;
            while ((data = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data, "|");
                add((T) new Object(), st);
            }
            return true;
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
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
                bw.write(data.toString() + "\n");
            }
            return true;
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileMangement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     *
     * @param data
     */
    private void add(T data, StringTokenizer dataL) {
        data.fileToObject(dataL);
        dataList.add(data);
    }

    @Override
    public void setList(List<T> list) {
        this.dataList = list;
    }

}
