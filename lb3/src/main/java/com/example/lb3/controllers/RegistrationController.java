package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public TextField email;

    @FXML
    public URL location;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShopApplication.setCurrentAccount(null);
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
    public boolean registrationProceed(ActionEvent actionEvent) {
        try {
            Account user = new Account(username.getText(), email.getText(), password.getText(), false);
            if (Database.isAccountExist(user)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Аккаунт с такой электронной почтой уже существует!");
                alert.showAndWait();

                return false;
            } else if (!isCorrectEmail(user.getEmail())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введена неверная электронная почта!");

                alert.showAndWait();

                return false;
            } else if (!isCorrectPassword(user.getPassword())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введен слишком короткий пароль(минимум 8 символов)!");

                alert.showAndWait();

                return false;
            } else {
                Database.addAccount(user);
                Alert alert = new Alert(Alert.AlertType.NONE, "Регистрация прошла успешно!", ButtonType.OK);
                alert.showAndWait();
                openLoginForm(actionEvent);

                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean isCorrectEmail(String email) {
        return email.matches("\\w+@\\w+.(com|ru|by)");
    }

    private boolean isCorrectPassword(String password) {
        return password.length() >= 8;
    }
}
