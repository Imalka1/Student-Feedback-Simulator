<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="../header.jsp"/>

<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";

    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

%>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">

        <%----------------------------------------------Page back (Start)---------------------------------------------%>

        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white"
               href="results.jsp">Back</a>
        </li>

        <%-----------------------------------------------Page back (End)----------------------------------------------%>

        <%----------------------------------------------Logout tab (Start)--------------------------------------------%>

        <form action="logout" method="post">
            <li class="nav-item">
                <a id="btnLogout" class="js-scroll-trigger" href="<%= logout%>"
                   style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                    Logout
                </a>
            </li>
        </form>

        <%----------------------------------------------Logout tab (End)----------------------------------------------%>

    </ul>
</div>
</div>
</nav>

<%-------------------------------------------------Navigation bar (End)-----------------------------------------------%>

<input type="hidden" id="graph" value="<%= request.getParameter("graph")%>">
<input type="hidden" id="subjectId" value="<%= request.getParameter("subjectId")%>">
<input type="hidden" id="lecturerId" value="<%= request.getParameter("lecturerId")%>">
<input type="hidden" id="dateOfSubmission" value="<%= request.getParameter("dateOfSubmission")%>">

<div class="row" style="margin-top: 100px">
    <div class="col-12" style="font-size: 20px;text-align: center" id="graphHeading"></div>
    <div class="col-12" style="font-size: 16px;text-align: center;margin-top: 10px">
        <%= request.getParameter("subjectName")%> / <%= request.getParameter("lecturerName")%> <br> <%= request.getParameter("dateOfSubmission")%>
    </div>
</div>

<div id="chart" style=""></div>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="${pageContext.request.contextPath}/controller/admin/graphController.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>