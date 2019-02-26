package controller.url_controller;

import controller.db_controller.UserController;
import model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionLogin = req.getSession(true);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDTO userDTO = UserController.chkLogin(username, password);
        if (userDTO != null) {
            sessionLogin.setAttribute("uid", userDTO.getUid());
            HttpSession ses = req.getSession();
//            Cookie cookie = new Cookie("JSESSIONID", ses.getId());
//            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
//            resp.addCookie(cookie);
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
//                    resp.addCookie(cookie);
                }
                System.out.println(cookie.getName());
                System.out.println(cookie.getMaxAge());
            }
            resp.sendRedirect("subjects.jsp");
        } else {
            resp.sendRedirect("index.jsp?error=error");
        }
    }
}
