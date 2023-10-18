package com.example.lb3.models;

import com.example.lb3.AccessoryType;

public class Computer extends Product{
    private String videoCard;
    private int coreCount;
    private double displayPermission;
    private String processor;

    public Computer(String name, double price, String videoCard, int coreCount, double displayPermission, String processor) {
        super(name, price);
        this.videoCard = videoCard;
        this.coreCount = coreCount;
        this.displayPermission = displayPermission;
        this.processor = processor;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
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
