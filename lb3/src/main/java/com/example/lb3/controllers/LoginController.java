package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Account;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public PasswordField password;

    @FXML
    public TextField email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShopApplication.setCurrentAccount(null);
        ShopApplication.setCartProducts(null);
    }

    @FXML
    public boolean openRegistrationPage(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/registration_form.fxml"));
            Scene registrationScene = new Scene(root);
            Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

            stage.setScene(registrationScene);
            stage.show();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    public boolean loginProceed(ActionEvent actionEvent) {
        try {
            Account currentUser = Database.getUserInDB(email.getText(), password.getText());
            if (currentUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Данного аккаунта не существует!");

                alert.showAndWait();

                return false;
            } else {
                ShopApplication.setCurrentAccount(currentUser);
                ShopApplication.setCartProducts(Database.getCartArrayListInDB(Database.getAccountID(currentUser)));
                Alert alert = new Alert(Alert.AlertType.NONE, "Вы вошли в свой аккаунт!", ButtonType.OK);
                alert.showAndWait();
                Parent root = null;
                if (currentUser.isWithAddingAccess()) {
                    root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_employee_form.fxml"));
                } else {
                    root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_user_form.fxml"));
                }
                Scene productsScene = new Scene(root);
                Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

                stage.setScene(productsScene);
                stage.show();

                return true;
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

//    private boolean isCorrectEmail(String email) {
//        return email.matches("\\w+@\\w+.(com|ru|by)");
//    }
//
//    private boolean isCorrectPassword(String password) {
//        return password.length() >= 8;
//    }
}
