<%@ page import="java.util.List" %>
<%@ page import="model.Semester" %>
<%@ page import="controller.db_controller.SemesterController" %>

<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 75px;margin-left: 30px;margin-right: 30px">
    <div class="row" style="margin-top: 20px;text-align: center;font-size: 19px">
        <div class="col-12">
            Semester
        </div>
    </div>
    <div class="row">
        <div class="col-12">
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

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Subjects
        </div>
    </div>

    <div class="row" style="margin-bottom: 50px;margin-top: 10px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr style="font-size: 18px">
                    <th width="5%"></th>
                    <th width="10%" style="text-align: center">Subject Code</th>
                    <th width="65%" style="text-align: center">Subject Title</th>
                    <th width="10%" style="text-align: center">Credits</th>
                    <th width="10%" style="text-align: center">Allowed</th>
                </tr>
                <tbody id="subjectsBody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="/controller/admin/subjectsController.js"></script>
<jsp:include page="../footer.jsp"/>