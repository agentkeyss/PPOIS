package org.example;

import java.sql.SQLException;

public class Office extends Building{
    public Office(String country, String city, String street, int buildingNumber, String contactPhoneNumber) {
        super(country, city, street, buildingNumber, contactPhoneNumber);
    }

    public boolean register(){
        try {
            Database.addOffice(this);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean unregister(int id) {
        try {
            Database.deleteOffice(id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
