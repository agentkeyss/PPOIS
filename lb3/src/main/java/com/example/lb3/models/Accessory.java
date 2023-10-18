package com.example.lb3.models;

import com.example.lb3.AccessoryType;

public class Accessory extends Product{
    private AccessoryType accessoryType;
    private String color;

    public Accessory(String name, double price, AccessoryType accessoryType, String color) {
        super(name, price);
        this.accessoryType = accessoryType;
        this.color = color;
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
