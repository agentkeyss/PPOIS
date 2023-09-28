package org.example;

import org.example.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SignUpAndCreationTests {

    @Test
    public void signUpEmployeeTest() throws SQLException {
        Office office = new Office("Belarus", "Minsk", "Asertovskaya", 78,
                "+375214564196");
        office.register();
        Employee employee = new Employee("Dmitry", "Oskirko", "+375291728835", Gender.MALE,
                100.5, Department.TOUR_AGENT, JobTitle.DIRECTOR, LocalDate.of(2005, 1, 28),
                "agentkeyyy@gmail.com", Database.getOfficeID("Belarus", "Minsk", "Pobedy",
                43), "qwerty", LocalDate.of(2010, 8, 23));
        Assert.assertTrue(employee.signUp());
    }

    @Test
    public void signUpUserTest() {
        User user = new User("Steve", "Jobs", "+375129845687", Gender.MALE, LocalDate.of(1955, 2, 24),
                "jobs@mail.ru", "stevejobs1955");
        Assert.assertTrue(user.signUp());
    }

    @Test
    public void addingNewOfficeTest() {
        Office office = new Office("Belarus", "Minsk", "Pobedy", 43,
                "+375214569034");
        Assert.assertTrue(office.register());
    }

    @Test
    public void addingNewHotelTest() {
        Hotel hotel = new Hotel("USA", "Washington", "Green", 21,
                "+14323534534", "Grand Hotel", HotelType.CASINO_HOTEL, 5, 5);
        Assert.assertTrue(hotel.register());
    }

    @Test
    public void addingNewTransportTest() {
        Transport transport = new Transport("TF-5001", 120, TransportType.SHIP, 60);
        Assert.assertTrue(transport.register());
    }

    @Test
    public void addingNewOrderTest() throws SQLException{
        Order order = new Order(LocalDateTime.of(2023, 10, 9, 12, 45, 3),
                LocalDateTime.of(2023, 10, 12, 4, 2, 56),
                "Belarus", "UK", "Minsk", "London",
                400, 10, Database.getHotelID("USA", "Washington", "Green", 21), 1);
        Assert.assertTrue(order.add());
    }
}
