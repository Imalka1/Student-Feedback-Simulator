package controller.db_controller;

import db.DBConnection;
import model.Batch;
import model.Semester;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchController {

    public List<Batch> getIntakes() {
        List<Batch> batches = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
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
