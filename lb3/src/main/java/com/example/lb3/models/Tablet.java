package com.example.lb3.models;

import com.example.lb3.AccessoryType;

public class Tablet extends Product{
    private int megapixelsCount;
    private int coreCount;
    private double batteryCapacity;
    private double displayPermission;
    private String processor;

    public Tablet(String name, double price, int megapixelsCount, int coreCount, double batteryCapacity, double displayPermission, String processor) {
        super(name, price);
        this.megapixelsCount = megapixelsCount;
        this.coreCount = coreCount;
        this.batteryCapacity = batteryCapacity;
        this.displayPermission = displayPermission;
        this.processor = processor;
    }

    public int getMegapixelsCount() {
        return megapixelsCount;
    }

    public void setMegapixelsCount(int megapixelsCount) {
        this.megapixelsCount = megapixelsCount;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getDisplayPermission() {
        return displayPermission;
    }

    public void setDisplayPermission(double displayPermission) {
        this.displayPermission = displayPermission;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
}
