package controller.url_controller.admin.subject;

import controller.db_controller.SubjectController;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_subject")
public class DeleteSubjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        String subjectId = req.getParameter("subjectId").trim();

        //------------------------------------------Set data to model object--------------------------------------------
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);

        if (new SubjectController().deleteSubject(subject)) {//---Call the db server (UserController(db_controller)) to delete student
            resp.getWriter().println(true);
        } else {
            resp.getWriter().println(false);
        }
    }
}
