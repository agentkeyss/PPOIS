package com.example.lb3.models;

import org.apache.commons.codec.digest.DigestUtils;

public class Account {
    private String username;
    private String email;
    private String password;
    private boolean isWithAddingAccess;

    public Account(String username, String email, String password, boolean isWithAddingAccess) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isWithAddingAccess = isWithAddingAccess;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isWithAddingAccess() {
        return isWithAddingAccess;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
