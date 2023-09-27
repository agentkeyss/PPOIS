package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Database{
    private static final String DATABASE = "tourist_agency";
    private static final String PASSWORD = "1q2w3e4r5t6y7u8i";

    public static boolean addUser(User user) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                "1q2w3e4r5t6y7u8i"
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO users(name, surname, " +
                        "hashed_password, phone_number, gender, birth_date, email, registration_date_and_time) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                user.getName(), user.getSurname(), user.getHashedPassword(), user.getPhoneNumber(),
                user.getGender(), user.getBirthDate(), user.getEmail(),
                user.getRegistrationDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean addEmployee(Employee employee) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO employees(name, surname, " +
                        "hashed_password, phone_number, gender, birth_date, email, salary, department, job_title," +
                        "came_to_company_date, registration_date_and_time, office_id) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
                employee.getName(), employee.getSurname(), employee.getHashedPassword(), employee.getPhoneNumber(),
                employee.getGender(), employee.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                employee.getEmail(), employee.getSalary(), employee.getDepartment(), employee.getJobTitle(),
                employee.getCameToCompanyDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                employee.getRegistrationDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                employee.getOfficeID()
                ));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean addOrder(Order order) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO orders(departure_date_and_time, arrival_date_and_time, " +
                        "order_date_and_time, departure_country, departure_city, arrival_city," +
                        "arrival_country, cost, days_duration, cipher, transport_id, hotel_id) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d)",
                order.getDepartureDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                order.getArrivalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                order.getDepartureCity(), order.getArrivalCity(),
                order.getDepartureCountry(), order.getArrivalCountry(), order.getCost(), order.getDaysDuration(),
                order.getCipher(), order.getTransportID(), order.getHotelID()));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean setCustomerToOrder(int orderID, int customerID) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("UPDATE orders SET customer_id = %d " +
                "WHERE id = %d", customerID, orderID));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean deleteCustomerFromOrder(int orderID) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("UPDATE orders SET customer_id = null " +
                "WHERE id = %d", orderID));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean addTransport(Transport transport) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO transports(model, number_of_seats, " +
                        "transport_type, max_speed) " +
                        "VALUES('%s', '%s', '%s', '%s')", transport.getModel(), transport.getSeatsNumber(),
                transport.getTransportType(), transport.getMaxSpeed()));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean addHotel(Hotel hotel) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO hotels(name, hotel_type, " +
                        "rooms_count, stars_count, country, city, building_number, contact_phone_number, street) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                hotel.getName(), hotel.getHotelType(), hotel.getRoomsCount(), hotel.getStarsCount(),
                hotel.getCountry(), hotel.getCity(), hotel.getBuildingNumber(), hotel.getContactPhoneNumber(),
                hotel.getStreet()
                ));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean addOffice(Office office) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int updatedRowsCount = statement.executeUpdate(String.format("INSERT INTO offices(country, city, " +
                        "building_number, contact_phone_number, street) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s')",
                office.getCountry(), office.getCity(), office.getBuildingNumber(), office.getContactPhoneNumber(),
                office.getStreet()
        ));

        connection.close();
        return updatedRowsCount > 0;
    }

    public static boolean isPersonExistByPhoneNumber(String phoneNumber) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE phone_number = " +
                "'%s'", phoneNumber));

        if (resultSet.next()) {
            return true;
        }

        resultSet = statement.executeQuery(String.format("SELECT * FROM employees WHERE phone_number = " +
                "'%s'", phoneNumber));

        return resultSet.next();
    }

    public static boolean isPersonExistByEmail(String email) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users WHERE email = " +
                "'%s'", email));

        if (resultSet.next()) {
            return true;
        }

        resultSet = statement.executeQuery(String.format("SELECT * FROM employees WHERE email = " +
                "'%s'", email));

        return resultSet.next();
    }

    public static User getUserByPhoneNumber(String phoneNumber, String password) throws SQLException {
        User user = null;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users " +
                "WHERE phone_number = '%s' AND hashed_password = '%s'", phoneNumber, DigestUtils.sha256Hex(password)));
        while(resultSet.next()) {
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            Gender gender = Gender.valueOf(resultSet.getString(7));
            LocalDate birthDate = LocalDate.parse(resultSet.getString(8),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String email = resultSet.getString(9);
            user = new User(name, surname, phoneNumber, gender, birthDate, email, password);
            user.setId(resultSet.getInt(1));
            user.setRegistrationDateTime(LocalDateTime.parse(resultSet.getString(6),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return user;
    }

    public static User getUserByEmail(String email, String password) throws SQLException {
        User user = null;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users " +
                "WHERE email = '%s' AND hashed_password = '%s'", email, DigestUtils.sha256Hex(password)));
        while(resultSet.next()) {
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String phoneNumber = resultSet.getString(5);
            Gender gender = Gender.valueOf(resultSet.getString(7));
            LocalDate birthDate = LocalDate.parse(resultSet.getString(8),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            user = new User(name, surname, phoneNumber, gender, birthDate, email, password);
            user.setId(resultSet.getInt(1));
            user.setRegistrationDateTime(LocalDateTime.parse(resultSet.getString(6),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return user;
    }

    public static Employee getEmployeeByPhoneNumber(String phoneNumber, String password) throws SQLException {
        Employee employee = null;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM employees " +
                "WHERE phone_number = '%s' AND hashed_password = '%s'", phoneNumber, DigestUtils.sha256Hex(password)));
        while(resultSet.next()) {
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            double salary = resultSet.getDouble(6);
            Department department = Department.valueOf(resultSet.getString(7));
            JobTitle jobTitle = JobTitle.valueOf(resultSet.getString(8));
            int officeID = resultSet.getInt(10);
            Gender gender = Gender.valueOf(resultSet.getString(11));
            LocalDate birthDate = LocalDate.parse(resultSet.getString(12),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String email = resultSet.getString(13);
            LocalDate cameToCompanyDate = LocalDate.parse(resultSet.getString(9) ,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            employee = new Employee(name, surname, phoneNumber, gender, salary, department, jobTitle, birthDate, email,
                    officeID, password, cameToCompanyDate);
            employee.setId(resultSet.getInt(1));
            employee.setRegistrationDateTime(LocalDateTime.parse(resultSet.getString(14),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return employee;
    }

    public static Employee getEmployeeByEmail(String email, String password) throws SQLException {
        Employee employee = null;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM employees " +
                "WHERE email = '%s' AND hashed_password = '%s'", email, DigestUtils.sha256Hex(password)));
        while(resultSet.next()) {
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            double salary = resultSet.getDouble(6);
            Department department = Department.valueOf(resultSet.getString(7));
            JobTitle jobTitle = JobTitle.valueOf(resultSet.getString(8));
            int officeID = resultSet.getInt(10);
            Gender gender = Gender.valueOf(resultSet.getString(11));
            LocalDate birthDate = LocalDate.parse(resultSet.getString(12),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String phoneNumber = resultSet.getString(5);
            LocalDate cameToCompanyDate = LocalDate.parse(resultSet.getString(9) ,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            employee = new Employee(name, surname, phoneNumber, gender, salary, department, jobTitle, birthDate, email,
                    officeID, password, cameToCompanyDate);
            employee.setId(resultSet.getInt(1));
            employee.setRegistrationDateTime(LocalDateTime.parse(resultSet.getString(14),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return employee;
    }

    public static int getHotelID(String country, String city, String street, int buildingNumber) throws SQLException {
        int id = -1;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM hotels " +
                "WHERE country = '%s' AND city = '%s' AND street = '%s' AND building_number = %d", country, city, street,
                buildingNumber));
        while(resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    public static int getTransportID(String model, TransportType transportType) throws SQLException {
        int id = -1;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM transports " +
                "WHERE model = '%s' AND transport_type = '%s'", model, transportType));
        while(resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    public static int getOfficeID(String country, String city, String street, int buildingNumber) throws SQLException {
        int id = -1;

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM offices " +
                        "WHERE country = '%s' AND city = '%s' AND street = '%s' AND building_number = %d", country, city, street,
                buildingNumber));
        while(resultSet.next()) {
            id = resultSet.getInt(1);
        }

        return id;
    }

    public static boolean deleteHotel(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM hotels WHERE id = %d", id));

        return deletedRows > 0;
    }

    public static boolean deleteOffice(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM offices WHERE id = %d", id));

        return deletedRows > 0;
    }

    public static boolean deleteUser(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM users WHERE id = %d", id));

        return deletedRows > 0;
    }

    public static boolean deleteEmployee(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM employees WHERE id = %d", id));

        return deletedRows > 0;
    }

    public static boolean deleteOrder(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM orders WHERE id = %d", id));

        return deletedRows > 0;
    }

    public static boolean deleteTransport(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DATABASE,
                "tour_admin",
                PASSWORD
        );
        Statement statement = connection.createStatement();
        int deletedRows = statement.executeUpdate(String.format("DELETE FROM transports WHERE id = %d", id));

        return deletedRows > 0;
    }
}