package controller.db_controller;

import db.DBConnection;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectController {

    public List<Subject> getSubjectsViaSemesterAndDegree(int degid, int semid) {
        List<Subject> subjects = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select s.subid,title,l.name,credits from subject s,lecturer l,subject_lecturer sl where l.lecid=sl.lecid && s.subid=sl.subid && degid=? && semid=? && current=true");
            preparedStatement.setObject(1, degid);
            preparedStatement.setObject(2, semid);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rst.getString(1));
                subject.setSubjectName(rst.getString(2));
                subject.setLecturerName(rst.getString(3));
                subject.setCredits(rst.getInt(4));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public Subject getSubjectNameAndCredits(String subjectId) {
        Subject subject = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select title,credits,l.name,sublecid from subject s,lecturer l,subject_lecturer sl where l.lecid=sl.lecid && s.subid=sl.subid && s.subid=? && current=true");
            preparedStatement.setObject(1, subjectId);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                subject = new Subject();
                subject.setSubjectName(rst.getString(1));
                subject.setCredits(rst.getInt(2));
                subject.setLecturerName(rst.getString(3));
                subject.setSublecId(rst.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
