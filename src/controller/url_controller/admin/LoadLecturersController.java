package controller.url_controller.admin;

import controller.db_controller.SubjectController;
import controller.db_controller.SubjectLecturerController;
import model.Lecturer;
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

@WebServlet(urlPatterns = "/load_lecturers")
public class LoadLecturersController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = new Subject();
        subject.setSubjectId(req.getParameter("subjectId"));
        List<Lecturer> allLecturersViaSubject = new SubjectLecturerController().getAllLecturersViaSubject(subject);
        JSONObject obj = new JSONObject();
        JSONArray lecturersJson = new JSONArray();
        for (Lecturer lecturer : allLecturersViaSubject) {
            JSONObject lecturerJson = new JSONObject();
            lecturerJson.put("LecturerId", lecturer.getLecId());
            lecturerJson.put("LecturerName", lecturer.getLecturerName());
            lecturersJson.add(lecturerJson);
        }
        obj.put("Lecturers", lecturersJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
