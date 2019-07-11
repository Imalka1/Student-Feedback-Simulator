package controller.url_controller.student;

import controller.db_controller.StudentController;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete_student")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String regNo = req.getParameter("regNo").trim();
        Student student = new Student();
        student.setUid(regNo);

        PrintWriter writer = resp.getWriter();
        if (new StudentController().deleteStudent(student)) {
            writer.println(true);
        } else {
            writer.println(false);
        }
    }
}
