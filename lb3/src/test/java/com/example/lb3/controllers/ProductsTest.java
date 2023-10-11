package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ProductType;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Account;
import com.example.lb3.models.Product;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class ProductsTest extends ApplicationTest {
    private final Account testAccount = new Account("test", "test@gmail.com", "test1234", false);
    private final Product testProduct = new Product("iMac", 2000, ProductType.COMPUTER);

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
        Database.addProduct(testProduct);
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        clickOn(testProduct.getName());
        clickOn("#addToCartButton");
        clickOn("#cartImage");

        FxAssert.verifyThat("iMac", NodeMatchers.isVisible());
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
    public void openLoginForm() {
        FxRobot robot = new FxRobot();
        loginAsTestAccount(robot);
        clickOn("#exitButton");

        FxAssert.verifyThat("#loginButton", NodeMatchers.isVisible());
    }
}
