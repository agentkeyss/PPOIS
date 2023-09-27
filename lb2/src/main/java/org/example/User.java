package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User extends Person{
    public User() {}

    public User(String name, String surname, String phone_number, Gender gender, LocalDate birthDate,
                String email, String password) {
        super(name, surname, phone_number, gender, birthDate, email, password);
    }

    public boolean signUp() {
        this.registrationDateTime = LocalDateTime.now();

        try {
            Database.addUser(this);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean signIn(String phoneNumberOrEmail, String password) throws SQLException {
        User user = Database.getUserByEmail(phoneNumberOrEmail, password);
        if (user == null) user = Database.getUserByPhoneNumber(phoneNumberOrEmail, password);
        if (user == null) return false;

        this.name = user.name;
        this.surname = user.surname;
        this.birthDate = user.birthDate;
        this.hashedPassword = user.hashedPassword;
        this.phoneNumber = user.phoneNumber;
        this.gender = user.gender;
        this.email = user.email;
        this.registrationDateTime = user.registrationDateTime;
        this.id = user.id;

        return true;
    }

    public boolean makeOrder(int orderID){
        try {
            Database.setCustomerToOrder(orderID, this.id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean deleteOrder(int orderID) {
        try {
            Database.deleteCustomerFromOrder(orderID);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean deleteAccount() {
        try {
            Database.deleteUser(this.id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }



}
