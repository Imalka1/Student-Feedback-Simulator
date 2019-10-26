package controller.url_controller.student;

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
import java.util.List;

@WebServlet(urlPatterns = "/load_subjects")//---URL extension which mapped to this servlet object
public class LoadSubjectsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        Semester semester = new Semester();
        semester.setSemesterId(Integer.parseInt(req.getParameter("semesterId")));

        List<Subject> allSubjectsViaDegree = new SubjectController().getAllSubjectsViaSemester(semester);//---Call the db server (SubjectController(db_controller)) to get subjects via degree and semester

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray subjectsJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Subject subjectObj : allSubjectsViaDegree) {
            JSONObject subjectJson = new JSONObject();//---Creates a JSON object {}
            subjectJson.put("SubjectId", subjectObj.getSubjectId());//---Add data to JSON {"SubjectId":1}
            subjectJson.put("SubjectName", subjectObj.getSubjectName());//---Add data to JSON {"SubjectId":1,"SubjectName":"Programming"}
            subjectJson.put("Credits", subjectObj.getCredits());//---Add data to JSON {"SubjectId":1,"SubjectName":"Programming","Credits":2}
            subjectJson.put("Allowed", subjectObj.isAllowed());//---Add data to JSON {"SubjectId":1,"SubjectName":"Programming","Credits":2,"Allowed":true}
            subjectsJson.add(subjectJson);//---Add JSON object to JSON array [{"SubjectId":"1","SubjectName":"Programming","Credits":2,"Allowed":"true"},{"SubjectId":"2","SubjectName":"Database","Credits":2,"Allowed":"true"}]
        }
        obj.put("Subjects", subjectsJson);//---Add JSON array to JSON object {"Subjects":[{"SubjectId":1,"SubjectName":"Programming","Credits":2,"Allowed":true},{"SubjectId":2,"SubjectName":"Database","Credits":2,"Allowed":true}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
