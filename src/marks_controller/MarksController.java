package marks_controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/processMarks")
public class MarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] marks = req.getParameter("marks").split(",");
        String[] ecids = req.getParameter("ecids").split(",");
        for (String mark : marks) {
            System.out.println(mark);
        }
        for(String ecid:ecids){
            System.out.println(ecid);
        }
    }
}
