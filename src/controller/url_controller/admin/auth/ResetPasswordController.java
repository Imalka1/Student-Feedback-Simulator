package controller.url_controller.admin.auth;

import controller.db_controller.UserController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reset_password")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUid(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));
        if (new UserController().updatePassword(user)) {
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("/view/reset_password.jsp?error=errorReset");
        }
    }
}
