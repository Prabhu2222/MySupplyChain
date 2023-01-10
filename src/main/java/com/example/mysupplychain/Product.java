package com.example.mysupplychain;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private IntegerProperty product_id;
    private StringProperty product_name;
    private DoubleProperty product_price;

    public Product(int product_id, String product_name, Double product_price) {
        this.product_id = new SimpleIntegerProperty(product_id);
        this.product_name = new SimpleStringProperty(product_name);
        this.product_price = new SimpleDoubleProperty(product_price);
    }

    public String getProduct_name() {
        return product_name.get();
    }

    public double getProduct_price() {
        return product_price.get();
    }

    public int getProduct_id(){
      return   product_id.get();
    }

    public static ObservableList<Product> getAllProducts(){
        ObservableList<Product> list= FXCollections.observableArrayList();
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query="select * from product";
        ResultSet rs=databaseConnection.getQueryTable(query);
        try{
            while (rs.next()){
                list.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getDouble("price")));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;


    }
    public static ObservableList<Product> getProductsByName(String productName){
        ObservableList<Product> list= FXCollections.observableArrayList();
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("select * from product where lower(name) like '%%%s%%'",productName.toLowerCase());
        ResultSet rs=databaseConnection.getQueryTable(query);
        try{
            while (rs.next()){
                list.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getDouble("price")));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;


    }


}
