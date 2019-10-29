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
                    "select subject.subjectId,title,credits,lecturer.lecturer_name,allowed,count(marks) " +
                    "from subject inner join subject_lecturer on subject.subjectId=subject_lecturer.subjectId && semesterId=(select sem.semesterId from student s,semester sem where s.semesterId=sem.semesterId && s.uId=?)" +
                    "inner join lecturer on subject_lecturer.lecturerId=lecturer.lecturerId && current=true " +
                    "inner join subject_degree on subject.subjectId=subject_degree.subjectId && degreeId=(select d.degreeId from student s,degree d where s.degreeId=d.degreeId && s.uId=?)" +
                    "left join marks on marks.subjectId=subject_lecturer.subjectId && marks.lecturerId=subject_lecturer.lecturerId && uId=? && dateOfSubmission=curdate() group by 1");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            preparedStatement.setObject(2, student.getuId());//---Set values to sql object
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

    //------------------------------------Get subjects via semester and degree------------------------------------------
    public List<Subject> getAllowedSubjectsViaSemesterAndDegree(Student student) {
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select subject.subjectId,title,credits,lecturer.lecturer_name,allowed,count(marks) " +
                    "from subject inner join subject_lecturer on subject.subjectId=subject_lecturer.subjectId && semesterId=(select sem.semesterId from student s,semester sem where s.semesterId=sem.semesterId && s.uId=?)" +
                    "inner join lecturer on subject_lecturer.lecturerId=lecturer.lecturerId && current=true " +
                    "inner join subject_degree on subject.subjectId=subject_degree.subjectId && degreeId=(select d.degreeId from student s,degree d where s.degreeId=d.degreeId && s.uId=?)" +
                    "left join marks on marks.subjectId=subject_lecturer.subjectId && marks.lecturerId=subject_lecturer.lecturerId && uId=? && dateOfSubmission=curdate() group by 1");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            preparedStatement.setObject(2, student.getuId());//---Set values to sql object
            preparedStatement.setObject(3, student.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Subject subjectSemesterAndDegree = new Subject();//---Creates a subject object
                subjectSemesterAndDegree.setSubjectId(rst.getString(1));//---Set table row data to subject model object
                subjectSemesterAndDegree.setSubjectName(rst.getString(2));//---Set table row data to subject model object
                subjectSemesterAndDegree.setCredits(rst.getInt(3));//---Set table row data to subject model object
                subjectSemesterAndDegree.setLecturerName(rst.getString(4));//---Set table row data to subject model object
                subjectSemesterAndDegree.setAllowed(rst.getBoolean(5) && rst.getInt(6) > 0);//---Set table row data to subject model object
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
                    "select title,credits,l.lecturer_name,s.subjectId,l.lecturerId " +
                    "from subject s,lecturer l,subject_lecturer sl " +
                    "where l.lecturerId=sl.lecturerId && s.subjectId=sl.subjectId && s.subjectId=? && current=true");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                subjectNameAndCredits = new Subject();
                subjectNameAndCredits.setSubjectName(rst.getString(1));//---Set table row data to subject model object
                subjectNameAndCredits.setCredits(rst.getInt(2));//---Set table row data to subject model object
                subjectNameAndCredits.setLecturerName(rst.getString(3));//---Set table row data to subject model object
                subjectNameAndCredits.setSubjectId(rst.getString(4));//---Set table row data to subject model object
                subjectNameAndCredits.setLecturerId(rst.getString(5));//---Set table row data to subject model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjectNameAndCredits;//---Return subject object if subject id exist, if not return null
    }

    //---------------------------------Get all subjects via degree and semester-----------------------------------------
    public List<Subject> getAllSubjectsViaSemester(Semester semester) {
        List<Subject> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select subjectId,title,credits,allowed from subject where semesterId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, semester.getSemesterId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Subject subjectObj = new Subject();//---Creates a subject object
                subjectObj.setSubjectId(rst.getString(1));//---Set table row data to subject model object
                subjectObj.setSubjectName(rst.getString(2));//---Set table row data to subject model object
                subjectObj.setCredits(rst.getInt(3));//---Set table row data to subject model object
                subjectObj.setAllowed(rst.getBoolean(4));//---Set table row data to subject model object
                subjects.add(subjectObj);//---Add subject object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjects;//---Return subjects array object with a length > 0 if subjects exists, if not array object returns with a length = 0
    }

    public boolean changeAllow(Subject subject) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update subject " +
                    "set allowed=? " +
                    "where subjectId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.isAllowed());//---Set values to sql object
            preparedStatement.setObject(2, subject.getSubjectId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //---------------------------------------------------Add subject----------------------------------------------------
    public boolean addSubject(Subject subject) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into subject (subjectId,semesterId,title,credits,allowed) " +
                    "values (?,?,?,?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            preparedStatement.setObject(2, subject.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(3, subject.getSubjectName());//---Set values to sql object
            preparedStatement.setObject(4, subject.getCredits());//---Set values to sql object
            preparedStatement.setObject(5, false);//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Update subject---------------------------------------------------
    public boolean updateSubject(Subject subject) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update subject " +
                    "set semesterId=?,title=?,credits=? " +
                    "where subjectId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(2, subject.getSubjectName());//---Set values to sql object
            preparedStatement.setObject(3, subject.getCredits());//---Set values to sql object
            preparedStatement.setObject(4, subject.getSubjectId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete subject---------------------------------------------------
    public boolean deleteSubject(Subject subject) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("delete from subject where subjectId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }
}
