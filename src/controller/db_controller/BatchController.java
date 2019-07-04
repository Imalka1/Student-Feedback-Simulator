package controller.db_controller;

import db.DBConnection;
import model.BatchDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchController {

    public static BatchDTO getYearAndMonthDiff(String uid) {
        BatchDTO batchDTO = new BatchDTO();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select year(curdate())-year(b.intake),month(curdate())-month(b.intake) from student s,batch b,user u where s.batchid=b.batchid && u.uid=s.uid && s.uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                batchDTO.setYearDiff(rst.getInt(1));
                batchDTO.setMonthDiff(rst.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batchDTO;
    }
}
