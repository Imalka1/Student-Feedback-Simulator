package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Semester;
import model.Subject;
import model.SubjectDegree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDegreeController {

    //---------------------------------------------------Add subject----------------------------------------------------
    public boolean addSubjectDegree(SubjectDegree subjectDegree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into subject_degree (subjectId,degreeId) " +
                    "values (?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, subjectDegree.getSubjectId());//---Set values to sql object
            preparedStatement.setObject(2, subjectDegree.getDegreeId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete subject---------------------------------------------------
    public boolean deleteSubjectDegree(SubjectDegree subjectDegree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("delete from subject_degree where subjectId=? && degreeId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subjectDegree.getSubjectId());//---Set values to sql object
            preparedStatement.setObject(2, subjectDegree.getDegreeId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    public List<SubjectDegree> getSubjectsViaDegree(Degree degree) {
        List<SubjectDegree> subjects = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select s.subjectId,s.title,sem.name from subject_degree sd,subject s,semester sem where sd.subjectId=s.subjectId && s.semesterId=sem.semesterId && degreeId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, degree.getDegreeId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                SubjectDegree subjectDegree = new SubjectDegree();
                subjectDegree.setSubjectId(rst.getString(1));
                subjectDegree.setSubjectName(rst.getString(2));
                subjectDegree.setSemesterName(rst.getString(3));
                subjects.add(subjectDegree);
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return subjects;//---Return subjects array object with a length > 0 if subjects exists, if not array object returns with a length = 0
    }
}