package controller.url_controller.admin;

import controller.db_controller.DegreeController;
import controller.db_controller.FacultyController;
import model.Degree;
import model.Faculty;
import model.Student;
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

@WebServlet(urlPatterns = "/load_degrees")
public class LoadDegreesController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Faculty faculty = new Faculty();
        faculty.setFacid(Integer.parseInt(req.getParameter("facultyId")));
        List<Degree> allDegreesViaFaculty = new DegreeController().getAllDegreesViaFaculty(faculty);
        JSONObject obj = new JSONObject();
        JSONArray degreesJson = new JSONArray();
        for (Degree degree : allDegreesViaFaculty) {
            JSONObject degreeJson = new JSONObject();
            degreeJson.put("DegId", degree.getDegid());
            degreeJson.put("DegreeName", degree.getDegreeName());
            degreesJson.add(degreeJson);
        }
        obj.put("Degrees", degreesJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}
