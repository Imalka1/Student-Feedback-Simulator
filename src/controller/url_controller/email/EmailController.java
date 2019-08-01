package controller.url_controller.email;

import controller.db_controller.UserController;
import model.User;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/email_confirmation_code"})//---URL extension which mapped to this servlet object
public class EmailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //-----------------Retrieve data which submitted to the server and set data to model object---------------------
        User user = new User();
        user.setuId(request.getParameter("userId"));
        User emailViaUid = new UserController().getEmailViaUid(user);

        //---------------------------------Generate a random number for the confirmation code---------------------------
        int number = new Random().nextInt(1000000);

        try {
            //------------------------------Set gmail server as smtp mailing server-------------------------------------
            Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");

            //----------------------------------------Login to email (sender)-------------------------------------------
            Authenticator auth = new SMTPAuthenticator("webphpjava@gmail.com", "webphpjava1");

            Session session = Session.getInstance(props, auth);

            //--------------------------------------Create email text (body)--------------------------------------------
            MimeMessage msg = new MimeMessage(session);
            msg.setText(String.valueOf(number));//---Set random number to email
            msg.setSubject("Confirmation Code");//---Set subject
            msg.setFrom(new InternetAddress("webphpjava@gmail.com"));//---Set email
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailViaUid.getEmailAddress()));//---Set receiver's email

            //------------------------------------------Send email------------------------------------------------------
            Transport.send(msg);
        } catch (AuthenticationFailedException ex) {//--Catch if any authentication exception occurred
            ex.printStackTrace();
        } catch (AddressException ex) {//--Catch if any address exception occurred
            ex.printStackTrace();
        } catch (MessagingException ex) {//--Catch if any messaging exception occurred
            ex.printStackTrace();
        }
        response.getWriter().println(number);//---Print and reply random number as a text and send to ui
    }

    //---------------------------------Authenticate (login to) email (sender)-------------------------------------------
    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}