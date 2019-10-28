package controller.db_controller;

import db.DBConnection;
import model.Lecturer;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LecturerController {
    //---------------------------------------------------Add lecturer----------------------------------------------------
    public boolean addLecturer(Lecturer lecturer) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into lecturer (lecturerId,lecturer_name) " +
                    "values (?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, lecturer.getLecturerId());//---Set values to sql object
            preparedStatement.setObject(2, lecturer.getLecturerName());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Update lecturer---------------------------------------------------
    public boolean updateLecturer(Lecturer lecturer) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update lecturer " +
                    "set lecturer_name=? " +
                    "where lecturerId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, lecturer.getLecturerName());//---Set values to sql object
            preparedStatement.setObject(2, lecturer.getLecturerId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete lecturer---------------------------------------------------
    public boolean deleteLecturer(Lecturer lecturer) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("delete from lecturer where lecturerId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, lecturer.getLecturerId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }
}