<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<jsp:include page="../header.jsp"/>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="../../index.jsp"/>
<%
        }
    }
%>
<div class="navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger" style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white" href="landing_page(admin).jsp">Back</a>
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
    <div class="row">
        <div class="col-12" id="response">

        </div>
    </div>
    <div class="row" style="margin-top: 20px;text-align: center;font-size: 20px">
        <div class="col-1">
            Year
        </div>
        <div class="col-9">
            Degree
        </div>
        <div class="col-2">
            Batch
        </div>
    </div>

    <div class="row">
        <div class="col-1">
            <select class="form-control" id="year">
                <%
                    {
                        List<Batch> years = new BatchController().getYears();
                        for (Batch batch : years) {
                %>
                <option value="<%= batch.getYear()%>"><%= batch.getYear()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-9">
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
            <select class="form-control" id="batch">
                <%
                    {
                        List<Batch> allBatches = new BatchController().getAllBatches();
                        for (Batch batch : allBatches) {
                %>
                <option value="<%= batch.getBatchid()%>"><%= batch.getBatchName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <%--<div class="col-2">--%>
        <%--<div>--%>
        <%--<button type="submit" id="btnSearch" class="btn" style="background-color: #ffbf05;width: 100%">Search</button>--%>
        <%--</div>--%>
        <%--</div>--%>
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
        <div class="col-2" style="text-align: center">
            (Search student)
        </div>
        <div class="col-2" style="text-align: center">
            (Click for a new student)
        </div>
    </div>
    <div class="row">
        <div class="col-3">
            Registration Number :
        </div>
        <div class="col-5">
            <input class="form-control" type="text" id="regNo">
        </div>
        <div class="col-2">
            <button id="btnSearchStudent" class="btn" style="background-color: #ffbf05;width: 100%">Search student</button>
        </div>
        <div class="col-2">
            <button id="btnNewStudent" class="btn" style="background-color: #ffbf05;width: 100%">New student</button>
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
    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnAdd" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>Submit</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnUpdate" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>Update</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnDelete" class="btn" style="background-color: #ffbf05;margin-bottom: 50px" disabled>Delete</button>
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
                    <th width="20%" style="text-align: center">Registration Number</th>
                    <th width="60%" style="text-align: center">Student Name</th>
                    <th width="15%" style="text-align: center">National ID</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="studentsDataBody">
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="margin-bottom: 50px">
        <div class="col-4"><i class="fa fa-arrow-circle-left fa-3x" style="float: right;margin-right: 30px" id="decPageNo"></i></div>
        <div class="col-4" style="text-align: center;font-size: 30px;font-weight: bold" id="pageNo"></div>
        <div class="col-4"><i class="fa fa-arrow-circle-right fa-3x" style="margin-left: 30px" id="incPageNo"></i></div>
    </div>
</div>
</div>
<%--</div>--%>
<jsp:include page="../footer.jsp"/>