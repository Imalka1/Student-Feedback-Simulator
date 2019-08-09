package controller.url_controller.admin.auth;

import controller.db_controller.StudentController;
import controller.db_controller.UserController;
import model.Student;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")//---URL extension which mapped to this servlet object
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //------------------------------------------Load a new session--------------------------------------------------
        HttpSession sessionLogin = req.getSession(true);

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        String uid = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        //--------------------------------------Set data to model object------------------------------------------------
        User userObj = new User();
        userObj.setuId(uid);
        userObj.setPassword(password);

        User user = new UserController().chkLogin(userObj);//---Call the db server (UserController(db_controller)) to retrieve user data

        if (user != null) {//---Check whether the user is alive

            sessionLogin.setAttribute("uId", uid);
            if (user.getAccountType().equals("student")) {//---Check whether the user is a student

                Student student = new Student();
                student.setuId(uid);
                Student studentNic = new StudentController().getStudentNic(student);//---Call the db server (StudentController(db_controller)) to retrieve student nic data
                if (password.equals(studentNic.getNationalId())) {//---Check whether the password and student nic is equal
                    resp.sendRedirect("/view/reset_password.jsp");//---Navigate (redirect) to reset password page
                } else {
                    sessionLogin.setAttribute("accountType", user.getAccountType());
                    resp.sendRedirect("/view/student/landing_page(student).jsp");//---Navigate (redirect) to landing_page(student).jsp
                }

            } else if (user.getAccountType().equals("admin")) {//---Check whether the user is an admin

                if (password.equals("admin")) {//---Check whether the password and admin password is equal to admin
                    resp.sendRedirect("/view/reset_password.jsp");//---Navigate (redirect) to reset_password.jsp
                } else {
                    sessionLogin.setAttribute("accountType", user.getAccountType());
                    resp.sendRedirect("/view/admin/landing_page(admin).jsp");//---Navigate (redirect) to landing_page(admin).jsp
                }
            }

        } else {//---If user is not alive
            resp.sendRedirect("/index.jsp?error=errorLogin");//---Navigate (redirect) again to login page with login error
        }
    }
}
