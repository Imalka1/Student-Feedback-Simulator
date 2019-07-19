package controller.db_controller;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public User chkLogin(String username, String password) {
        User user = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select uid,accountType from user where uid=? && password=?");
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                user = new User();
                user.setUid(rst.getString(1));
                user.setAccountType(rst.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean addUser(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (uid,password,accountType,emailAddress) values (?,?,?,?)");
            preparedStatement.setObject(1, user.getUid());
            preparedStatement.setObject(2, user.getPassword());
            preparedStatement.setObject(3, "student");
            preparedStatement.setObject(4, user.getEmailAddress());
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

//    public static User getAdminUsername(String uid) {
//        User userDTO = null;
//        try {
//            Connection connection = DBConnection.getDBConnection().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select admin_name from user u,admin a where u.uid=a.uid && u.uid=?");
//            preparedStatement.setObject(1, uid);
//            ResultSet rst = preparedStatement.executeQuery();
//            if (rst.next()) {
//                userDTO = new User();
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
