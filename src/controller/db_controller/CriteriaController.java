package controller.db_controller;

import db.DBConnection;
import model.CriteriaDTO;

import java.sql.*;
import java.util.ArrayList;

public class CriteriaController {

    public static ArrayList<CriteriaDTO> getCriteriaHeadings() {
        ArrayList<CriteriaDTO> criteriaDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            Statement createStatement = connection.createStatement();
            ResultSet rst = createStatement.executeQuery("select echid,text from evaluation_criteria_heading");
            while (rst.next()) {
                CriteriaDTO criteriaDTO = new CriteriaDTO();
                criteriaDTO.setEchid(rst.getInt(1));
                criteriaDTO.setCriteriaHeadingName(rst.getString(2));
                criteriaDTOS.add(criteriaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return criteriaDTOS;
    }

    public static ArrayList<CriteriaDTO> getCriterias(int echid) {
        ArrayList<CriteriaDTO> criteriaDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select ecid,text from evaluation_criteria where echid=?");
            preparedStatement.setObject(1, echid);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                CriteriaDTO criteriaDTO = new CriteriaDTO();
                criteriaDTO.setEcid(rst.getInt(1));
                criteriaDTO.setCriteriaName(rst.getString(2));
                criteriaDTOS.add(criteriaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return criteriaDTOS;
    }
}
