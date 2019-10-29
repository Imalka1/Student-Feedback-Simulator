package controller.url_controller.admin.degree;

import controller.db_controller.DegreeController;
import model.Degree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update_degree")
public class UpdateDegreeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //--------------------------------------Set data to model object--------------------------------------------
        Degree degree = new Degree();
        degree.setDegreeId(Integer.parseInt(req.getParameter("degreeId").trim()));
        degree.setDegreeName(req.getParameter("degreeTitle").trim());

        if (new DegreeController().updateDegree(degree)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        } else {
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}
