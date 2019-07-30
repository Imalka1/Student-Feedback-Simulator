package controller.url_controller.admin;

import controller.db_controller.MarkController;
import model.Mark;
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

@WebServlet(urlPatterns = "/load_marks")
public class LoadMarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mark mark=new Mark();
        mark.setSubid(req.getParameter("subjectId"));
        mark.setLecid(req.getParameter("lecturerId"));
        mark.setDateOfSubmission(req.getParameter("dateOfSubmission"));
        List<Mark> marksViaSubjectAndLecturer = new MarkController().getMarksViaSubjectAndLecturer(mark);
        JSONObject obj = new JSONObject();
        JSONArray datesJson = new JSONArray();
        for (Mark markObj : marksViaSubjectAndLecturer) {
            JSONObject dateJson = new JSONObject();
            dateJson.put("EvaluationCriteria", markObj.getCriteria());
            dateJson.put("Marks", markObj.getMarks());
            dateJson.put("StudentsCount", markObj.getStudentsCount());
            datesJson.add(dateJson);
        }
        obj.put("Marks", datesJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
