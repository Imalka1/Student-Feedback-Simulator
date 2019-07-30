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

    //----------------------------------------Get all lecturers via subject---------------------------------------------
    public List<Lecturer> getAllLecturersViaSubject(Subject subject) {
        List<Lecturer> lecturers = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select l.lecid,l.name from lecturer l,subject_lecturer sl where l.lecid=sl.lecid && subid=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Lecturer lecturer = new Lecturer();//---Creates a lecturer object
                lecturer.setLecId(rst.getString(1));//---Set table row data to lecturer model object
                lecturer.setLecturerName(rst.getString(2));//---Set table row data to lecturer model object
                lecturers.add(lecturer);//---Add lecturer object to array object
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
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct dateOfSubmission from subject_lecturer sl,marks m where sl.sublecid=m.sublecid && lecid=? && subid=? order by 1 desc");//---Prepare sql as a java object
            preparedStatement.setObject(1, subjectLecturer.getLecid());//---Set values to sql object
            preparedStatement.setObject(2, subjectLecturer.getSubid());//---Set values to sql object
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
