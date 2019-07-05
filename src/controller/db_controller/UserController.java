package controller.db_controller;

import db.DBConnection;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public UserDTO chkLogin(String username, String password) {
        UserDTO userDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select uid,accountType from user where uid=? && password=?");
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                userDTO = new UserDTO();
                userDTO.setUid(rst.getString(1));
                userDTO.setAccountType(rst.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    public boolean addUser(UserDTO userDTO) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (uid,password,accountType) values (?,?,?)");
            preparedStatement.setObject(1, userDTO.getUid());
            preparedStatement.setObject(2, userDTO.getPassword());
            preparedStatement.setObject(3, "student");
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static UserDTO getAdminUsername(String uid) {
//        UserDTO userDTO = null;
//        try {
//            Connection connection = DBConnection.getDBConnection().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select admin_name from user u,admin a where u.uid=a.uid && u.uid=?");
//            preparedStatement.setObject(1, uid);
//            ResultSet rst = preparedStatement.executeQuery();
//            if (rst.next()) {
//                userDTO = new UserDTO();
//                userDTO.setUsername(rst.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return userDTO;
//    }
}
