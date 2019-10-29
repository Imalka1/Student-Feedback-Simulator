package controller.url_controller.admin.subject;

import controller.db_controller.SubjectLecturerController;
import model.SubjectLecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_subject_lecturer")
public class DeleteSubjectLecturerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //---------------------------------Retrieve data which submitted to the server----------------------------------

        String lecturerId = req.getParameter("lecturerId").trim();
        String subjectId = req.getParameter("subjectId").trim();

        //--------------------------------------Set data to model object--------------------------------------------
        SubjectLecturer subjectLecturer = new SubjectLecturer();
        subjectLecturer.setSubjectId(subjectId);
        subjectLecturer.setLecturerId(lecturerId);

        if (new SubjectLecturerController().deleteSubjectLecturer(subjectLecturer)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        } else {
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}
