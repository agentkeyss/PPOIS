package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id = -1;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
    private final LocalDateTime orderDateTime;
    private final String departureCountry;
    private final String arrivalCountry;
    private final String departureCity;
    private final String arrivalCity;
    private final double cost;
    private final int daysDuration;
    private int customerID;
    private int hotelID = -1;
    private int transportID = -1;
    private final String cipher;

    public Order(LocalDateTime departureDateTime,  LocalDateTime arrivalDateTime, String departureCountry,
                 String arrivalCountry, String departureCity, String arrivalCity, double cost, int daysDuration,
                 int hotelID, int transportID) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.orderDateTime = LocalDateTime.now();
        this.departureCountry = departureCountry;
        this.arrivalCountry = arrivalCountry;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.cost = cost;
        this.daysDuration = daysDuration;
        this.hotelID = hotelID;
        this.transportID = transportID;
        this.cipher = DigestUtils.sha256Hex(" " + departureDateTime + arrivalDateTime + orderDateTime +
                departureCountry + arrivalCountry + cost + daysDuration);
    }

    public boolean add() {
        try {
            Database.addOrder(this);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public double getCost() {
        return cost;
    }

    public int getDaysDuration() {
        return daysDuration;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCipher() {
        return cipher;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getTransportID() {
        return transportID;
    }
}
