package controller.db_controller;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    public User chkLogin(User user) {
        User userObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select accountType from user where uid=? && password=?");
            preparedStatement.setObject(1, user.getUid());
            preparedStatement.setObject(2, user.getPassword());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                userObj = new User();
                userObj.setAccountType(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userObj;
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

    public boolean updateEmail(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update user set emailAddress=? where uid=?");
            preparedStatement.setObject(1, user.getEmailAddress());
            preparedStatement.setObject(2, user.getUid());
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

    public boolean updatePassword(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update user set password=? where uid=?");
            preparedStatement.setObject(1, user.getPassword());
            preparedStatement.setObject(2, user.getUid());
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

    public User getEmailViaUid(User user) {
        User userEmail = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select emailAddress from user where uid=?");
            preparedStatement.setObject(1, user.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                userEmail = new User();
                userEmail.setEmailAddress(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userEmail;
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
