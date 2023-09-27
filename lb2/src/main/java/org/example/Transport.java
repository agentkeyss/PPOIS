package org.example;

import java.sql.SQLException;

public class Transport {
    private int id = -1;
    private final String model;
    private final int seatsNumber;
    private final TransportType transportType;
    private final double maxSpeed;

    public Transport(String model, int seatsNumber, TransportType transportType, double maxSpeed) {
        this.model = model;
        this.seatsNumber = seatsNumber;
        this.transportType = transportType;
        this.maxSpeed = maxSpeed;
    }

    public boolean register(){
        try {
            Database.addTransport(this);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean unregister(int id) {
        try {
            Database.deleteTransport(id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public String getModel() {
        return model;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
