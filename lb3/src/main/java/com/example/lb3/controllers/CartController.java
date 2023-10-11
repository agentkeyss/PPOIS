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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CartController implements Initializable {
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

        ObservableList<Product> products = FXCollections.observableArrayList(ShopApplication.getCartProducts());
        productsTable.setItems(products);
    }

    @FXML
    public boolean openProductsForm(MouseEvent mouseEvent){
        try {
            Parent root = null;
            if (ShopApplication.getCurrentAccount().isWithAddingAccess()) {
                root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_employee_form.fxml"));
            } else {
                root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_user_form.fxml"));
            }
            Scene userProductsScene = new Scene(root);
            Stage stage = (Stage) ((Node) (mouseEvent.getSource())).getScene().getWindow();

            stage.setScene(userProductsScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean openLoginForm(ActionEvent actionEvent){
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
    public boolean openAccountForm(MouseEvent mouseEvent){
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
    public boolean deleteFromCartHandler(ActionEvent actionEvent){
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) return false;
            Database.clearAccountIDForProduct(selectedProduct);
            ShopApplication.setCartProducts(Database.getCartArrayListInDB(Database.getAccountID(ShopApplication.getCurrentAccount())));
            updateTable();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void updateTable() {
        ObservableList<Product> products = null;
        try {
            products = FXCollections.observableArrayList(Database.getCartArrayListInDB(
                    Database.getAccountID(ShopApplication.getCurrentAccount())));
            productsTable.setItems(products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
