package controller.url_controller.admin.lecturer;

import controller.db_controller.LecturerController;
import controller.db_controller.SubjectController;
import controller.db_controller.SubjectDegreeController;
import controller.db_controller.SubjectLecturerController;
import db.DBConnection;
import model.Lecturer;
import model.Subject;
import model.SubjectLecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/add_lecturer")
public class AddLecturerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //---------------------------------Retrieve data which submitted to the server----------------------------------
        String lecturerId = req.getParameter("lecturerId").trim();
        String lecturerName = req.getParameter("lecturerName").trim();
        String subjectId = req.getParameter("subjectId").trim();

        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            connection.setAutoCommit(false);//---Temporary disable automatically commit(write) data on database

            //--------------------------------------Set data to model object--------------------------------------------
            Lecturer lecturer = new Lecturer();
            lecturer.setLecturerId(lecturerId);
            lecturer.setLecturerName(lecturerName);

            if (new LecturerController().addLecturer(lecturer)) {//---Call the db server (SubjectController(db_controller)) to add subject

                //--------------------------------------Set data to model object----------------------------------------
                SubjectLecturer subjectLecturer = new SubjectLecturer();
                subjectLecturer.setSubjectId(subjectId);
                subjectLecturer.setLecturerId(lecturerId);
                subjectLecturer.setCurrent(true);

                if (new SubjectLecturerController().addSubjectLecturer(subjectLecturer)) {//---Call the db server (StudentController(db_controller)) to add student
                    connection.commit();//---If all data were sent for both tables, then commit (write) data on both tables
                    resp.getWriter().println(true);//---Reply / Response
                    return;
                } else {
                    connection.rollback();//---If student insertion false, this removes both student and user data on database
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
        resp.getWriter().println(false);//---Reply / Response
    }
}
