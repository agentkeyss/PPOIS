package com.example.lb3;

import com.example.lb3.models.Accessory;
import com.example.lb3.models.Account;
import com.example.lb3.models.Computer;
import com.example.lb3.models.Product;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String USER = "shop_designer";
    private static final String DATABASE = "online_shop";
    private static final String PASSWORD = "Qwerty123";

    public static void init() throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS accounts(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(50), email VARCHAR(50), right_to_add_products BOOLEAN DEFAULT false, hashed_password VARCHAR(64))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS products(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50), price FLOAT, product_type VARCHAR(50), customer_id INT, FOREIGN KEY (customer_id) REFERENCES accounts (id))");
        }
    }

    private static String getProductTypeByClass(Product product) {
        return product.getClass().getSimpleName();
    }

//    private static String getInsertStatementByClass(Product product) {
//        switch (product.getClass().getName()) {
//            case "Accessory" -> {
//                return String.format("INSERT INTO accessories(name, price, accessory_type, color)" +
//                        "VALUES('%s', '%s', '%s', '%s')", product.getName(), product.getPrice(), ((Accessory)product).getAccessoryType(), ((Accessory) product).getColor());
//            }
//            case "Computer" -> {
//                return String.format("INSERT INTO computers(name, price, video_card, core_count, display_permission, processor)" +
//                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s')", product.getName(), product.getPrice(), ((Computer)product).getVideoCard(), ((Computer)product).getCoreCount(),
//                        ((Computer)product).getDisplayPermission(), ((Computer)product).getProcessor());
//            }
//            case "Smartphone" -> {
//                return "smartphones";
//            }
//            case "Tablet" -> {
//                return "tablets";
//            }
//        }
//
//        return "TVs";
//    }

    public static void addAccount(Account user) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO accounts(username, email, hashed_password, right_to_add_products) " +
                            "VALUES('%s', '%s', '%s', %b)",
                    user.getUsername(), user.getEmail(), DigestUtils.sha256Hex(user.getPassword()), user.isWithAddingAccess()));
        }
    }

    public static void addProduct(Product product) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO products(name, price, product_type) " +
                            "VALUES('%s', '%s', '%s')", product.getName(), product.getPrice(), getProductTypeByClass(product)));
        }
    }

    public static int getAccountID(Account user) throws SQLException {
        if (!isAccountExist(user)) return -1;
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery(String.format("SELECT * FROM accounts WHERE email = '%s'", user.getEmail()));
            users.next();
            return users.getInt("id");
        }
    }

    public static int getProductID(Product product) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet products = statement.executeQuery(String.format("SELECT * FROM products WHERE name = '%s'", product.getName()));

            if (!products.next()) return -1;
            return products.getInt("id");
        }
    }

    public static boolean isAccountExist(Account user) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery(String.format("SELECT * FROM accounts WHERE email = '%s'", user.getEmail()));
            return users.next();
        }
    }

    public static Account getUserInDB(String email, String password) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery(String.format(String.format("SELECT * FROM accounts WHERE email = '%s' AND " +
                    "hashed_password = '%s'", email, DigestUtils.sha256Hex(password))));
            if (users.next()) return new Account(users.getString("username"), email, password,
                    users.getBoolean("right_to_add_products"));
            return null;
        }
    }

    public static void updatePassword(String newPassword) throws SQLException {
        ShopApplication.getCurrentAccount().setPassword(newPassword);

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE accounts SET hashed_password = '%s' WHERE email = '%s'",
                    DigestUtils.sha256Hex(newPassword), ShopApplication.getCurrentAccount().getEmail()));
        }
    }

    public static ArrayList<Product> getProductsArrayListInDB() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet productsSet = statement.executeQuery("SELECT * FROM products WHERE customer_id is NULL");
            while (productsSet.next()) {
                products.add(new Product(productsSet.getString("name"), productsSet.getDouble("price")));
            }
        }

        return products;
    }

    public static ArrayList<Product> getCartArrayListInDB(int userID) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet productsSet = statement.executeQuery(String.format("SELECT * FROM products WHERE customer_id = %d", userID));
            while (productsSet.next()) {
                products.add(new Product(productsSet.getString("name"), productsSet.getDouble("price")));
            }
        }

        return products;
    }

    public static void setAccountIDForProduct(Account user, Product product) throws SQLException {
        int userID = Database.getAccountID(user);
        int productID = Database.getProductID(product);

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE products SET customer_id = %d WHERE id = %d", userID, productID));
        }
    }

    public static void clearAccountIDForProduct(Product product) throws SQLException {
        int productID = Database.getProductID(product);

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE products SET customer_id = NULL WHERE id = %d", productID));
        }
    }

    public static void clearDatabase() throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLES products, accounts");
        }
    }

    public static void giveRightsToAdd(String email) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE,
                USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE accounts SET right_to_add_products=%b", true));
        }
    }
}
