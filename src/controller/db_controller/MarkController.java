package controller.db_controller;

import db.DBConnection;
import model.Mark;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MarkController {
    public boolean addMarks(List<Mark> marks) {
        int count = 0;
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            for (Mark mark : marks) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into marks values (?,?,?,?,?)");
                preparedStatement.setObject(1, mark.getUid());
                preparedStatement.setObject(2, mark.getEcid());
                preparedStatement.setObject(3, mark.getDateOfSubmitted());
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
}
