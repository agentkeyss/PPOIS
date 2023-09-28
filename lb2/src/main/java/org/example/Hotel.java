package org.example;

import java.sql.SQLException;

public class Hotel extends Building{
    private final String name;
    private final HotelType hotelType;
    private final int roomsCount;
    private final int starsCount;

    public Hotel(String country, String city, String street, int buildingNumber, String contactPhoneNumber, String name,
                 HotelType hotelType, int roomsCount, int starsCount) {
        super(country, city, street, buildingNumber, contactPhoneNumber);
        this.name = name;
        this.hotelType = hotelType;
        this.roomsCount = roomsCount;
        this.starsCount = starsCount;
    }

    public boolean register(){
        try {
            Database.addHotel(this);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean unregister(int id){
        try {
            Database.deleteTable("hotels" ,id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public int getStarsCount() {
        return starsCount;
    }
}
