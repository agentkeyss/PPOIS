package org.example;

abstract public class Building {
    protected int id = -1;
    protected String country;
    protected String city;
    protected String street;
    protected int buildingNumber;
    protected String contactPhoneNumber;

    public Building(String country, String city, String street, int buildingNumber, String contactPhoneNumber) {
        this.country = country;
        this.city = city;
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getStreet() {
        return street;
    }
}
