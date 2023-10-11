package com.example.lb3.controllers;

import com.example.lb3.Database;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    public Label usernameLabel;
    @FXML
    public TableView<Product> productsTable;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, Double> priceColumn;
    @FXML
    public TableColumn<Product, String> typeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(ShopApplication.getCurrentAccount().getUsername());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductType().toString()));

        ObservableList<Product> products = null;
        try {
            products = FXCollections.observableArrayList(Database.getProductsArrayListInDB());
            productsTable.setItems(products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateTable() {
        ObservableList<Product> products = null;
        try {
            products = FXCollections.observableArrayList(Database.getProductsArrayListInDB());
            productsTable.setItems(products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public boolean openCartForm(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/cart_form.fxml"));
            Scene cartScene = new Scene(root);
            Stage stage = (Stage) ((Node) (mouseEvent.getSource())).getScene().getWindow();

            stage.setScene(cartScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean openLoginForm(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/login_form.fxml"));
            Scene loginScene = new Scene(root);
            Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

            stage.setScene(loginScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean openAccountForm(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/account_form.fxml"));
            Scene accountScene = new Scene(root);
            Stage stage = (Stage) ((Node) (mouseEvent.getSource())).getScene().getWindow();

            stage.setScene(accountScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    private boolean addToCartHandler(ActionEvent actionEvent) {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) return false;
            Database.setAccountIDForProduct(ShopApplication.getCurrentAccount(), selectedProduct);
            ShopApplication.setCartProducts(Database.getCartArrayListInDB(Database.getAccountID(ShopApplication.getCurrentAccount())));
            updateTable();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean openAddingForm(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/product_adding_form.fxml"));
            Scene addingScene = new Scene(root);
            Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

            stage.setScene(addingScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}
