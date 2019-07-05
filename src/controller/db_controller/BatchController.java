package controller.db_controller;

import db.DBConnection;
import model.BatchDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<BatchDTO> getYears() {
        List<BatchDTO> batchDTOs = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct year(intake) from batch");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                BatchDTO batchDTO = new BatchDTO();
                batchDTO.setYear(rst.getInt(1));
                batchDTOs.add(batchDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batchDTOs;
    }

    public static List<BatchDTO> getAllBatches() {
        List<BatchDTO> batchDTOs = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select batchid,name from batch");
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                BatchDTO batchDTO = new BatchDTO();
                batchDTO.setBatchid(rst.getInt(1));
                batchDTO.setBatchName(rst.getString(2));
                batchDTOs.add(batchDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batchDTOs;
    }
}
