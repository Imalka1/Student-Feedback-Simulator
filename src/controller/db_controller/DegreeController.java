package controller.db_controller;

import db.DBConnection;
import model.DegreeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeController {

    public DegreeDTO getDegreeData(String uid) {
        DegreeDTO degreeDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid from student s,faculty f,degree d where f.facid=d.facid && d.degid=s.degid && s.uid=?");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return degreeDTO;
    }

    public DegreeDTO getDegreeName(String uid){
        DegreeDTO degreeDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select d.name from student s,degree d where d.degid=s.degid && s.uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                degreeDTO = new DegreeDTO();
                degreeDTO.setDegreeName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return degreeDTO;
    }

    public List<DegreeDTO> getAllDegrees(){
        List<DegreeDTO> degreeDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select degid,name from degree");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                DegreeDTO degreeDTO = new DegreeDTO();
                degreeDTO.setDegid(rst.getInt(1));
                degreeDTO.setDegreeName(rst.getString(2));
                degreeDTOS.add(degreeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return degreeDTOS;
    }
}
