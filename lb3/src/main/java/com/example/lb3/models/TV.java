package com.example.lb3.models;

import com.example.lb3.AccessoryType;

public class TV extends Product{
    private double displayPermission;

    public TV(String name, double price, double displayPermission) {
        super(name, price);
        this.displayPermission = displayPermission;
    }

    public double getDisplayPermission() {
        return displayPermission;
    }

    public void setDisplayPermission(double displayPermission) {
        this.displayPermission = displayPermission;
    }
}
