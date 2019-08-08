package controller.db_controller;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    //-------------------------------------------------Check login------------------------------------------------------
    public User chkLogin(User user) {
        User userObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select accountType " +
                    "from user " +
                    "where uId=? && binary(password) = binary(?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, user.getuId());//---Set values to sql object
            preparedStatement.setObject(2, user.getPassword());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                userObj = new User();
                userObj.setAccountType(rst.getString(1));//---Set table row data to user model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return userObj;//---Return user object if login exist, if not return null
    }

    //----------------------------------------------------Get email via user id-----------------------------------------
    public User getEmailViaUid(User user) {
        User userEmail = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select emailAddress from user where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, user.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                userEmail = new User();
                userEmail.setEmailAddress(rst.getString(1));//---Set table row data to user model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return userEmail;//---Return user object if user exist, if not return null
    }

    //----------------------------------------------------Add user------------------------------------------------------
    public boolean addUser(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user (uId,password,accountType,emailAddress) values (?,?,?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, user.getuId());//---Set values to sql object
            preparedStatement.setObject(2, user.getPassword());//---Set values to sql object
            preparedStatement.setObject(3, "student");//---Set values to sql object
            preparedStatement.setObject(4, user.getEmailAddress());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;//---Returns if add fails
    }

    //----------------------------------------------------Update email--------------------------------------------------
    public boolean updateEmail(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("update user set emailAddress=? where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, user.getEmailAddress());//---Set values to sql object
            preparedStatement.setObject(2, user.getuId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;//---Returns if update fails
    }

    //----------------------------------------------------Update password-----------------------------------------------
    public boolean updatePassword(User user) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("update user set password=? where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, user.getPassword());//---Set values to sql object
            preparedStatement.setObject(2, user.getuId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;//---Returns if update fails
    }
}
