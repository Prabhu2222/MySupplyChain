module com.example.mysupplychain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mysupplychain to javafx.fxml;
    exports com.example.mysupplychain;
}