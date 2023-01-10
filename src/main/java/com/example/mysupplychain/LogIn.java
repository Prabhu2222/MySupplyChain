package com.example.mysupplychain;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.sql.ResultSet;

public class LogIn {

    public static  boolean customerLogin(String email,String password){
        String query=String.format("select * from customer where email='%s' and password='%s'",email,password);
        try{
            DatabaseConnection dc=new DatabaseConnection();
            ResultSet myres= dc.getQueryTable(query);
            if (myres!=null && myres.next()){
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private static byte[] getSHA(String input){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String getEncryptedPassword(String password){
        String encryptedPassword="";
        try{
            BigInteger number=new BigInteger(1,getSHA(password));
            StringBuilder hexString=new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public static void main(String[] args) {
        LogIn ln=new LogIn();

        System.out.println(ln.getEncryptedPassword("abc@123"));
    }
}
