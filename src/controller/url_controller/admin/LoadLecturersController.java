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

@WebServlet(urlPatterns = "/load_lecturers")//---URL extension which mapped to this servlet object
public class LoadLecturersController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        Subject subject = new Subject();
        subject.setSubjectId(req.getParameter("subjectId"));

        List<Lecturer> allLecturersViaSubject = new SubjectLecturerController().getAllLecturersViaSubject(subject);//---Call the db server (SubjectLecturerController(db_controller)) to get all lecturers via subject

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray lecturersJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Lecturer lecturer : allLecturersViaSubject) {
            JSONObject lecturerJson = new JSONObject();//---Creates a JSON object {}
            lecturerJson.put("LecturerId", lecturer.getLecId());//---Add data to JSON {"LecturerId":"1"}
            lecturerJson.put("LecturerName", lecturer.getLecturerName());//---Add data to JSON {"LecturerId":"1","LecturerName":"Kamal Silva"}
            lecturersJson.add(lecturerJson);//---Add JSON object to JSON array [{"LecturerId":"1","LecturerName":"Kamal Silva"},{"LecturerId":"2","LecturerName":"Nimal Silva"}]
        }
        obj.put("Lecturers", lecturersJson);//---Add JSON array to JSON object {"Lecturers":[{"LecturerId":"1","LecturerName":"Kamal Silva"},{"LecturerId":"2","LecturerName":"Nimal Silva"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
