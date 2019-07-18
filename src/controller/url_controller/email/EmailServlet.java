package controller.url_controller.email;

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

@WebServlet(urlPatterns = {"/email_confirmation_code"})
public class EmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String to = request.getParameter("email");
        int number = new Random().nextInt(1000000);

        try {
            Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");

            Authenticator auth = new SMTPAuthenticator("webphpjava@gmail.com", "webphpjava1");

            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(String.valueOf(number));
            msg.setSubject("Confirmation Code");
            msg.setFrom(new InternetAddress("webphpjava@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
        } catch (AuthenticationFailedException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Authentication failed");
        } catch (AddressException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Invalid email address");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        response.getWriter().println(number);
    }

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