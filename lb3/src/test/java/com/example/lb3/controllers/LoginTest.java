package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Account;
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
import java.util.concurrent.TimeoutException;

public class LoginTest extends ApplicationTest{
    private final Account testAccount = new Account("test", "test@gmail.com", "test1234", false);

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

    @Test
    public void successfulLoginTest() {
        FxRobot robot = new FxRobot();
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

        FxAssert.verifyThat("Вы вошли в свой аккаунт!", NodeMatchers.isVisible());
        robot.clickOn("OK");

    }

    @Test
    public void unsuccessfulLoginTest() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#loginButton");

        FxAssert.verifyThat("Данного аккаунта не существует!", NodeMatchers.isVisible());
        robot.clickOn("OK");

    }
}
