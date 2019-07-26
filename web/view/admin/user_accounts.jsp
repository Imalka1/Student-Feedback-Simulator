<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
    <div class="row">
        <div class="col-12" id="response">

        </div>
    </div>

    <div class="row" style="margin-top: 20px;text-align: center;font-size: 19px">
        <div class="col-4">
            Faculty
        </div>
        <div class="col-8">
            Degree
        </div>
    </div>

    <div class="row">
        <div class="col-4">
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
        <div class="col-8">
            <select class="form-control" id="degree"></select>
        </div>
    </div>

    <div class="row" style="margin-top: 20px;text-align: center;font-size: 19px">
        <div class="col-5">
            Intake
        </div>
        <div class="col-4">
            Semester
        </div>
        <div class="col-3" style="font-size: 12px">
            (Change all students to next semester)
        </div>
    </div>

    <div class="row">
        <div class="col-5">
            <select class="form-control" id="batch">
                <%
                    {
                        List<Batch> years = new BatchController().getIntakes();
                        for (Batch batch : years) {
                %>
                <option value="<%= batch.getBatchid()%>"><%= batch.getBatchName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-4">
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
        <div class="col-3">
            <div>
                <button id="btnChangeSem" class="btn" style="background-color: #ffbf05;width: 100%">Change to next semester
                </button>
            </div>
        </div>
    </div>

    <hr style="margin-top: 50px;margin-bottom: 50px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Student Configurations
        </div>
    </div>
    <div class="row" style="margin-top: 50px;font-size: 12px">
        <div class="col-3"></div>
        <div class="col-5">
            (The registration number cannot be updated)
        </div>
        <%--<div class="col-2" style="text-align: center">--%>
        <%--(Search student)--%>
        <%--</div>--%>
        <%--<div class="col-2" style="text-align: center">--%>
        <%--(Click to clear fields)--%>
        <%--</div>--%>
    </div>
    <div class="row">
        <div class="col-3">
            Registration Number :
        </div>
        <div class="col-5">
            <input class="form-control" type="text" id="regNo">
        </div>
        <div class="col-2">
            <button id="btnSearchStudent" class="btn" style="background-color: #ffbf05;width: 100%">Search student
            </button>
        </div>
        <div class="col-2">
            <button id="btnClear" class="btn" style="background-color: #ffbf05;width: 100%">Clear</button>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-3">
            Student Name :
        </div>
        <div class="col-9">
            <input class="form-control" type="text" id="studetName">
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-3">
            National ID :
        </div>
        <div class="col-9">
            <input class="form-control" type="text" id="nationalId">
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-3">
            Email Address :
        </div>
        <div class="col-9">
            <input class="form-control" type="email" id="emailAddress">
        </div>
    </div>
    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnAdd" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>Submit
                </button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnUpdate" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>
                    Update
                </button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnDelete" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>
                    Delete
                </button>
            </div>
        </div>
    </div>

    <hr style="margin-bottom: 50px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Registered Students
        </div>
    </div>
    <div class="row" style="margin-bottom: 50px;margin-top: 50px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr>
                    <th width="15%" style="text-align: center">Registration Number</th>
                    <th width="40%" style="text-align: center">Student Name</th>
                    <th width="10%" style="text-align: center">National ID</th>
                    <th width="30%" style="text-align: center">Email Address</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="studentsDataBody">
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="margin-bottom: 50px">
        <div class="col-4"><i class="fa fa-arrow-circle-left fa-3x" style="float: right;margin-right: 30px"
                              id="decPageNo"></i></div>
        <div class="col-4" style="text-align: center;font-size: 30px;font-weight: bold" id="pageNo"></div>
        <div class="col-4"><i class="fa fa-arrow-circle-right fa-3x" style="margin-left: 30px" id="incPageNo"></i></div>
    </div>
</div>
</div>
<%--</div>--%>

<script src="/controller/admin/userAccountController.js"></script>
<jsp:include page="../footer.jsp"/>