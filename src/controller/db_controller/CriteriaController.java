package controller.db_controller;

import db.DBConnection;
import model.Criteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriteriaController {

    //------------------------------------Get all criterias via criteria heading id-------------------------------------
    public List<Criteria> getCriterias() {
        List<Criteria> criterias = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select ecId,text,criteriaHeading from evaluation_criteria");//---Prepare sql as a java object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Criteria criteriaObj = new Criteria();//---Creates a criteria object
                criteriaObj.setEcId(rst.getInt(1));//---Set table row data to criteria model object
                criteriaObj.setCriteriaName(rst.getString(2));//---Set table row data to criteria model object
                criteriaObj.setCriteriaHeading(rst.getBoolean(3));//---Set table row data to criteria model object
                criterias.add(criteriaObj);//---Add criteria object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return criterias;//---Return criterias array object with a length > 0 if criterias exists, if not array object returns with a length = 0
    }
}
