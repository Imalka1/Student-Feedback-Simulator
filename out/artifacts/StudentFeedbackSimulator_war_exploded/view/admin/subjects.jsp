<%@ page import="controller.db_controller.FacultyController" %>
<%@ page import="model.Faculty" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Semester" %>
<%@ page import="controller.db_controller.SemesterController" %>

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

    <div class="row" style="margin-top:20px;text-align: center;font-size: 19px">
        <div class="col-6">
            Subject ID
        </div>
        <div class="col-6">
            Subject Title
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <input class="form-control" type="text" id="subjectId">
        </div>
        <div class="col-6">
            <input class="form-control" type="text" id="subjectTitle">
        </div>
    </div>

    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnChangeSem" class="btn" style="background-color: #ffbf05">Submit</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnChangeSem" class="btn" style="background-color: #ffbf05">Update</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnChangeSem" class="btn" style="background-color: #ffbf05">Delete</button>
            </div>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Subjects
        </div>
    </div>

    <div class="row" style="margin-bottom: 50px;margin-top: 20px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr style="font-size: 18px">
                    <th width="5%"></th>
                    <th width="10%" style="text-align: center">Subject Code</th>
                    <th width="70%" style="text-align: center">Subject</th>
                    <th width="10%" style="text-align: center">Subject Allowed</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="marksBody">
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>

<script src="/controller/admin/subjectsController.js"></script>
<jsp:include page="../footer.jsp"/>