package controller.db_controller;

import db.DBConnection;
import model.Faculty;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyController {
    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select facid,name from faculty");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Faculty faculty = new Faculty();
                faculty.setFacid(rst.getInt(1));
                faculty.setFacultyName(rst.getString(2));
                faculties.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculties;
    }
}
