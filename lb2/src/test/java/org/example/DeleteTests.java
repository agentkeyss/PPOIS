package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DeleteTests {
    @Test
    public void deleteUserByEmployeeTest() throws SQLException {
        User user = new User();
        Employee employee = new Employee();
        employee.signIn("+375291728835", "qwerty");
        user.signIn("+375129845687", "stevejobs1955");
        Assert.assertTrue(employee.deleteUser(user.getId()));
    }

    @Test
    public void deleteUserTest() throws SQLException {
        User user = new User();
        user.signIn("+375129845687", "stevejobs1955");
        user.deleteOrder(1);
        Assert.assertTrue(user.deleteAccount());
    }

    @Test
    public void deleteHotelTest() throws SQLException {
        Employee employee = new Employee();
        employee.signIn("+375291728835", "qwerty");
        employee.deleteHotel(Database.getHotelID("USA", "Washington", "Green", 21));
    }

    @Test
    public void deleteOfficeTest() throws SQLException {
        Employee employee = new Employee();
        employee.signIn("+375291728835", "qwerty");
        employee.deleteOffice(Database.getOfficeID("Belarus", "Minsk", "Pobedy", 43));
    }

    @Test
    public void deleteAccount() throws SQLException {
        Employee employee = new Employee();
        employee.signIn("+375291728835", "qwerty");
        employee.deleteAccount();
    }

    @Test
    public void deleteTransportTest() throws SQLException {
        Employee employee = new Employee();
        employee.signIn("+375291728835", "qwerty");
        employee.deleteTransport(Database.getTransportID("TF-5001", TransportType.SHIP));
    }
}
