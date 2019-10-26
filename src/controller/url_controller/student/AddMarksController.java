package controller.url_controller.student;

import controller.db_controller.MarkController;
import db.DBConnection;
import model.Mark;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/processMarks")//---URL extension which mapped to this servlet object

//------------------------------Add marks (This process add marks using transaction process)----------------------------
public class AddMarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        JSONArray jsonObjectIds = (JSONArray) JSONValue.parse(req.getParameter("ecids"));//---Convert JSON text to JSON object (Criteria ids)
        JSONArray jsonObjectMarks = (JSONArray) JSONValue.parse(req.getParameter("marks"));//---Convert JSON text to JSON object (Marks)
        String dateOfSubmission = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//---Get current date

        int count = 0;//---Maintain the count of marks which sent to the database
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            connection.setAutoCommit(false);//---Temporary disable automatically commit(write) data on database

            for (int i = 0; i < jsonObjectIds.size(); i++) {

                //--------------------------------------Set data to model object----------------------------------------
                Mark mark = new Mark();
                mark.setuId(req.getParameter("uid"));
                mark.setSubjectLecturerId(Integer.parseInt(req.getParameter("sublecid")));
                mark.setEcId(Integer.parseInt(jsonObjectIds.get(i).toString()));
                mark.setMarks(Integer.parseInt(jsonObjectMarks.get(i).toString()));
                mark.setDateOfSubmission(dateOfSubmission);

                if (new MarkController().addMarks(mark)) {//---Call the db server (MarkController(db_controller)) to add mark
                    count++;//---Count the marks which sent to the database
                }
            }

            if (count == jsonObjectIds.size()) {//---Check whether the count of submitted marks and the count of marks which received from ui are equal
                connection.commit();//---If all data were sent for marks table, then commit (write) data on marks table
                resp.getWriter().println(true);//---Reply / Response
                return;
            } else {
                connection.rollback();//---If all marks insertion false, this removes all marks on database
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        } finally {//---Runs if any error occurred or not
            try {
                connection.setAutoCommit(true);//---The connection should be enabled for automatic committing if an error occurred or not
            } catch (SQLException e) {//---Catch if any sql exception occurred
                e.printStackTrace();
            }
        }
        resp.getWriter().println(false);//---Reply / Response
    }
}
