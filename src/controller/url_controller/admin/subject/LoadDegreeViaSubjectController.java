package controller.url_controller.admin.subject;

import controller.db_controller.DegreeController;
import model.Degree;
import model.Subject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/load_degrees_via_subject")//---URL extension which mapped to this servlet object
public class LoadDegreeViaSubjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------

        Subject subject = new Subject();
        subject.setSubjectId(req.getParameter("subjectId"));

        List<Degree> allDegreesViaSubject = new DegreeController().getAllDegreesViaSubject(subject);//---Call the db server (DegreeController(db_controller)) to get all degrees via faculty

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray degreesJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Degree degree : allDegreesViaSubject) {
            JSONObject degreeJson = new JSONObject();//---Creates a JSON object {}
            degreeJson.put("DegId", degree.getDegreeId());//---Add data to JSON {"DegId":"1"}
            degreeJson.put("DegreeName", degree.getDegreeName());//---Add data to JSON {"DegId":"1","DegreeName":"BSc(Computer Science)"}
            degreesJson.add(degreeJson);//---Add JSON object to JSON array [{"DegId":"1","DegreeName":"BSc(Computer Science)"},{"DegId":"2","DegreeName":"BSc(Information Systems)"}]
        }
        obj.put("Degrees", degreesJson);//---Add JSON array to JSON object {"Degrees":[{"DegId":"1","DegreeName":"BSc(Computer Science)"},{"DegId":"2","DegreeName":"BSc(Information Systems)"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
