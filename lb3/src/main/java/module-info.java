module com.example.lb3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;

    opens com.example.lb3 to javafx.fxml;
    exports com.example.lb3;
    exports com.example.lb3.controllers;
    opens com.example.lb3.controllers to javafx.fxml;
    exports com.example.lb3.models;
    opens com.example.lb3.models to javafx.fxml;
}