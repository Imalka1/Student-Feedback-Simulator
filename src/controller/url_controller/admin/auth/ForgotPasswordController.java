package controller.url_controller.admin.auth;

import controller.db_controller.UserController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/forgot_password")//---URL extension which mapped to this servlet object
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        User user = new User();
        user.setUid(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));

        if (new UserController().updatePassword(user)) {//---Call the db server (UserController(db_controller)) to update password
            resp.sendRedirect("/index.jsp");//---Navigate (redirect) to login page
        } else {
            resp.sendRedirect("/view/forgot_password.jsp?error=errorReset");//---Navigate (redirect) again to forgot password page with error
        }
    }
}
