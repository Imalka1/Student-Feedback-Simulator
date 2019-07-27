package controller.url_controller.admin;

import controller.db_controller.StudentController;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/change_semester")
public class ChangeSemester extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());
        int facultyId = Integer.parseInt(req.getParameter("facultyId").trim());
        int batchId = Integer.parseInt(req.getParameter("batchId").trim());
        Student student = new Student();
        student.setSemesterId(semesterId);
        student.setFacultyId(facultyId);
        student.setBatchId(batchId);

        PrintWriter writer = resp.getWriter();
        if (new StudentController().changeSemester(student)) {
            writer.println(true);
        } else {
            writer.println(false);
        }
    }
}
