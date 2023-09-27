package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee extends Person{
    private double salary;
    private Department department;
    private JobTitle jobTitle;
    private LocalDate cameToCompanyDate;
    private int officeID;

    public Employee() {}

    public Employee(String name, String surname, String phoneNumber, Gender gender,
                    double salary, Department department, JobTitle jobTitle, LocalDate birthdayDate, String email,
                    int officeID, String password, LocalDate cameToCompanyDate) {
        super(name, surname, phoneNumber, gender, birthdayDate, email, password);
        this.salary = salary;
        this.department = department;
        this.jobTitle = jobTitle;
        this.cameToCompanyDate = cameToCompanyDate;
        this.officeID = officeID;
    }

    public boolean signUp() throws SQLException {

        if (Database.isPersonExistByPhoneNumber(this.phoneNumber) || Database.isPersonExistByEmail(this.email))
            return false;

        this.registrationDateTime = LocalDateTime.now();
        try {
            Database.addEmployee(this);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean deleteUser(int id){
        try {
            Database.deleteUser(id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean deleteHotel(int id){
        return Hotel.unregister(id);
    }

    public boolean deleteOffice(int id){
        return Office.unregister(id);
    }

    public boolean deleteTransport(int id) {
        return Transport.unregister(id);
    }

    public boolean addHotel(String country, String city, String street, int buildingNumber, String contactPhoneNumber,
                            String name, HotelType hotelType, int roomsCount, int starsCount){
        Hotel hotel = new Hotel(country, city, street, buildingNumber, contactPhoneNumber, name, hotelType, roomsCount,
                starsCount);
        return hotel.register();
    }

    public boolean signIn(String phoneNumberOrEmail, String password) throws SQLException {
        Employee employee = Database.getEmployeeByEmail(phoneNumberOrEmail, password);
        if (employee == null) employee = Database.getEmployeeByPhoneNumber(phoneNumberOrEmail, password);
        if (employee == null) return false;

        this.name = employee.name;
        this.surname = employee.surname;
        this.birthDate = employee.birthDate;
        this.hashedPassword = employee.hashedPassword;
        this.phoneNumber = employee.phoneNumber;
        this.gender = employee.gender;
        this.email = employee.email;
        this.salary = employee.salary;
        this.department = employee.department;
        this.jobTitle = employee.jobTitle;
        this.officeID = employee.officeID;
        this.cameToCompanyDate = employee.cameToCompanyDate;
        this.id = employee.id;
        this.registrationDateTime = employee.registrationDateTime;

        return true;
    }

    public boolean deleteAccount(){
        try {
            Database.deleteEmployee(this.id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public LocalDate getCameToCompanyDate() {
        return cameToCompanyDate;
    }

    public int getOfficeID() {
        return officeID;
    }
}
