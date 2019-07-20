package controller.db_controller;

import db.DBConnection;
import model.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchController {

    public Batch getYearAndMonthDiff(String uid) {
        Batch batch = new Batch();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select year(curdate())-year(b.intake),month(curdate())-month(b.intake) from student s,batch b,user u where s.batchid=b.batchid && u.uid=s.uid && s.uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                batch.setYearDiff(rst.getInt(1));
                batch.setMonthDiff(rst.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    public List<Batch> getYears() {
        List<Batch> batches = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct year(intake) from batch");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Batch batch = new Batch();
                batch.setYear(rst.getInt(1));
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select batchid,name from batch");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Batch batch = new Batch();
                batch.setBatchid(rst.getInt(1));
                batch.setBatchName(rst.getString(2));
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }
}
