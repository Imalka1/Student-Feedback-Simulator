package controller.db_controller;

import db.DBConnection;
import model.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyController {

    //--------------------------------------------Get all faculties-----------------------------------------------------
    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select facultyId,name from faculty");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Faculty faculty = new Faculty();//---Creates a faculty object
                faculty.setFacultyId(rst.getInt(1));//---Set table row data to faculty model object
                faculty.setFacultyName(rst.getString(2));//---Set table row data to faculty model object
                faculties.add(faculty);//---Add faculty object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return faculties;//---Return faculties array object with a length > 0 if faculties exists, if not array object returns with a length = 0
    }
}
