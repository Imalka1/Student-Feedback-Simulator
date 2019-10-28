package controller.url_controller.admin.lecturer;

import controller.db_controller.SubjectLecturerController;
import db.DBConnection;
import model.SubjectLecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/change_current_lecturer")
public class ChangeCurrentLecturerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lecturerId = req.getParameter("lecturerId").trim();
        String subjectId = req.getParameter("subjectId").trim();

        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            connection.setAutoCommit(false);//---Temporary disable automatically commit(write) data on database

            //--------------------------------------Set data to model object--------------------------------------------
            SubjectLecturer subjectLecturer = new SubjectLecturer();
            subjectLecturer.setSubjectId(subjectId);
            subjectLecturer.setLecturerId(lecturerId);
            subjectLecturer.setCurrent(true);

            if (new SubjectLecturerController().setAllSubjectLecturersCurrentStatusToFalse(subjectLecturer)) {//---Call the db server (UserController(db_controller)) to update user email
                if (new SubjectLecturerController().setSubjectLecturersCurrentStatusToTrue(subjectLecturer)) {
                    connection.commit();//---If all data were sent for both tables, then commit (write) data on both tables
                    resp.getWriter().println(true);//---Reply / Response
                    return;
                } else {
                    connection.rollback();//---If user insertion false, this removes user data on database
                }
            } else {
                connection.rollback();//---If user insertion false, this removes user data on database
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
        resp.getWriter().println(false);
    }
}