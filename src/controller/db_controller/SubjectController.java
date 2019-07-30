package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Semester;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectController {

    //------------------------------------Get subjects via semester and degree------------------------------------------
    public List<Subject> getSubjectsViaSemesterAndDegree(Subject subject) {
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select s.subid,title,l.name,credits from subject s,lecturer l,subject_lecturer sl,subject_degree sd where l.lecid=sl.lecid && s.subid=sl.subid && s.subid=sd.subid && degid=? && semid=? && current=true");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getDegid());//---Set values to sql object
            preparedStatement.setObject(2, subject.getSemid());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Subject subjectSemesterAndDegree = new Subject();//---Creates a subject object
                subjectSemesterAndDegree.setSubjectId(rst.getString(1));//---Set table row data to subject model object
                subjectSemesterAndDegree.setSubjectName(rst.getString(2));//---Set table row data to subject model object
                subjectSemesterAndDegree.setLecturerName(rst.getString(3));//---Set table row data to subject model object
                subjectSemesterAndDegree.setCredits(rst.getInt(4));//---Set table row data to subject model object
                subjects.add(subjectSemesterAndDegree);//---Add subject object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjects;//---Return subjects array object with a length > 0 if subjects exists, if not array object returns with a length = 0
    }

    //----------------------------------------Get subject name and credits----------------------------------------------
    public Subject getSubjectNameAndCredits(Subject subject) {
        Subject subjectNameAndCredits = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select title,credits,l.name,sublecid from subject s,lecturer l,subject_lecturer sl where l.lecid=sl.lecid && s.subid=sl.subid && s.subid=? && current=true");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                subjectNameAndCredits = new Subject();
                subjectNameAndCredits.setSubjectName(rst.getString(1));//---Set table row data to subject model object
                subjectNameAndCredits.setCredits(rst.getInt(2));//---Set table row data to subject model object
                subjectNameAndCredits.setLecturerName(rst.getString(3));//---Set table row data to subject model object
                subjectNameAndCredits.setSublecId(rst.getInt(4));//---Set table row data to subject model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjectNameAndCredits;//---Return subject object if subject id exist, if not return null
    }

    //---------------------------------Get all subjects via degree and semester-----------------------------------------
    public List<Subject> getAllSubjectsViaDegreeAndSemester(Semester semester){
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select subid,title from subject where semid=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, semester.getSemid());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Subject subjectObj = new Subject();//---Creates a subject object
                subjectObj.setSubjectId(rst.getString(1));//---Set table row data to subject model object
                subjectObj.setSubjectName(rst.getString(2));//---Set table row data to subject model object
                subjects.add(subjectObj);//---Add subject object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjects;//---Return subjects array object with a length > 0 if subjects exists, if not array object returns with a length = 0
    }
}
