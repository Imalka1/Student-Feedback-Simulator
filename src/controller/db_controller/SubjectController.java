package controller.db_controller;

import db.DBConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectController {

    //------------------------------------Get subjects via semester and degree------------------------------------------
    public List<Subject> getSubjectsViaSemesterAndDegree(Student student) {
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select subject.subjectId,title,credits,lecturer.name,allowed,count(marks) from subject " +
                    "inner join subject_lecturer on subject.subjectId=subject_lecturer.subjectId && semesterId=? " +
                    "inner join lecturer on subject_lecturer.lecturerId=lecturer.lecturerId && current=true " +
                    "inner join subject_degree on subject.subjectId=subject_degree.subjectId && degreeId=? " +
                    "left join marks on marks.subjectLecturerId=subject_lecturer.subjectLecturerId && uId=? && dateOfSubmission=curdate() group by 1");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(2, student.getDegreeId());//---Set values to sql object
            preparedStatement.setObject(3, student.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Subject subjectSemesterAndDegree = new Subject();//---Creates a subject object
                subjectSemesterAndDegree.setSubjectId(rst.getString(1));//---Set table row data to subject model object
                subjectSemesterAndDegree.setSubjectName(rst.getString(2));//---Set table row data to subject model object
                subjectSemesterAndDegree.setCredits(rst.getInt(3));//---Set table row data to subject model object
                subjectSemesterAndDegree.setLecturerName(rst.getString(4));//---Set table row data to subject model object
                subjectSemesterAndDegree.setAllowed(rst.getBoolean(5) && rst.getInt(6) == 0);//---Set table row data to subject model object
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
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select title,credits,l.name,subjectLecturerId " +
                    "from subject s,lecturer l,subject_lecturer sl " +
                    "where l.lecturerId=sl.lecturerId && s.subjectId=sl.subjectId && s.subjectId=? && current=true");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                subjectNameAndCredits = new Subject();
                subjectNameAndCredits.setSubjectName(rst.getString(1));//---Set table row data to subject model object
                subjectNameAndCredits.setCredits(rst.getInt(2));//---Set table row data to subject model object
                subjectNameAndCredits.setLecturerName(rst.getString(3));//---Set table row data to subject model object
                subjectNameAndCredits.setSubjectLecturerId(rst.getInt(4));//---Set table row data to subject model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjectNameAndCredits;//---Return subject object if subject id exist, if not return null
    }

    //---------------------------------Get all subjects via degree and semester-----------------------------------------
    public List<Subject> getAllSubjectsViaDegreeAndSemester(Semester semester) {
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select subjectId,title from subject where semesterId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, semester.getSemesterId());//---Set values to sql object
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

//    public boolean subjectIsAllowedAndMarksAreNotSubmitted(Mark mark) {
//        try {
//            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
//            PreparedStatement preparedStatement = connection.prepareStatement("select count(marks),allowed from subject s,marks m,subject_lecturer sl where s.subid=sl.subid && sl.sublecid=m.sublecid && uid=? && s.subid=? && dateOfSubmission=?");//---Prepare sql as a java object
//            preparedStatement.setObject(1, mark.getuId());//---Set values to sql object
//            preparedStatement.setObject(2, mark.getSubjectId());//---Set values to sql object
//            preparedStatement.setObject(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//---Set values to sql object
//            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
//            if (rst.next()) {//---Execute sql and returns whether it was executed or not
//                if (rst.getInt(1) == 0 && rst.getBoolean(2)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (SQLException e) {//---Catch if any sql exception occurred
//            e.printStackTrace();
//        }
//        return true;
//    }
}
