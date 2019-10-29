package controller.url_controller.admin.degree;

import controller.db_controller.DegreeController;
import controller.db_controller.SubjectController;
import model.Degree;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add_degree")
public class AddDegreeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //--------------------------------------Set data to model object--------------------------------------------
        Degree degree = new Degree();
        degree.setDegreeName(req.getParameter("degreeTitle").trim());

        if (new DegreeController().addDegree(degree)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        } else {
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}
