<%@ page import="controller.db_controller.BatchController" %>
<%@ page import="model.Batch" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.db_controller.DegreeController" %>
<%@ page import="model.Degree" %>
<%@ page import="controller.db_controller.SemesterController" %>
<%@ page import="model.Semester" %>
<%@ page import="controller.db_controller.FacultyController" %>
<%@ page import="model.Faculty" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
    <div class="row" style="margin-top: 20px;text-align: center;font-size: 20px">
        <div class="col-3">
            Faculty
        </div>
        <div class="col-7">
            Degree
        </div>
        <div class="col-2">
            Semester
        </div>
    </div>
    <div class="row">
        <div class="col-3">
            <select class="form-control" id="faculty">
                <%
                    {
                        List<Faculty> allFaculties = new FacultyController().getAllFaculties();
                        for (Faculty faculty : allFaculties) {
                %>
                <option value="<%= faculty.getFacid()%>"><%= faculty.getFacultyName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-7">
            <select class="form-control" id="degree">
                <%--<%--%>
                    <%--{--%>
                        <%--List<Degree> allDegrees = new DegreeController().getAllDegrees();--%>
                        <%--for (Degree degree : allDegrees) {--%>
                <%--%>--%>
                <%--<option value="<%= degree.getDegid()%>"><%= degree.getDegreeName()%>--%>
                <%--</option>--%>
                <%--<%--%>
                        <%--}--%>
                    <%--}--%>
                <%--%>--%>
            </select>
        </div>
        <div class="col-2">
            <select class="form-control" id="semester">
                <%
                    {
                        List<Semester> allSemesters = new SemesterController().getAllSemesters();
                        for (Semester semester : allSemesters) {
                %>
                <option value="<%= semester.getSemid()%>"><%= semester.getSemesterName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row" style="margin-top: 20px;text-align: center;font-size: 20px">
        <div class="col-6">
            Lecturer
        </div>
        <div class="col-6">
            Subject
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <select class="form-control" id="">
                <%--<option value=""></option>--%>
            </select>
        </div>
        <div class="col-6">
            <select class="form-control" id="">
                <%--<option value=""></option>--%>
            </select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">


</div>
</div>

<script src="/controller/admin/resultsController.js"></script>
<jsp:include page="../footer.jsp"/>