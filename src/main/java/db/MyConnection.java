package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection;

    // creates a connection to MySQL
    public static Connection connect() {
        try {
            // Load MySQL JDBC driver
            // This is necessary to connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database (update credentials if needed)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filehider", "root", "31082005");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connected to database");
        return connection;
    }

    // closes the database connection
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test database connection
    public static void main(String[] args) {
        MyConnection.connect();
    }
}
