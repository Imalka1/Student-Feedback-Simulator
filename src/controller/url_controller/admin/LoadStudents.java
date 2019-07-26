package controller.url_controller.admin;

import controller.db_controller.StudentController;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/load_students")
public class LoadStudents extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degreeId = Integer.parseInt(req.getParameter("degree").trim());
        int batchId = Integer.parseInt(req.getParameter("batch").trim());
        Student student=new Student();
        student.setDegId(degreeId);
        student.setBatchId(batchId);
        List<Student> allStudents = new StudentController().getAllStudents(student);
        JSONObject obj = new JSONObject();
        JSONArray studentsJson = new JSONArray();
        for (Student studentObj : allStudents) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("RegId", studentObj.getUid());
            studentJson.put("StudentName", studentObj.getStudentName());
            studentJson.put("NationalId", studentObj.getNationalId());
            studentJson.put("EmailAddress", studentObj.getEmailAddress());
            studentsJson.add(studentJson);
        }
        obj.put("Students",studentsJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
