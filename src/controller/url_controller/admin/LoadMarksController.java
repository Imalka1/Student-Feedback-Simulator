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

@WebServlet(urlPatterns = "/load_marks")//---URL extension which mapped to this servlet object
public class LoadMarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        Mark mark = new Mark();
        mark.setSubid(req.getParameter("subjectId"));
        mark.setLecid(req.getParameter("lecturerId"));
        mark.setDateOfSubmission(req.getParameter("dateOfSubmission"));

        List<Mark> marksViaSubjectAndLecturer = new MarkController().getMarksViaSubjectAndLecturer(mark);//---Call the db server (MarkController(db_controller)) to get marks via subject and lecturer

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray datesJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Mark markObj : marksViaSubjectAndLecturer) {
            JSONObject dateJson = new JSONObject();//---Creates a JSON object {}
            dateJson.put("EvaluationCriteria", markObj.getCriteria());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual"}
            dateJson.put("Marks", markObj.getMarks());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual","Marks":"25"}
            dateJson.put("StudentsCount", markObj.getStudentsCount());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"}
            datesJson.add(dateJson);//---Add JSON object to JSON array [{"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"},{"EvaluationCriteria":"Lecturer was prepared for lectures","Marks":"35","StudentsCount":"7"}]
        }
        obj.put("Marks", datesJson);//---Add JSON array to JSON object {"Marks":[{"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"},{"EvaluationCriteria":"Lecturer was prepared for lectures","Marks":"35","StudentsCount":"7"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
