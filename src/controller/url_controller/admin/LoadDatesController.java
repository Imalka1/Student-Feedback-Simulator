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
import java.util.List;

@WebServlet(urlPatterns = "/load_dates")//---URL extension which mapped to this servlet object
public class LoadDatesController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        SubjectLecturer subjectLecturer = new SubjectLecturer();
        subjectLecturer.setSubjectId(req.getParameter("subjectId"));
        subjectLecturer.setLecturerId(req.getParameter("lecturerId"));

        List<Mark> allDatesViaSubjectAndLecturer = new SubjectLecturerController().getAllDatesViaSubjectAndLecturer(subjectLecturer);//---Call the db server (SubjectLecturerController(db_controller)) to get all dates via subject lecturer

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray datesJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Mark mark : allDatesViaSubjectAndLecturer) {
            JSONObject dateJson = new JSONObject();//---Creates a JSON object {}
            dateJson.put("DateOfSubmission", mark.getDateOfSubmission());//---Add data to JSON {"DateOfSubmission":"2019-02-05"}
            datesJson.add(dateJson);//---Add JSON object to JSON array [{"DateOfSubmission":"2019-02-05"},{"DateOfSubmission":"2019-03-06"}]
        }
        obj.put("Dates", datesJson);//---Add JSON array to JSON object {"Dates":[{"DateOfSubmission":"2019-02-05"}]} / {"Dates":[{"DateOfSubmission":"2019-02-05"},{"DateOfSubmission":"2019-03-06"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
