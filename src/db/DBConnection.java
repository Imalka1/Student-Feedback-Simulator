package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/studentfeedback", "root", "mysql");
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
