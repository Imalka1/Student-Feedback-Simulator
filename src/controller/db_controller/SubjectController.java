package controller.db_controller;

import db.DBConnection;
import model.SubjectDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectController {

    public static ArrayList<SubjectDTO> getSubjects(int degid, int semid) {
        ArrayList<SubjectDTO> subjectDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select s.subid,title,l.name,credits from subject s,lecturer l where l.lecid=s.lecid && degid=? && semid=?");
            preparedStatement.setObject(1, degid);
            preparedStatement.setObject(2, semid);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setSubjectId(rst.getString(1));
                subjectDTO.setSubjectName(rst.getString(2));
                subjectDTO.setLecturerName(rst.getString(3));
                subjectDTO.setCredits(rst.getInt(4));
                subjectDTOS.add(subjectDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subjectDTOS;
    }
}
