package controller.db_controller;

import db.DBConnection;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public static UserDTO chkLogin(String username, String password) {
        UserDTO userDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select uid from user where uid=? && password=?");
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                userDTO = new UserDTO();
                userDTO.setUid(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    public static UserDTO getUsername(String uid) {
        UserDTO userDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select username from user where uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                userDTO = new UserDTO();
                userDTO.setUsername(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDTO;
    }
}
