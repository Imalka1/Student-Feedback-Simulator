package controller.db_controller;

import db.DBConnection;
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

    //-------------------------------------------------Update subject---------------------------------------------------
    public boolean updateSubjectDegree(SubjectDegree subjectDegree) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update subject_degree " +
                    "set degreeId=? " +
                    "where subjectId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subjectDegree.getDegreeId());//---Set values to sql object
            preparedStatement.setObject(2, subjectDegree.getSubjectId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete subject---------------------------------------------------
    public boolean deleteSubjectDegree(Subject subject) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement1 = connection.prepareStatement("select count(subdegid) from subject_degree where subjectId=?");//---Prepare sql as a java object
            preparedStatement1.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement1.executeQuery();//---Execute sql and store result
            if (rst.next()) {
                if (rst.getInt(1) > 0) {
                    PreparedStatement preparedStatement2 = connection.prepareStatement("delete from subject_degree where subjectId=?");//---Prepare sql as a java object
                    preparedStatement2.setObject(1, subject.getSubjectId());//---Set values to sql object
                    if (preparedStatement2.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                        return true;
                    }
                } else {
                    return true;
                }
            }

        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }
}