package controller.db_controller;

import db.DBConnection;
import model.Semester;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SemesterController {

    //-----------------------------------------------Get all semesters--------------------------------------------------
    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select semesterId,name from semester");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Semester semester = new Semester();//---Creates a semester object
                semester.setSemesterId(rst.getInt(1));//---Set table row data to semester model object
                semester.setSemesterName(rst.getString(2));//---Set table row data to semester model object
                semesters.add(semester);//---Add semester object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return semesters;//---Return semesters array object with a length > 0 if semesters exists, if not array object returns with a length = 0
    }
}
