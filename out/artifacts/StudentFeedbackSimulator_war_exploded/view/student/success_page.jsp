<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Student" %>
<%@ page import="model.Subject" %>
<%@ page import="controller.db_controller.SubjectController" %>
<%@ page import="java.util.List" %><%---------------------------------------------------Add header.jsp file----------------------------------------------%>
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
               href="landing_page(student).jsp">Subjects</a>
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

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
    <div class="row">
        <div class="col-12" style="text-align: center;font-weight: bold;font-size: 28px;color: #656565">
            Thank You
        </div>
    </div>
    <div class="row">
        <div class="col-12" style="text-align: center;margin-top: 15px;margin-bottom:25px;font-size: 23px;color: #656565">
            <span>You have successfully submitted marks for</span>
        </div>
    </div>

    <%
        {
            //-------Call the server (SubjectController(db_controller)) to retrieve subject data--------
            Student studentUserID = new Student();
            studentUserID.setuId(sessionLogin.getAttribute("uId").toString());
            List<Subject> subjects = new SubjectController().getAllowedSubjectsViaSemesterAndDegree(studentUserID);
            for (Subject subject : subjects) {
                if(subject.isAllowed()){
    %>

    <div class="row">
        <div class="col-12" style="text-align: center;margin-top: 15px;font-size: 20px">
            <span><%= subject.getSubjectName()%></span>
        </div>
    </div>

    <%
                }
            }
        }
    %>

    <div class="row">
        <div class="col-12" style="text-align: center;margin-top: 40px;font-size: 23px;color: #656565">
            <span>on <%= new SimpleDateFormat("yyyy-MM-dd").format(new Date())%></span>
        </div>
    </div>
</div>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>