<%@ page import="java.util.List" %>
<%@ page import="model.Semester" %>
<%@ page import="controller.db_controller.SemesterController" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 75px;margin-left: 30px;margin-right: 30px">

    <div class="row">
        <div class="col-12" style="background-color: #f0f0f0;padding: 5px">
            <span style="border-right: 2px solid black;padding-right: 10px;font-weight: bold">Subjects</span>
        </div>
    </div>

    <div class="row" style="margin-top: 20px">
        <div class="col-12" id="response">

        </div>
    </div>

    <div class="row" style="margin-top: 30px;text-align: center;font-size: 19px">
        <div class="col-3">
            <%--Faculty--%>
        </div>
        <div class="col-9">
            Semester
        </div>
    </div>
    <div class="row">
        <div class="col-3" style="text-align: center;font-weight: bold">
            Faculty Of Computing
        </div>
        <div class="col-9">
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
        <div class="col-3">
            Subject ID
        </div>
        <div class="col-6">
            Subject Title
        </div>
        <div class="col-1">
            Credits
        </div>
    </div>
    <div class="row">
        <div class="col-3">
            <input class="form-control" type="text" id="subjectId">
        </div>
        <div class="col-6">
            <input class="form-control" type="text" id="subjectTitle">
        </div>
        <div class="col-1">
            <input class="form-control" type="number" id="credits" min="1" value="1">
        </div>
        <div class="col-2">
            <div>
                <button id="btnClear" class="btn" style="background-color: #ffbf05;width: 100%">Clear</button>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnAdd" class="btn" style="background-color: #ffbf05">Submit</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnUpdate" class="btn" style="background-color: #ffbf05" disabled>Update</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnDelete" class="btn" style="background-color: #ffbf05" disabled>Delete</button>
            </div>
        </div>
    </div>

    <hr style="margin-top: 50px;margin-bottom: 50px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Subjects
        </div>
    </div>

    <div class="row">
        <div class="col-12" style="margin-top: 20px;font-size: 13px">
            <b>Note :- </b>Change only semester to view subjects
        </div>
    </div>

    <div class="row" style="margin-bottom: 50px;margin-top: 10px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr style="font-size: 18px">
                    <th width="5%"></th>
                    <th width="10%" style="text-align: center">Subject Code</th>
                    <th width="60%" style="text-align: center">Subject Title</th>
                    <th width="10%" style="text-align: center">Credits</th>
                    <th width="10%" style="text-align: center">Allowed</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="subjectsBody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/controller/admin/subjectsController.js"></script>
<jsp:include page="../footer.jsp"/>