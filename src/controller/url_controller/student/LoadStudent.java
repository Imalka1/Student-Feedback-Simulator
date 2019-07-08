package controller.url_controller.student;

import controller.db_controller.StudentController;
import model.StudentDTO;
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
public class LoadStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degree = Integer.parseInt(req.getParameter("degree").trim());
        int year = Integer.parseInt(req.getParameter("year").trim());
        int batch = Integer.parseInt(req.getParameter("batch").trim());
        int pageNo = Integer.parseInt(req.getParameter("page_no").trim());
        List<StudentDTO> allStudents = new StudentController().getAllStudents(degree, batch, year, pageNo);
        JSONObject obj = new JSONObject();
        JSONArray studentsJson = new JSONArray();
        for (StudentDTO studentDTO : allStudents) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("RegId", studentDTO.getUid());
            studentJson.put("StudentName", studentDTO.getStudentName());
            studentJson.put("NationalId", studentDTO.getNationalId());
            studentsJson.add(studentJson);
        }
        obj.put("Students",studentsJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
