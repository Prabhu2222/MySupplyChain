package com.example.mysupplychain;
import java.sql.*;


public class DatabaseConnection {
    private static String Url="jdbc:mysql://localhost:3306/my_supply_chain_tables";
    private static String userName="root";
    private static String password="root/root";

   public Statement getStatement(){
       Statement statement=null;
        Connection conn;
        try{
            conn=DriverManager.getConnection(Url,userName,password);
             statement=conn.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
        return statement;
    }


    public ResultSet getQueryTable(String query){
        Statement statement_obj=getStatement();
        try{
            ResultSet rs=statement_obj.executeQuery(query);
            return rs;

        }catch(Exception e){
         e.printStackTrace();
        }
        return null;
    }
    public int executeUpdateQuery(String query){
        Statement statement_obj=getStatement();
        try{
            return statement_obj.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DatabaseConnection dc=new DatabaseConnection();
        String query="select * from customer";
        ResultSet rs=dc.getQueryTable(query);
        try{
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+
                rs.getString(2)+" "+rs.getString(3)+" "+
                        rs.getString(4)+" "+rs.getString(5)+" "+
                        rs.getString(6)+" "+rs.getString(7));
            }

        }catch(Exception e ){
            e.printStackTrace();

        }

    }
}
