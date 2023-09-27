package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

public class SignInTests {
    @Test
    public void signInEmployeeTest() throws SQLException {
        Employee employee = new Employee();
        employee.signIn("agentkeyyy@gmail.com", "qwerty");
        Employee exceptedEmployee = new Employee("Dmitry", "Oskirko", "+375291728835", Gender.MALE,
                100.5, Department.TOUR_AGENT, JobTitle.DIRECTOR, LocalDate.of(2005, 1, 28),
                "agentkeyyy@gmail.com", 1, "qwerty", LocalDate.of(2010, 8, 23));
        Assert.assertEquals(exceptedEmployee, employee);
    }

    @Test
    public void signInUserTest() throws SQLException {
        User user = new User();
        user.signIn("+375129845687", "stevejobs1955");
        User exceptedUser = new User("Steve", "Jobs", "+375129845687", Gender.MALE, LocalDate.of(1955, 2, 24),
                "jobs@mail.ru", "stevejobs1955");
        Assert.assertEquals(exceptedUser, user);
    }
}
