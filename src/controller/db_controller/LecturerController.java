package controller.db_controller;

import db.DBConnection;
import model.Lecturer;
import model.Subject;
import model.SubjectLecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    //----------------------------------------Get all lecturers---------------------------------------------
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select lecturerId,lecturer_name " +
                    "from lecturer");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Lecturer lecturer = new Lecturer();//---Creates a lecturer object
                lecturer.setLecturerId(rst.getString(1));//---Set table row data to lecturer model object
                lecturer.setLecturerName(rst.getString(2));//---Set table row data to lecturer model object
                lecturers.add(lecturer);//---Add lecturer object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return lecturers;//---Return lecturers array object with a length > 0 if lecturers exists, if not array object returns with a length = 0
    }
}