package controller.url_controller.admin;

import controller.db_controller.SubjectLecturerController;
import model.Mark;
import model.SubjectLecturer;
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

@WebServlet(urlPatterns = "/load_dates")
public class LoadDatesController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectLecturer subjectLecturer=new SubjectLecturer();
        subjectLecturer.setSubid(req.getParameter("subjectId"));
        subjectLecturer.setLecid(req.getParameter("lecturerId"));
        List<Mark> allDatesViaSubjectAndLecturer = new SubjectLecturerController().getAllDatesViaSubjectAndLecturer(subjectLecturer);
        JSONObject obj = new JSONObject();
        JSONArray datesJson = new JSONArray();
        for (Mark mark : allDatesViaSubjectAndLecturer) {
            JSONObject dateJson = new JSONObject();
            dateJson.put("DateOfSubmission", mark.getDateOfSubmission());
            datesJson.add(dateJson);
        }
        obj.put("Dates", datesJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
