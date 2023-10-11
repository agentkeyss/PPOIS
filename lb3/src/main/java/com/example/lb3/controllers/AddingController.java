package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ProductType;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddingController implements Initializable {
    @FXML
    public ComboBox<ProductType> typesComboBox;
    @FXML
    public TextField productPrice;
    @FXML
    public TextField productName;
    @FXML
    public Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(ShopApplication.getCurrentAccount().getUsername());

        ObservableList<ProductType> types = FXCollections.observableArrayList(Arrays.asList(ProductType.values()));
        typesComboBox.setItems(types);
    }

    @FXML
    public boolean openProductsForm(MouseEvent mouseEvent){
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_employee_form.fxml"));
            Scene employeeProductsScene = new Scene(root);
            Stage stage = (Stage) ((Node) (mouseEvent.getSource())).getScene().getWindow();

            stage.setScene(employeeProductsScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean addingProceed(ActionEvent actionEvent){
        try {
            Product product = new Product(productName.getText(), Double.parseDouble(productPrice.getText()), typesComboBox.getValue());
            Database.addProduct(product);
            productName.clear();
            productPrice.clear();
            typesComboBox.valueProperty().set(null);

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
