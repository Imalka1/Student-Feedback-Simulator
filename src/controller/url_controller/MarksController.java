package controller.url_controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/processMarks")
public class MarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jsonObjectIds = (JSONArray) JSONValue.parse(req.getParameter("ecids"));
        JSONArray jsonObjectMarks = (JSONArray) JSONValue.parse(req.getParameter("marks"));

        for (int i = 0; i < jsonObjectIds.size(); i++) {
            System.out.println(jsonObjectIds.get(i));
            System.out.println(jsonObjectMarks.get(i));
        }
    }
}
