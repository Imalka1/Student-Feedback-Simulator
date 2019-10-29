package controller.url_controller.admin.subject;

import controller.db_controller.SubjectController;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add_subject")
public class AddSubjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //---------------------------------Retrieve data which submitted to the server----------------------------------

        int semesterId = Integer.parseInt(req.getParameter("semesterId").trim());
        int credits = Integer.parseInt(req.getParameter("credits").trim());
        String subjectId = req.getParameter("subjectId").trim();
        String subjectTitle = req.getParameter("subjectTitle").trim();

        //--------------------------------------Set data to model object--------------------------------------------
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSemesterId(semesterId);
        subject.setSubjectName(subjectTitle);
        subject.setCredits(credits);

        if (new SubjectController().addSubject(subject)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        } else {
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}