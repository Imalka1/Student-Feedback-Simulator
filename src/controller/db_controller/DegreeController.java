package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeController {

    public Degree getDegreeData(Student student) {
        Degree degree = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid from student s,faculty f,degree d where f.facid=d.facid && d.degid=s.degid && s.uid=?");
            preparedStatement.setObject(1, student.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                degree = new Degree();
                degree.setFacultyName(rst.getString(1));
                degree.setDegreeName(rst.getString(2));
                degree.setDegid(rst.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public Degree getDegreeName(Student student){
        Degree degree = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select d.name from student s,degree d where d.degid=s.degid && s.uid=?");
            preparedStatement.setObject(1, student.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                degree = new Degree();
                degree.setDegreeName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public List<Degree> getAllDegrees(){
        List<Degree> degrees = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select degid,name from degree");
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
