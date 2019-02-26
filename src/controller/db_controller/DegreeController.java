package controller.db_controller;

import db.DBConnection;
import model.DegreeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DegreeController {
    private static Connection connection;

    public DegreeController() {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DegreeDTO getDegreeData(String uid) {
        DegreeDTO degreeDTO = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid from user u,faculty f,degree d where f.facid=d.facid && d.degid=u.degid && u.uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                degreeDTO = new DegreeDTO();
                degreeDTO.setFacultyName(rst.getString(1));
                degreeDTO.setDegreeName(rst.getString(2));
                degreeDTO.setDegid(rst.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degreeDTO;
    }
}
