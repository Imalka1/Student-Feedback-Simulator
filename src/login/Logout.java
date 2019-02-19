package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionLogin = req.getSession(false);
        if (sessionLogin != null) {
            sessionLogin.invalidate();
        }
        resp.sendRedirect("index.jsp");
//        HttpSession sessionRep = req.getSession(false);
//        if (!sessionRep.isNew()) {
//            sessionRep.invalidate();
//        }
//        resp.sendRedirect("index.jsp");
    }
}
