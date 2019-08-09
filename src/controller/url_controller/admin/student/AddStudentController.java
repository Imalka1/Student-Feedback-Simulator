package controller.url_controller.admin.student;

import controller.db_controller.StudentController;
import controller.db_controller.UserController;
import db.DBConnection;
import model.Student;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/add_student")//---URL extension which mapped to this servlet object

//------------Add student (This process add user and student simultaneously using transaction process)------------------
public class AddStudentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        int degreeId = Integer.parseInt(req.getParameter("degreeId").trim());
        int batchId = Integer.parseInt(req.getParameter("batchId").trim());
        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());
        String regNo = req.getParameter("regNo").trim();
        String studetName = req.getParameter("studetName").trim();
        String nationalId = req.getParameter("nationalId").trim();
        String emailAddress = req.getParameter("emailAddress").trim();

        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            connection.setAutoCommit(false);//---Temporary disable automatically commit(write) data on database

            //--------------------------------------Set data to model object--------------------------------------------
            User user = new User();
            user.setuId(regNo);
            user.setPassword(nationalId);
            user.setEmailAddress(emailAddress);

            if (new UserController().addUser(user)) {//---Call the db server (UserController(db_controller)) to add user

                //--------------------------------------Set data to model object----------------------------------------
                Student student = new Student();
                student.setuId(regNo);
                student.setStudentName(studetName);
                student.setNationalId(nationalId);
                student.setDegreeId(degreeId);
                student.setBatchId(batchId);
                student.setSemesterId(semesterId);

                if (new StudentController().addStudent(student)) {//---Call the db server (StudentController(db_controller)) to add student
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
