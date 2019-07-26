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

@WebServlet(urlPatterns = "/update_student")
public class UpdateStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degreeId = Integer.parseInt(req.getParameter("degreeId").trim());
        int batchId = Integer.parseInt(req.getParameter("batchId").trim());
        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());
        String regNo = req.getParameter("regNo").trim();
        String studetName = req.getParameter("studetName").trim();
        String nationalId = req.getParameter("nationalId").trim();
        String emailAddress = req.getParameter("emailAddress").trim();
        Student student = new Student();
        student.setUid(regNo);
        student.setStudentName(studetName);
        student.setNationalId(nationalId);
        student.setDegId(degreeId);
        student.setBatchId(batchId);
        student.setSemesterId(semesterId);
        student.setEmailAddress(emailAddress);

        PrintWriter writer = resp.getWriter();
        if (new StudentController().updateStudent(student)) {
            writer.println(true);
        } else {
            writer.println(false);
        }
    }
}
