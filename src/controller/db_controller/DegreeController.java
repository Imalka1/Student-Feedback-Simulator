package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Faculty;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeController {

    public List<Degree> getAllDegreesViaFaculty(Faculty faculty){
        List<Degree> degrees = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select degid,name from degree where facid=?");
            preparedStatement.setObject(1, faculty.getFacid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Degree degree = new Degree();
                degree.setDegid(rst.getInt(1));
                degree.setDegreeName(rst.getString(2));
                degrees.add(degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degrees;
    }
}
