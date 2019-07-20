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

    public List<Subject> getSubjectsViaSemesterAndDegree(Subject subject) {
        List<Subject> subjects = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select s.subid,title,l.name,credits from subject s,lecturer l,subject_lecturer sl where l.lecid=sl.lecid && s.subid=sl.subid && degid=? && semid=? && current=true");
            preparedStatement.setObject(1, subject.getDegid());
            preparedStatement.setObject(2, subject.getSemid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Subject subjectSemesterAndDegree = new Subject();
                subjectSemesterAndDegree.setSubjectId(rst.getString(1));
                subjectSemesterAndDegree.setSubjectName(rst.getString(2));
                subjectSemesterAndDegree.setLecturerName(rst.getString(3));
                subjectSemesterAndDegree.setCredits(rst.getInt(4));
                subjects.add(subjectSemesterAndDegree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public Subject getSubjectNameAndCredits(Subject subject) {
        Subject subjectNameAndCredits = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select title,credits,l.name,sublecid from subject s,lecturer l,subject_lecturer sl where l.lecid=sl.lecid && s.subid=sl.subid && s.subid=? && current=true");
            preparedStatement.setObject(1, subject.getSubjectId());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                subjectNameAndCredits = new Subject();
                subjectNameAndCredits.setSubjectName(rst.getString(1));
                subjectNameAndCredits.setCredits(rst.getInt(2));
                subjectNameAndCredits.setLecturerName(rst.getString(3));
                subjectNameAndCredits.setSublecId(rst.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectNameAndCredits;
    }
}
