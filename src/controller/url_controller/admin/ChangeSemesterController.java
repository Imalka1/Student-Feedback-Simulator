package controller.url_controller.admin;

import controller.db_controller.StudentController;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/change_semester")//---URL extension which mapped to this servlet object
public class ChangeSemesterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());
        int facultyId = Integer.parseInt(req.getParameter("facultyId").trim());
        int batchId = Integer.parseInt(req.getParameter("batchId").trim());

        //--------------------------------------Set data to model object------------------------------------------------
        Student student = new Student();
        student.setSemesterId(semesterId);
        student.setFacultyId(facultyId);
        student.setBatchId(batchId);

        if (new StudentController().changeSemester(student)) {//---Call the db server (StudentController(db_controller)) to change semester
            resp.getWriter().println(true);
        } else {
            resp.getWriter().println(false);
        }
    }
}
