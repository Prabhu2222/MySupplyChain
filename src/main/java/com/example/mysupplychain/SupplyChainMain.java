package com.example.mysupplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChainMain extends Application {
    public static final int width=700,height=600,headerBar=60;
    Pane root;
   static  Pane bodyPane=new Pane();
    ProductDetails productDetails=new ProductDetails();
    Button globalLogOutButton;
    TextField searchTextField;
    Label customerInfoLabel=new Label();
    Pane createContent(){
        root=new Pane();
        root.setPrefSize(width,height+2*headerBar);


        bodyPane.setPrefWidth(width);
        bodyPane.setPrefHeight(height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(logInPage());
        bodyPane.setStyle("-fx-background-color:yellow");
        root.getChildren().addAll(headerBarTop(),signUpFooterBar(),bodyPane);


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
        globalLogOutButton =new Button("LogOut");
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
        pane.add(globalLogOutButton,2,0);
        pane.add(customerInfoLabel,3,0);


       globalLogOutButton.setDisable(true);
       globalLogOutButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               globalLogOutButton.setDisable(true);
//               bodyPane.setPrefHeight(headerBar+height);
               root.getChildren().add(signUpFooterBar());
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
                    globalLogOutButton.setDisable(false);
//                    bodyPane.setPrefHeight(height);
                    root.getChildren().add(footerBar());
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
    GridPane signUpFooterBar(){
       GridPane pane=new GridPane();
       Label signUpLabel=new Label("Not a Member ? Sign up");
       Button signUpButton=new Button("Sign Up");
       signUpButton.setTranslateX(36);
       signUpButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               bodyPane.getChildren().clear();
               bodyPane.getChildren().add(signUpPane());
               bodyPane.setPrefHeight(height+headerBar);
           }
       });

       pane.add(signUpLabel,0,0);
       pane.add(signUpButton,0,1);
       pane.setPrefWidth(width);
       pane.setPrefHeight(headerBar);
       pane.setTranslateY(headerBar+height);
       pane.setVgap(5);
       pane.setStyle("-fx-background-color:grey");
//       pane.setGridLinesVisible(true);
       pane.setAlignment(Pos.CENTER);

       return pane;
    }
    public  Pane signUpPane(){
        GridPane pane=new GridPane();

        Label firstnameLabel=new Label("First Name : ");
        TextField firstNameTextField=new TextField();
        firstNameTextField.setPromptText("first name");
        firstNameTextField.setTooltip(new Tooltip("* Mandatory Field"));

        Label lastnameLabel=new Label("Last Name : ");
        TextField lastNameTextField=new TextField();
        lastNameTextField.setPromptText("last name");

        Label addressLabel=new Label("Address : ");
        TextField addressTextField=new TextField();
        addressTextField.setPromptText("Communication address");


        Label mobileNumberLabel=new Label("Mobile Number : ");
        TextField mobileNumberTextField=new TextField();
        mobileNumberTextField.setPromptText("10 digit mobile number");
        mobileNumberTextField.setTooltip(new Tooltip("* Mandatory Field"));

        Label emailLabel=new Label("Email : ");
        TextField emailTextField=new TextField();
        emailTextField.setPromptText("xyz@gmail.com");
        emailTextField.setTooltip(new Tooltip("* Mandatory Field"));

        Label passwordLabel=new Label("Password : ");
        PasswordField passwordField=new PasswordField();
        passwordField.setTooltip(new Tooltip("* Mandatory Field"));

        Button registerButton=new Button("Register");
        Button backButton=new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.setPrefHeight(height);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(logInPage());

            }
        });


        pane.add(firstnameLabel,0,0);
        pane.add(firstNameTextField,1,0);
        pane.add(lastnameLabel,0,1);
        pane.add(lastNameTextField,1,1);
        pane.add(addressLabel,0,2);
        pane.add(addressTextField,1,2);
        pane.add(mobileNumberLabel,0,3);
        pane.add(mobileNumberTextField,1,3);
        pane.add(emailLabel,0,4);
        pane.add(emailTextField,1,4);
        pane.add(passwordLabel,0,5);
        pane.add(passwordField,1,5);
        pane.add(registerButton,1,6);
        pane.add(backButton,1,7);



        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.setPrefWidth(SupplyChainMain.width);
        pane.setPrefHeight(SupplyChainMain.height);
        pane.setTranslateY(SupplyChainMain.headerBar);
        return pane;
    }

}