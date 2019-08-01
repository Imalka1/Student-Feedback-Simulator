package controller.db_controller;

import db.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    //--------------------------------------Get admin username via user id----------------------------------------------
    public Admin getAdminUsername(Admin admin) {
        Admin adminUsername = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select admin_name " +
                    "from user u,admin a " +
                    "where u.uId=a.uId && u.uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, admin.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                adminUsername = new Admin();
                adminUsername.setAdminName(rst.getString(1));//---Set table row data to user model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return adminUsername;//---Return admin object if user exist, if not return null
    }
}
