package controller.db_controller;

import db.DBConnection;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private static Connection connection;

    public UserController() {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDTO chkLogin(String username, String password) throws SQLException {
        UserDTO userDTO = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select uid from user where uid=? && password=?");
        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, password);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            userDTO = new UserDTO();
            userDTO.setUid(rst.getString(1));
        }
        return userDTO;
    }
}
