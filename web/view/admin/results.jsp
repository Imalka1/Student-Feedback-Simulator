<%@ page import="java.util.List" %>
<%@ page import="controller.db_controller.SemesterController" %>
<%@ page import="model.Semester" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 75px;margin-left: 30px;margin-right: 30px">

    <div class="row">
        <div class="col-12" style="background-color: #f0f0f0;padding: 5px">
            <span style="border-right: 2px solid black;padding-right: 10px;font-weight: bold">Results</span>
        </div>
    </div>

    <div class="row" style="text-align: center;font-size: 19px;margin-top: 30px">
        <div class="col-3">
            Semester
        </div>
        <div class="col-9">
            Subjects
        </div>
    </div>
    <div class="row">
        <div class="col-3">
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
        <div class="col-9">
            <select class="form-control" id="subjects"></select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 20px;background-color: #b0b0b0">

    <div class="row" style="text-align: center;font-size: 19px">
        <div class="col-9">
            Lecturer
        </div>
        <div class="col-3">
            Date
        </div>
    </div>
    <div class="row">
        <div class="col-9">
            <select class="form-control" id="lecturers"></select>
        </div>
        <div class="col-3">
            <select class="form-control" id="dates"></select>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Results
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-12" id="studentsCount">

        </div>
    </div>
    <div class="row" style="margin-bottom: 50px;margin-top: 5px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr>
                    <th width="5%"></th>
                    <th width="50%" style="text-align: center">Evaluation Criteria</th>
                    <th width="15%" style="text-align: center">Marks (Total)</th>
                    <th width="15%" style="text-align: center">Marks (Average)</th>
                    <th width="15%" style="text-align: center">Marks (Percentage)</th>
                </tr>
                <tbody id="marksBody">
                </tbody>
            </table>
        </div>
    </div>

    <%--------------------------------------------Submit marks button (Start)-----------------------------------------%>

    <div class="row">
        <div class="col-6">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnGraph1" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Show Graph (Criteria Heading)
                </button>
            </div>
        </div>
        <div class="col-6">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnGraph2" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Show Graph (Criteria)
                </button>
            </div>
        </div>
    </div>

    <%---------------------------------------------Submit marks button (End)------------------------------------------%>
</div>

<script src="/controller/admin/resultsController.js"></script>
<jsp:include page="../footer.jsp"/>