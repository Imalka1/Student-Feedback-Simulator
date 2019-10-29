package controller.url_controller.admin.subject;

import controller.db_controller.DegreeController;
import controller.db_controller.SubjectController;
import controller.db_controller.SubjectDegreeController;
import model.Degree;
import model.Subject;
import model.SubjectDegree;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/load_subjects_via_degree")//---URL extension which mapped to this servlet object
public class LoadSubjectsViaDegreeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------

        Degree degree = new Degree();
        degree.setDegreeId(Integer.parseInt(req.getParameter("degreeId")));

        List<SubjectDegree> subjectsViaDegree = new SubjectDegreeController().getSubjectsViaDegree(degree);//---Call the db server (DegreeController(db_controller)) to get all degrees via faculty

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray subjectsJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (SubjectDegree subject : subjectsViaDegree) {
            JSONObject subjectJson = new JSONObject();//---Creates a JSON object {}
            subjectJson.put("SubjectId", subject.getSubjectId());//---Add data to JSON {"DegId":"1"}
            subjectJson.put("SubjectName", subject.getSubjectName());//---Add data to JSON {"DegId":"1","DegreeName":"BSc(Computer Science)"}
            subjectJson.put("Semester", subject.getSemesterName());
            subjectsJson.add(subjectJson);//---Add JSON object to JSON array [{"DegId":"1","DegreeName":"BSc(Computer Science)"},{"DegId":"2","DegreeName":"BSc(Information Systems)"}]
        }
        obj.put("Subjects", subjectsJson);//---Add JSON array to JSON object {"Degrees":[{"DegId":"1","DegreeName":"BSc(Computer Science)"},{"DegId":"2","DegreeName":"BSc(Information Systems)"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
