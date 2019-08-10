<%@ page import="java.util.List" %>
<%@ page import="controller.db_controller.SemesterController" %>
<%@ page import="model.Semester" %>
<%@ page import="controller.db_controller.FacultyController" %>
<%@ page import="model.Faculty" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 75px;margin-left: 30px;margin-right: 30px">
    <div class="row" style="text-align: center;font-size: 19px">
        <div class="col-6">
            Faculty
        </div>
        <div class="col-6">
            Semester
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <select class="form-control" id="faculty">
                <%
                    {
                        List<Faculty> allFaculties = new FacultyController().getAllFaculties();
                        for (Faculty faculty : allFaculties) {
                %>
                <option value="<%= faculty.getFacultyId()%>"><%= faculty.getFacultyName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-6">
            <select class="form-control" id="semester">
                <%
                    {
                        List<Semester> allSemesters = new SemesterController().getAllSemesters();
                        for (Semester semester : allSemesters) {
                %>
                <option value="<%= semester.getSemesterId()%>"><%= semester.getSemesterName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 20px;background-color: #b0b0b0">

    <div class="row" style="text-align: center;font-size: 19px">
        <div class="col-6">
            Subject
        </div>
        <div class="col-6">
            Lecturer
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <select class="form-control" id="subjects"></select>
        </div>
        <div class="col-6">
            <select class="form-control" id="lecturers"></select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Results
        </div>
    </div>
    <div class="row" style="margin-top:10px;font-size: 19px">
        <div class="col-1">
            Date :
        </div>
        <div class="col-3">
            <select class="form-control" id="dates"></select>
        </div>
    </div>
    <div class="row" style="margin-bottom: 50px;margin-top: 20px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr>
                    <th width="5%"></th>
                    <th width="50%" style="text-align: center">Evaluation Criteria</th>
                    <th width="15%" style="text-align: center">Marks</th>
                    <th width="15%" style="text-align: center">Students Count<br>(Marks Submitted)</th>
                    <th width="15%" style="text-align: center">Average Mark</th>
                </tr>
                <tbody id="marksBody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="/controller/admin/resultsController.js"></script>
<jsp:include page="../footer.jsp"/>