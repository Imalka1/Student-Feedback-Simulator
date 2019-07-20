package controller.db_controller;

import db.DBConnection;
import model.Semester;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SemesterController {

    public Semester getSemesterName(Semester semester) {
        Semester semDTO = new Semester();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select text from semester where semid=?");
            preparedStatement.setObject(1, semester.getSemid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                semDTO.setSemesterName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semDTO;
    }
}
