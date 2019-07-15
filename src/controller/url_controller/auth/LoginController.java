package controller.url_controller.auth;

import controller.db_controller.UserController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionLogin = req.getSession(true);
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        User user = new UserController().chkLogin(username, password);
        if (user != null) {
            sessionLogin.setAttribute("uid", user.getUid());
            sessionLogin.setAttribute("accountType", user.getAccountType());
//            HttpSession ses = req.getSession();
//            Cookie cookie = new Cookie("JSESSIONID", ses.getId());
//            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
//            resp.addCookie(cookie);
//            for (Cookie cookie : req.getCookies()) {
//                if (cookie.getName().equals("JSESSIONID")) {
//                    cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
////                    resp.addCookie(cookie);
//                }
//                System.out.println(cookie.getName());
//                System.out.println(cookie.getMaxAge());
//            }
            if (user.getAccountType().equals("student")) {
                resp.sendRedirect("/view/student/landing_page(student).jsp");
            } else if (user.getAccountType().equals("admin")) {
                resp.sendRedirect("/view/admin/landing_page(admin).jsp");
            }
        } else {
            resp.sendRedirect("/index.jsp?error=error");
        }
    }
}
