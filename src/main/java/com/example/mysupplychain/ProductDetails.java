package com.example.mysupplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductDetails {
    public TableView<Product> productTable;

    public  VBox getAllProducts(){
        Label productDetailsLabel=new Label("Products Details");

        TableColumn<Product,Integer> firstCol=new TableColumn<>("Product_id");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        TableColumn<Product,String> secondCol=new TableColumn<>("Product_name");
        secondCol.setCellValueFactory(new PropertyValueFactory<>("product_name"));

        TableColumn<Product,Integer> thirdCol=new TableColumn<>("Product_price");
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("product_price"));

        ObservableList<Product>list =Product.getAllProducts();
        productTable=new TableView<>();
        productTable.setItems(list);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(firstCol,secondCol,thirdCol);

        VBox vb=new VBox();
        vb.setPrefHeight(SupplyChainMain.height);
        vb.setPrefWidth(SupplyChainMain.width);
        vb.getChildren().addAll(productDetailsLabel,productTable);
        productTable.setPrefHeight(vb.getPrefHeight());
        productTable.setPrefWidth(vb.getPrefWidth());
        return vb;


    }
    public VBox getProductsByName(String productName){
        Label productDetailsLabel=new Label("Products Details");

        TableColumn<Product,Integer> firstCol=new TableColumn<>("Product_id");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        TableColumn<Product,String> secondCol=new TableColumn<>("Product_name");
        secondCol.setCellValueFactory(new PropertyValueFactory<>("product_name"));

        TableColumn<Product,Integer> thirdCol=new TableColumn<>("Product_price");
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("product_price"));

        ObservableList<Product>list =Product.getProductsByName(productName);
        productTable=new TableView<>();
        productTable.setItems(list);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(firstCol,secondCol,thirdCol);

        VBox vb=new VBox();
        vb.setPrefHeight(SupplyChainMain.height);
        vb.setPrefWidth(SupplyChainMain.width);
        vb.getChildren().addAll(productDetailsLabel,productTable);
        productTable.setPrefHeight(vb.getPrefHeight());
        productTable.setPrefWidth(vb.getPrefWidth());
        return vb;


    }
    public Product getSelectedProduct(){
        try{
            Product selectedProduct=productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
