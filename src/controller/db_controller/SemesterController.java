package controller.db_controller;

import db.DBConnection;
import model.Batch;
import model.Semester;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SemesterController {

    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select semid,name from semester");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Semester semester = new Semester();
                semester.setSemid(rst.getInt(1));
                semester.setSemesterName(rst.getString(2));
                semesters.add(semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semesters;
    }
}
