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

    //---------------------------------------------Get all intakes------------------------------------------------------
    public List<Batch> getIntakes() {
        List<Batch> batches = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select batchid,name from batch");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Batch batch = new Batch();//---Creates a batch object
                batch.setBatchid(rst.getInt(1));//---Set table row data to batch model object
                batch.setBatchName(rst.getString(2));//---Set table row data to batch model object
                batches.add(batch);//---Add batch object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return batches;//---Return batches array object with a length > 0 if batches exists, if not array object returns with a length = 0
    }
}
