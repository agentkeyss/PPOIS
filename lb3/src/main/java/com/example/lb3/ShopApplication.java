package com.example.lb3;

import com.example.lb3.models.Account;
import com.example.lb3.models.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopApplication extends Application {
    private static Account currentAccount = null;
    private static ArrayList<Product> cartProducts = null;

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        ShopApplication.currentAccount = currentAccount;
    }

    public static ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public static void setCartProducts(ArrayList<Product> cartProducts) {
        ShopApplication.cartProducts = cartProducts;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ShopApplication.class.getResource("/fxml/login_form.fxml"));
        Scene loginScene = new Scene(root);

        stage.setResizable(false);
        stage.setTitle("SoftBerries");
        stage.setScene(loginScene);
        stage.getIcons().add(new Image("D:\\PPOIS\\lb3\\src\\main\\resources\\images\\shop_icon.png"));
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        Database.init();
        launch();
    }
}
