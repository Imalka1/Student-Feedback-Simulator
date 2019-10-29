package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Semester;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeController {

    //----------------------------------------Get all degrees via faculty-----------------------------------------------
    public List<Degree> getAllDegrees() {
        List<Degree> degrees = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select degreeId,name from degree");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Degree degree = new Degree();//---Creates a degree object
                degree.setDegreeId(rst.getInt(1));//---Set table row data to degree model object
                degree.setDegreeName(rst.getString(2));//---Set table row data to degree model object
                degrees.add(degree);//---Add degree object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return degrees;//---Return degrees array object with a length > 0 if degrees exists, if not array object returns with a length = 0
    }

    //---------------------------------------------------Add lecturer----------------------------------------------------
    public boolean addDegree(Degree degree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into degree (name) " +
                    "values (?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, degree.getDegreeName());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Update lecturer---------------------------------------------------
    public boolean updateDegree(Degree degree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update degree " +
                    "set name=? " +
                    "where degreeId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, degree.getDegreeName());//---Set values to sql object
            preparedStatement.setObject(2, degree.getDegreeId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete lecturer---------------------------------------------------
    public boolean deleteDegree(Degree degree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("delete from degree where degreeId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, degree.getDegreeId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }
}
