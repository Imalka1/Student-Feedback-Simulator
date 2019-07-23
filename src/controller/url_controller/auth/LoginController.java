package controller.url_controller.auth;

import controller.db_controller.StudentController;
import controller.db_controller.UserController;
import model.Student;
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
        String uid = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        User userObj = new User();
        userObj.setUid(uid);
        userObj.setPassword(password);
        User user = new UserController().chkLogin(userObj);
        if (user != null) {
            sessionLogin.setAttribute("uid", uid);

            if (user.getAccountType().equals("student")) {
                Student student = new Student();
                student.setUid(uid);
                Student studentNic = new StudentController().getStudentNic(student);
                if (password.equals(studentNic.getNationalId())) {
                    resp.sendRedirect("/view/reset_password.jsp");
                } else {
                    sessionLogin.setAttribute("accountType", user.getAccountType());
                    resp.sendRedirect("/view/student/landing_page(student).jsp");
                }
            } else if (user.getAccountType().equals("admin")) {
                if (password.equals("admin")) {
                    resp.sendRedirect("/view/reset_password.jsp");
                } else {
                    sessionLogin.setAttribute("accountType", user.getAccountType());
                    resp.sendRedirect("/view/admin/landing_page(admin).jsp");
                }
            }

        } else {
            resp.sendRedirect("/index.jsp?error=errorLogin");
        }
    }
}
