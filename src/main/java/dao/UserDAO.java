package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDAO {

    // check if user exists
    public static boolean userExists(String email) throws SQLException {

        Connection connection = MyConnection.connect();

        // PreparedStatement is used to execute sql queries
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");

        // resultset stores the result of the sql query
        ResultSet rs = ps.executeQuery();
        // When a SELECT query is executed, the database returns multiple rows in a ResultSet.
        // by default, the cursor is positioned before the first row (not on the first row).
        // Calling rs.next() moves the cursor to the first row.
        // Calling rs.next() again moves to the second row, and so on.
        // When there are no more rows, rs.next() returns false.

        if (rs.next()) {

            String email_str = rs.getString("email");
            if (email_str.equals(email)) {
                return true;
            }

        }

        return false;
    }

    // save user method
    public static int saveUser(User user) throws SQLException {

        Connection connection = MyConnection.connect();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, email) VALUES (?, ?)");

        // set the values of the parameters
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());


        // execute the query
        // it will return the number of rows affected
        return ps.executeUpdate();

    }

}
 