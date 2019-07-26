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
    public boolean addMarks(List<Mark> marks) {
        int count = 0;
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            connection.setAutoCommit(false);
            for (Mark mark : marks) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into marks values (?,?,?,?,?)");
                preparedStatement.setObject(1, mark.getUid());
                preparedStatement.setObject(2, mark.getEcid());
                preparedStatement.setObject(3, mark.getDateOfSubmission());
                preparedStatement.setObject(4, mark.getSublecid());
                preparedStatement.setObject(5, mark.getMarks());
                if (preparedStatement.executeUpdate() > 0) {
                    count++;
                }
            }
            if (count == marks.size()) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Mark> getMarksViaSubjectAndLecturer(Mark mark) {
        List<Mark> marks = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select text,sum(marks),count(marks) " +
                    "from evaluation_criteria " +
                    "left join marks on evaluation_criteria.ecid=marks.ecid && " +
                    "dateOfSubmission=? && " +
                    "sublecid=(select sublecid from subject_lecturer where lecid=? && subid=?) " +
                    "group by evaluation_criteria.ecid asc");
            preparedStatement.setObject(1, mark.getDateOfSubmission());
            preparedStatement.setObject(2, mark.getLecid());
            preparedStatement.setObject(3, mark.getSubid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Mark markObj = new Mark();
                markObj.setCriteria(rst.getString(1));
                markObj.setMarks(rst.getInt(2));
                markObj.setStudentsCount(rst.getInt(3));
                marks.add(markObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }
}
