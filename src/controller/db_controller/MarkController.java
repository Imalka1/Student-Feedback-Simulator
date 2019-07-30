package controller.db_controller;

import db.DBConnection;
import model.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkController {

    //---------------------------------------------Add mark-------------------------------------------------------------
    public boolean addMarks(Mark mark) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("insert into marks values (?,?,?,?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, mark.getUid());//---Set values to sql object
            preparedStatement.setObject(2, mark.getEcid());//---Set values to sql object
            preparedStatement.setObject(3, mark.getDateOfSubmission());//---Set values to sql object
            preparedStatement.setObject(4, mark.getSublecid());//---Set values to sql object
            preparedStatement.setObject(5, mark.getMarks());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //--------------------------------------Get marks via subject and lecturer------------------------------------------
    public List<Mark> getMarksViaSubjectAndLecturer(Mark mark) {
        List<Mark> marks = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select text,sum(marks),count(marks) " +
                    "from evaluation_criteria " +
                    "left join marks on evaluation_criteria.ecid=marks.ecid && dateOfSubmission=? && " +
                    "sublecid=(select sublecid from subject_lecturer where lecid=? && subid=?) " +
                    "group by evaluation_criteria.ecid asc");//---Prepare sql as a java object
            preparedStatement.setObject(1, mark.getDateOfSubmission());
            preparedStatement.setObject(2, mark.getLecid());
            preparedStatement.setObject(3, mark.getSubid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Mark markObj = new Mark();//---Creates a mark object
                markObj.setCriteria(rst.getString(1));//---Set table row data to mark model object
                markObj.setMarks(rst.getInt(2));//---Set table row data to mark model object
                markObj.setStudentsCount(rst.getInt(3));//---Set table row data to mark model object
                marks.add(markObj);//---Add mark object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return marks;//---Return marks array object with a length > 0 if marks exists, if not array object returns with a length = 0
    }
}
