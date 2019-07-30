package controller.url_controller.admin.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")//---URL extension which mapped to this servlet object
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionLogin = req.getSession(false);//---Load the current session
        if (sessionLogin != null) {
            sessionLogin.invalidate();//---Remove the current session
        }
        resp.sendRedirect("/index.jsp");//---Navigate (redirect) to login page
    }
}
