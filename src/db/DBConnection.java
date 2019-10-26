package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//-------------This class is responsible only for giving one database connection with the database server---------------
public class DBConnection {

    private static DBConnection dBConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//--Load JDBC driver from library(mysql connector)
            connection = DriverManager.getConnection("jdbc:mysql://localhost/studentfeedback", "root", "mysql");//--Establish the database connection via db url, db username and db password
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        } catch (ClassNotFoundException e) {//--Catch if driver is not loaded or cannot be found
            e.printStackTrace();
        }
    }

    //---------------------------------------Return DBConnection object-------------------------------------------------
    public static DBConnection getDBConnection() {
        if (dBConnection == null) {
            dBConnection = new DBConnection();//--Creates DBConnection object to retrieve database connection
        }
        return dBConnection;
    }

    //-----------------------------------------Return database connection-----------------------------------------------
    public Connection getConnection() {
        return connection;
    }
}
