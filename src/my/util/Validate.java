/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author AnataArisa
 */
public class Validate {

    private static Scanner sc;

    public static String regexValidate(String regex, String message) {
        String scan;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
        } while (!scan.matches(regex));
        return scan;
    }
    
    public static String regexValidateCanSkip(String regex, String message){
        String scan ;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
        } while (!scan.isEmpty() && !scan.matches(regex));
        return scan;
    }

    public static String phoneValidation(String message) {//Vietnam phone
        return regexValidate("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message);
    }
    
    public static String phoneValidationCanSkip(String message){
        return regexValidateCanSkip("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message);
    }
    public static boolean booleanValidation(String message) {
        String scan;
        do {
            System.out.println(message + "(T | F | True | False)");
            sc = new Scanner(System.in);
            scan = sc.nextLine();
            if (scan.toUpperCase().charAt(0) == 'T' || scan.toUpperCase().equals("TRUE")) {
                return true;
            }
            if (scan.toUpperCase().charAt(0) == 'F' || scan.toUpperCase().equals("FALSE")) {
                return false;
            }
            System.out.println("Your input isn't valid, please try again!!!");
        } while (true);
    }

    public static String stringValidation(String message) {
        String scan;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
        } while (scan == null || scan.isEmpty());
        return scan;
    }
    
    public static String stringValidationCanSkip(String message) {
        String scan;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
        } while (scan == null || !scan.isEmpty());
        return scan;
    }

    public static int intValidation(String message, int min, int max) {
        String scan;
        boolean check = false;
        int result = 0;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
            check = false;
            try {
                result = Integer.parseInt(scan);
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Your input is not valid, please try again !!!");
                check = true;
            }
        } while (check);
        return result;
    }

    public static int intValidation(String message, int min) {
        return intValidation(message, min, Integer.MAX_VALUE);
    }
    
    public static int intValidationCanSkip(String message,int  min, int max){
        String scan;
        boolean check = false;
        int result = 0;
        do {
            System.out.println(message);
            sc = new Scanner(System.in);
            scan = sc.nextLine();
            if(scan.isEmpty()) return -1;
            try {
                result = Integer.parseInt(scan);
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Your input is not valid, please try again !!!");
                check = true;
            }
        } while (check);
        return result;
    }

    public static String dateTimeValidate(String mesage, String format) {
        boolean check = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        String date;
        do {
            System.out.println(mesage);
            sc = new Scanner(System.in);
            date = sc.nextLine();
            try {
                LocalDate.parse(date, dtf);
                check = false;
            }catch(DateTimeParseException e){
                System.out.println("Your input date is not valid, please try again !!!");
                check = true;
            }
        } while (check);
        return date;
    }
    
    public static String dateTimeValidateCanSkip(String mesage, String format) {
        boolean check = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        String date;
        do {
            System.out.println(mesage);
            sc = new Scanner(System.in);
            date = sc.nextLine();
            if(date.isEmpty()) return null;
            try {
                LocalDate.parse(date, dtf);
                check = false;
            }catch(DateTimeParseException e){
                System.out.println("Your input date is not valid, please try again !!!");
                check = true;
            }
        } while (check);
        return date;
    }
}
