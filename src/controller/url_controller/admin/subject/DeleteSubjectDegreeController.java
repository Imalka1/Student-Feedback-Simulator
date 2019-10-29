package controller.url_controller.admin.subject;

import controller.db_controller.SubjectDegreeController;
import model.SubjectDegree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_subject_degree")
public class DeleteSubjectDegreeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //---------------------------------Retrieve data which submitted to the server----------------------------------

        int degreeId = Integer.parseInt(req.getParameter("degreeId").trim());
        String subjectId = req.getParameter("subjectId").trim();

        //--------------------------------------Set data to model object--------------------------------------------
        SubjectDegree subjectDegree = new SubjectDegree();
        subjectDegree.setSubjectId(subjectId);
        subjectDegree.setDegreeId(degreeId);

        if (new SubjectDegreeController().deleteSubjectDegree(subjectDegree)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        } else {
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}
