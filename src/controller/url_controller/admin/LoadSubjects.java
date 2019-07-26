package controller.url_controller.admin;

import controller.db_controller.SubjectController;
import model.Semester;
import model.Subject;
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

@WebServlet(urlPatterns = "/load_subjects")
public class LoadSubjects extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Semester semester = new Semester();
        semester.setSemid(Integer.parseInt(req.getParameter("semesterId")));
        List<Subject> allSubjectsViaDegree = new SubjectController().getAllSubjectsViaDegreeAndSemester(semester);
        JSONObject obj = new JSONObject();
        JSONArray subjectsJson = new JSONArray();
        for (Subject subjectObj : allSubjectsViaDegree) {
            JSONObject subjectJson = new JSONObject();
            subjectJson.put("SubjectId", subjectObj.getSubjectId());
            subjectJson.put("SubjectName", subjectObj.getSubjectName());
            subjectsJson.add(subjectJson);
        }
        obj.put("Subjects", subjectsJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
