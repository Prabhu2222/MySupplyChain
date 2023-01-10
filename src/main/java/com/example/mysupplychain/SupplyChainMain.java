package com.example.mysupplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChainMain extends Application {
    public static final int width=700,height=600,headerBar=60;
    Pane root;
   static  Pane bodyPane=new Pane();
    ProductDetails productDetails=new ProductDetails();
    Button globalLogOut=new Button("LogOut");
    TextField searchTextField;
    Label customerInfoLabel=new Label();
    Pane createContent(){
        root=new Pane();
        root.setPrefSize(width,height+2*headerBar);


        bodyPane.setPrefWidth(width);
        bodyPane.setPrefHeight(height+headerBar);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(logInPage());
        bodyPane.setStyle("-fx-background-color:yellow");
        root.getChildren().addAll(headerBarTop(),footerBar(),bodyPane);


        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SupplyChainMain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
   GridPane headerBarTop(){
       GridPane pane=new GridPane();
       searchTextField=new TextField();
        searchTextField.setEditable(false);
        Button searchButton=new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName=searchTextField.getText();
                if(!productName.isBlank() || !productName.isEmpty()){
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
                }
            }
        });


        pane.add(searchTextField,0,0);
        pane.add(searchButton,1,0);
        pane.add(globalLogOut,2,0);
        pane.add(customerInfoLabel,3,0);


       globalLogOut.setDisable(true);
       globalLogOut.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               globalLogOut.setDisable(true);
               bodyPane.setPrefHeight(headerBar+height);
               customerInfoLabel.setText("Welcome User");
               bodyPane.getChildren().clear();
               bodyPane.getChildren().add(logInPage());
           }
       });
       customerInfoLabel.setText("Welcome User");


       pane.setAlignment(Pos.CENTER);
       pane.setHgap(5);
        pane.setPrefWidth(width);
        pane.setPrefHeight(headerBar);
        pane.setStyle("-fx-background-color:skyblue");


        return pane;
    }

    GridPane logInPage(){
        GridPane loginGrid=new GridPane();
        Label emailLabel=new Label("Email:");
        TextField emailTextField=new TextField();
        Label passwordLabel=new Label("Password:");
        PasswordField passwordField=new PasswordField();
        Button loginButton=new Button("logIn");
        Label loginLabel=new Label("i am log in label");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailTextField.getText();
                String password=passwordField.getText();
                if(email.isEmpty()||email.isBlank()){
                    loginLabel.setText("please enter a valid email");
                    return;
                }
                else if(password.isEmpty()||password.isBlank()){
                    loginLabel.setText("please enter a valid password");
                    return;
                }
                else if(LogIn.customerLogin(email,password)){
                    globalLogOut.setDisable(false);
                    bodyPane.setPrefHeight(height);
                    String name=emailTextField.getText();
                    name=name.substring(0,name.indexOf('@'));
                    customerInfoLabel.setText("Welcome : "+name);
                    searchTextField.setEditable(true);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }
                else {
                    loginLabel.setText("Incorrect email or password");
                    return;
                }

            }
        });
        loginLabel.setText("Please Login to view Our Products");



        loginGrid.add(emailLabel,0,0);
        loginGrid.add(emailTextField,1,0);
        loginGrid.add(passwordLabel,0,1);
        loginGrid.add(passwordField,1,1);
        loginGrid.add(loginButton,0,2);
        loginGrid.add(loginLabel,1,2);


        loginGrid.setHgap(5);
        loginGrid.setVgap(5);
        loginGrid.setPrefWidth(bodyPane.getPrefWidth());
        loginGrid.setPrefHeight(bodyPane.getPrefHeight());
        loginGrid.setAlignment(Pos.CENTER);


        return  loginGrid;
    }
    GridPane footerBar(){
        GridPane pane=new GridPane();
        Button addToCrt=new Button("Add to Cart");
        Button buyNow=new Button("Buy Now");
        Label buyNowLabel=new Label();
        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct=productDetails.getSelectedProduct();
                if(Order.placeOrder("angad"+"@gmail.com",selectedProduct)){
                    buyNowLabel.setText("Ordered");
                }
            }
        });

        pane.add(addToCrt,1,0);
        pane.add(buyNow,2,0);
        pane.add(buyNowLabel,2,1);
        pane.setPrefWidth(width);
        pane.setPrefHeight(headerBar);
        pane.setStyle("-fx-background-color:green");
        pane.setHgap(5);
        pane.setTranslateY(headerBar+height);
        pane.setAlignment(Pos.CENTER);


        return pane;
    }

}