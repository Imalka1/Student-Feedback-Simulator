package controller.db_controller;

import db.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {
    public Admin getAdminUsername(Admin admin) {
        Admin adminUsername = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select admin_name from user u,admin a where u.uid=a.uid && u.uid=?");
            preparedStatement.setObject(1, admin.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                adminUsername = new Admin();
                adminUsername.setAdminName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminUsername;
    }
}
