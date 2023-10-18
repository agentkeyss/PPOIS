package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.AccessoryType;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProductsTest extends ApplicationTest {
    private final Account testAccount = new Account("test", "test@gmail.com", "test1234", false);
    private final List<Product> testProducts = List.of(new TV("Samsung", 2000, 10),
            new Smartphone("iPhone", 2000, 13, 4, 5000, 5, "A15 Bionic"),
            new Tablet("iPad", 3000, 13, 8, 7000, 7, "A15 Bionic"),
            new Computer("iMac", 5000, "PCIe", 4, 10, "Intel Core 8"),
            new Accessory("AppleWatch", 300, AccessoryType.SMARTWATCH, "Black"));

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @BeforeEach
    public void setUp() throws Exception {
        Database.init();
        ApplicationTest.launch(ShopApplication.class);
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException, SQLException {
        Database.clearDatabase();
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    private void loginAsTestAccount(FxRobot robot) {
        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#username");
        robot.write(testAccount.getUsername());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#registrationButton");
        robot.clickOn("OK");
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#loginButton");
        robot.clickOn("OK");
    }

    @Test
    public void addToCartTest() throws SQLException {
        for (Product product : testProducts){
            Database.addProduct(product);
        }
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        for (Product product : testProducts){
            clickOn(product.getName());
            clickOn("#addToCartButton");
        }
        clickOn("#cartImage");

        for (Product product : testProducts){
            FxAssert.verifyThat(product.getName(), NodeMatchers.isVisible());
        }
    }

    @Test
    public void openAccountTest() {
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        clickOn("#accountImage");

        FxAssert.verifyThat("#changePasswordButton", NodeMatchers.isVisible());
    }

    @Test
    public void openCartTest() {
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        clickOn("#cartImage");

        FxAssert.verifyThat("Ваша корзина", NodeMatchers.isVisible());
    }

    @Test
    public void openLoginFormTest() {
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        clickOn("#exitButton");

        FxAssert.verifyThat("#loginButton", NodeMatchers.isVisible());
    }
}
