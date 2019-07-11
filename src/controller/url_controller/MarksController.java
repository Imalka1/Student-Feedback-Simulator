package controller.url_controller;

import controller.db_controller.MarkController;
import model.Mark;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/processMarks")
public class MarksController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jsonObjectIds = (JSONArray) JSONValue.parse(req.getParameter("ecids"));
        JSONArray jsonObjectMarks = (JSONArray) JSONValue.parse(req.getParameter("marks"));
        String dateOfSubmission = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        PrintWriter printWriter = resp.getWriter();
        List<Mark> marks=new ArrayList<>();
        for (int i = 0; i < jsonObjectIds.size(); i++) {
            Mark mark = new Mark();
            mark.setSublecid(Integer.parseInt(req.getParameter("sublecid")));
            mark.setEcid(Integer.parseInt(jsonObjectIds.get(i).toString()));
            mark.setMarks(Integer.parseInt(jsonObjectMarks.get(i).toString()));
            mark.setDateOfSubmitted(dateOfSubmission);
            marks.add(mark);
        }
        if (new MarkController().addMarks(marks)) {
            printWriter.println(true);
        } else {
            printWriter.println(false);
        }
    }
}
