package controller.url_controller.admin;

import controller.db_controller.StudentController;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/load_students")//---URL extension which mapped to this servlet object
public class LoadStudentsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        int degreeId = Integer.parseInt(req.getParameter("degreeId").trim());
        int batchId = Integer.parseInt(req.getParameter("batchId").trim());
        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());

        //------------------------------------------Set data to model object--------------------------------------------
        Student student = new Student();
        student.setDegreeId(degreeId);
        student.setBatchId(batchId);
        student.setSemesterId(semesterId);

        List<Student> allStudents = new StudentController().getAllStudents(student);//---Call the db server (StudentController(db_controller)) to all students

        JSONObject obj = new JSONObject();//---Creates a JSON object {}
        JSONArray studentsJson = new JSONArray();//---Creates a JSON array to store JSON objects []
        for (Student studentObj : allStudents) {
            JSONObject studentJson = new JSONObject();//---Creates a JSON object {}
            studentJson.put("RegId", studentObj.getuId());//---Add data to JSON {"RegId":"IT123"}
            studentJson.put("StudentName", studentObj.getStudentName());//---Add data to JSON {"RegId":"IT123","StudentName":"Amal"}
            studentJson.put("NationalId", studentObj.getNationalId());//---Add data to JSON {"RegId":"IT123","StudentName":"Amal","NationalId":"961251465V"}
            studentJson.put("EmailAddress", studentObj.getEmailAddress());//---Add data to JSON {"RegId":"IT123","StudentName":"Amal","NationalId":"961251465V","EmailAddress":"amal@gmail.com"}
            studentsJson.add(studentJson);//---Add JSON object to JSON array [{"RegId":"IT123","StudentName":"Amal","NationalId":"961251465V","EmailAddress":"amal@gmail.com"},{"RegId":"IT456","StudentName":"Nimal","NationalId":"961251465V","EmailAddress":"nimal@gmail.com"}]
        }
        obj.put("Students", studentsJson);//---Add JSON array to JSON object {"Students":[{"RegId":"IT123","StudentName":"Amal","NationalId":"961251465V","EmailAddress":"amal@gmail.com"},{"RegId":"IT456","StudentName":"Nimal","NationalId":"961251465V","EmailAddress":"nimal@gmail.com"}]}
        resp.getWriter().println(obj.toJSONString());//---Print and reply JSON as a text
    }
}
