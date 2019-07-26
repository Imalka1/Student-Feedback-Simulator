package controller.db_controller;

import db.DBConnection;
import model.Lecturer;
import model.Mark;
import model.Subject;
import model.SubjectLecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectLecturerController {
    public List<Lecturer> getAllLecturersViaSubject(Subject subject) {
        List<Lecturer> lecturers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select l.lecid,l.name from lecturer l,subject_lecturer sl where l.lecid=sl.lecid && subid=?");
            preparedStatement.setObject(1, subject.getSubjectId());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setLecId(rst.getString(1));
                lecturer.setLecturerName(rst.getString(2));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }

    public List<Mark> getAllDatesViaSubjectAndLecturer(SubjectLecturer subjectLecturer) {
        List<Mark> dates = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct dateOfSubmission from subject_lecturer sl,marks m where sl.sublecid=m.sublecid && lecid=? && subid=? order by 1 desc");
            preparedStatement.setObject(1, subjectLecturer.getLecid());
            preparedStatement.setObject(2, subjectLecturer.getSubid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Mark mark = new Mark();
                mark.setDateOfSubmission(rst.getString(1));
                dates.add(mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }
}
