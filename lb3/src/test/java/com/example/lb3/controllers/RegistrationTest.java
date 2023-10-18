package com.example.lb3.controllers;

import com.example.lb3.Database;
import com.example.lb3.ShopApplication;
import com.example.lb3.models.Account;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.NodeQuery;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

class RegistrationTest extends ApplicationTest {

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
    public void firstRegistrationTest() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#username");
        robot.write(testAccount.getUsername());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#registrationButton");

        FxAssert.verifyThat("Регистрация прошла успешно!", NodeMatchers.isVisible());
        robot.clickOn("OK");
    }

    @Test
    public void secondRegistrationTest() {
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

        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#username");
        robot.write(testAccount.getUsername());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#registrationButton");

        FxAssert.verifyThat("Аккаунт с такой электронной почтой уже существует!", NodeMatchers.isVisible());
        robot.clickOn("OK");
    }

    @Test
    public void shortPasswordRegistrationTest() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#username");
        robot.write(testAccount.getUsername());
        robot.clickOn("#password");
        robot.write("1234");
        robot.clickOn("#email");
        robot.write(testAccount.getEmail());
        robot.clickOn("#registrationButton");

        FxAssert.verifyThat("Введен слишком короткий пароль(минимум 8 символов)!", NodeMatchers.isVisible());
        robot.clickOn("OK");
    }

    @Test
    public void invalidEmailRegistrationTest() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#username");
        robot.write(testAccount.getUsername());
        robot.clickOn("#password");
        robot.write(testAccount.getPassword());
        robot.clickOn("#email");
        robot.write("1234");
        robot.clickOn("#registrationButton");

        FxAssert.verifyThat("Введена неверная электронная почта!", NodeMatchers.isVisible());
        robot.clickOn("OK");
    }

    @Test
    public void openLoginFormTest() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#registrationHyperLink");
        robot.clickOn("#loginHyperLink");

        FxAssert.verifyThat("#loginButton", LabeledMatchers.hasText("Войти"));
    }
}