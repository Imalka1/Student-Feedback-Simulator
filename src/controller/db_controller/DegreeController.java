package controller.db_controller;

import db.DBConnection;
import model.Degree;
import model.Semester;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeController {

    public List<Degree> getAllDegreesViaSubject(Subject subject) {
        List<Degree> degrees = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select d.degreeId,d.name from subject_degree sd,degree d where sd.degreeId=d.degreeId && subjectId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, subject.getSubjectId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Degree degree = new Degree();
                degree.setDegreeId(rst.getInt(1));
                degree.setDegreeName(rst.getString(2));
                degrees.add(degree);
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return degrees;//---Return subjects array object with a length > 0 if subjects exists, if not array object returns with a length = 0
    }

    //----------------------------------------Get all degrees via faculty-----------------------------------------------
    public List<Degree> getAllDegrees() {
        List<Degree> degrees = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select degreeId,name from degree");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Degree degree = new Degree();//---Creates a degree object
                degree.setDegreeId(rst.getInt(1));//---Set table row data to degree model object
                degree.setDegreeName(rst.getString(2));//---Set table row data to degree model object
                degrees.add(degree);//---Add degree object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return degrees;//---Return degrees array object with a length > 0 if degrees exists, if not array object returns with a length = 0
    }
}
