package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ShopApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @FXML
    public Label usernameLabel;

    @FXML
    public PasswordField oldPassword;

    @FXML
    public PasswordField newPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(ShopApplication.getCurrentAccount().getUsername());
    }

    @FXML
    public boolean openProductsForm(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/products_user_form.fxml"));
            Scene userProductsScene = new Scene(root);
            Stage stage = (Stage) ((Node) (mouseEvent.getSource())).getScene().getWindow();

            stage.setScene(userProductsScene);
            stage.show();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @FXML
    public boolean changePassword() {
        try {
            if (DigestUtils.sha256Hex(ShopApplication.getCurrentAccount().getPassword()).equals(DigestUtils.sha256Hex(oldPassword.getText()))) {
                Database.updatePassword(newPassword.getText());
                Alert alert = new Alert(Alert.AlertType.NONE, "Вы обновили свой пароль!", ButtonType.OK);
                alert.showAndWait();

                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Неверный старый пароль!");

                alert.showAndWait();

                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
