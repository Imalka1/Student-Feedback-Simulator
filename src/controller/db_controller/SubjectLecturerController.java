package controller.db_controller;

import db.DBConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectLecturerController {

    //----------------------------------------Get all lecturers via subject---------------------------------------------
    public List<SubjectLecturer> getAllLecturersViaSubject(Subject subject) {
        List<SubjectLecturer> lecturers = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select l.lecturerId,l.lecturer_name,current " +
                    "from lecturer l,subject_lecturer sl " +
                    "where l.lecturerId=sl.lecturerId && subjectId=? " +
                    "order by 1 desc");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                SubjectLecturer subjectLecturer = new SubjectLecturer();//---Creates a lecturer object
                subjectLecturer.setLecturerId(rst.getString(1));//---Set table row data to lecturer model object
                subjectLecturer.setLecturerName(rst.getString(2));//---Set table row data to lecturer model object
                subjectLecturer.setCurrent(rst.getBoolean(3));//---Set table row data to lecturer model object
                lecturers.add(subjectLecturer);//---Add lecturer object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return lecturers;//---Return lecturers array object with a length > 0 if lecturers exists, if not array object returns with a length = 0
    }

    //-----------------------------------Get all dates via subject and lecturer-----------------------------------------
    public List<Mark> getAllDatesViaSubjectAndLecturer(SubjectLecturer subjectLecturer) {
        List<Mark> dates = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select distinct dateOfSubmission " +
                    "from subject_lecturer sl,marks m " +
                    "where sl.subjectLecturerId=m.subjectLecturerId && lecturerId=? && subjectId=? " +
                    "order by 1 desc");//---Prepare sql as a java object
            preparedStatement.setObject(1, subjectLecturer.getLecturerId());//---Set values to sql object
            preparedStatement.setObject(2, subjectLecturer.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Mark mark = new Mark();//---Creates a mark object
                mark.setDateOfSubmission(rst.getString(1));//---Set table row data to mark model object
                dates.add(mark);//---Add mark object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return dates;//---Return dates array object with a length > 0 if dates exists, if not array object returns with a length = 0
    }
}
