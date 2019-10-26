package controller.url_controller.admin.mark_sheet;

import controller.db_controller.CriteriaController;
import controller.db_controller.MarkController;
import model.Criteria;
import model.Mark;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/load_marks")//---URL extension which mapped to this servlet object
public class LoadMarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        Mark mark = new Mark();
        mark.setSubjectId(req.getParameter("subjectId"));
        mark.setLecturerId(req.getParameter("lecturerId"));
        mark.setDateOfSubmission(req.getParameter("dateOfSubmission"));

        List<Criteria> criteriaHeadings = new CriteriaController().getCriteriaHeadings();
        List<Mark> marksViaSubjectAndLecturer = new MarkController().getMarksViaSubjectAndLecturer(mark);//---Call the db server (MarkController(db_controller)) to get marks via subject and lecturer

        JSONArray criteriaHeadingJsonArray = new JSONArray();//---Creates a JSON array to store JSON objects []

        for (Criteria criteriaHeading : criteriaHeadings) {
            JSONObject objCriteria = new JSONObject();//---Creates a JSON object {}
            JSONArray criteriaJsonArray = new JSONArray();//---Creates a JSON array to store JSON objects []

            for (Mark markObj : marksViaSubjectAndLecturer) {
                if (criteriaHeading.getCriteriaHeadingName().equals(markObj.getCriteriaHeading())) {
                    JSONObject criteriaJson = new JSONObject();//---Creates a JSON object {}
                    criteriaJson.put("EvaluationCriteria", markObj.getCriteria());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual"}
                    criteriaJson.put("Marks", markObj.getMarks());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual","Marks":"25"}
                    criteriaJson.put("StudentsCount", markObj.getStudentsCount());//---Add data to JSON {"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"}
                    criteriaJsonArray.add(criteriaJson);//---Add JSON object to JSON array [{"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"},{"EvaluationCriteria":"Lecturer was prepared for lectures","Marks":"35","StudentsCount":"7"}]
                }
            }
            objCriteria.put("EvaluationCriteriaHeading", criteriaHeading.getCriteriaHeadingName());
            objCriteria.put("Marks", criteriaJsonArray);//---Add JSON array to JSON object {"Marks":[{"EvaluationCriteria":"Lecturer was punctual","Marks":"25","StudentsCount":"5"},{"EvaluationCriteria":"Lecturer was prepared for lectures","Marks":"35","StudentsCount":"7"}]}
            criteriaHeadingJsonArray.add(objCriteria);
        }

        resp.getWriter().println(criteriaHeadingJsonArray.toJSONString());//---Print and reply JSON as a text
    }
}
