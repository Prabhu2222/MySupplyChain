package com.example.mysupplychain;

public class Order {
    public static boolean placeOrder(String email,Product product){

        int rowCount=0;
        try{
            DatabaseConnection databaseConnection=new DatabaseConnection();
            String query=String.format("insert into orders(customer_id,product_id) value((select customer_id from customer where email='%s'),%s)",email,product.getProduct_id());
            rowCount=databaseConnection.executeUpdateQuery(query);

        }catch(Exception e){
            e.printStackTrace();
        }



        return rowCount!=0;
    }
}
