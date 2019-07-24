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

@WebServlet(urlPatterns = "/add_student")
public class AddStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degree = Integer.parseInt(req.getParameter("degree").trim());
        int batch = Integer.parseInt(req.getParameter("batch").trim());
        String regNo = req.getParameter("regNo").trim();
        String studetName = req.getParameter("studetName").trim();
        String nationalId = req.getParameter("nationalId").trim();
        String emailAddress = req.getParameter("emailAddress").trim();
        Student student = new Student();
        student.setUid(regNo);
        student.setStudentName(studetName);
        student.setNationalId(nationalId);
        student.setDegId(degree);
        student.setBatchId(batch);
        student.setEmailAddress(emailAddress);

        PrintWriter writer = resp.getWriter();
        if (new StudentController().addStudent(student)) {
            writer.println(true);
        } else {
            writer.println(false);
        }
    }
}
