package controller.url_controller.admin.lecturer;

import controller.db_controller.LecturerController;
import model.Lecturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_lecturer")
public class DeleteLecturerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //--------------------------------------Retrieve data which submitted to the server and set data to model object--------------------------------------------
        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerId(req.getParameter("lecturerId").trim());

        if (new LecturerController().deleteLecturer(lecturer)) {//---Call the db server (SubjectController(db_controller)) to add subject
            resp.getWriter().println(true);//---Reply / Response
        }else{
            resp.getWriter().println(false);//---Reply / Response
        }
    }
}