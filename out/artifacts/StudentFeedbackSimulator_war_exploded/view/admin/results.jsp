<%@ page import="controller.db_controller.BatchController" %>
<%@ page import="model.Batch" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.db_controller.DegreeController" %>
<%@ page import="model.Degree" %>
<%@ page import="controller.db_controller.SemesterController" %>
<%@ page import="model.Semester" %>

<jsp:include page="../header.jsp"/>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
%>
<div class="navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                <span>Menu</span><i class="fa fa-caret-down" style="margin-left: 5px"></i>
            </a>
            <div class="dropdown-content" style="padding-top: 14px;font-size: 14px">
                <a href="landing_page(admin).jsp">Home</a>
                <a href="user_accounts.jsp">User Accounts</a>
                <a href="#">Subjetcs</a>
                <a href="#">Lecturers</a>
                <a href="#">Semesters</a>
            </div>
        </li>
        <form action="logout" method="post">
            <li class="nav-item">
                <a id="btnLogout" class="js-scroll-trigger" href="<%= logout%>"
                   style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                    Logout
                    <%--<i class="fa fa-sign-out" style="margin-left: 20px"></i>--%>
                </a>
            </li>
        </form>
    </ul>
</div>
</div>
</nav>

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
    <div class="row" style="margin-top: 20px;text-align: center;font-size: 20px">
        <div class="col-2">
            Intake
        </div>
        <div class="col-8">
            Degree
        </div>
        <div class="col-2">
            Semester
        </div>
    </div>
    <div class="row">
        <div class="col-2">
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
        <div class="col-8">
            <select class="form-control" id="degree">
                <%
                    {
                        List<Degree> allDegrees = new DegreeController().getAllDegrees();
                        for (Degree degree : allDegrees) {
                %>
                <option value="<%= degree.getDegid()%>"><%= degree.getDegreeName()%>
                </option>
                <%
                        }
                    }
                %>
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
<jsp:include page="../footer.jsp"/>