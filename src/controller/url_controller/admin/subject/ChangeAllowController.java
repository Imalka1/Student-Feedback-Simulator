package controller.url_controller.admin.subject;

import controller.db_controller.SubjectController;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/change_allow")
public class ChangeAllowController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = new Subject();
        subject.setSubjectId(req.getParameter("subjectId").trim());
        subject.setAllowed(req.getParameter("allow").equals("true"));
        if (new SubjectController().changeAllow(subject)) {
            resp.getWriter().println(true);
        } else {
            resp.getWriter().println(false);
        }
    }
}
