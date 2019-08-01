<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="../header.jsp"/>
<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";

    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

    //---------------------Call the db server (StudentController(db_controller)) to retrieve student data---------------
    Student studentUserID = new Student();
    studentUserID.setuId(sessionLogin.getAttribute("uId").toString());
    Student student = new StudentController().getStudentLandingPageData(studentUserID);
%>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">

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

<header class="masthead">
    <div class="container">

        <%-----------------------------------------Display student data (Start)---------------------------------------%>

        <div class="intro-text" style="padding-top: 200px">
            <%
                {
                    if (student != null) {
            %>
            <div class="intro-lead-in"><%= student.getFacultyName()%>
            </div>
            <div class="intro-lead-in"><%= student.getDegreeName()%>
            </div>
            <%
                    }
                }
            %>
            <%
                {
                    if (student != null) {
            %>
            <div class="intro-lead-in" style="padding-top: 40px;color: #FFB508"><%= student.getBatchName()%>
                (<%= student.getSemesterName()%>)
            </div>
            <%
                    }
                }
            %>
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        if (student != null) {
                %>
                Online - <%= student.getStudentName()%>
                <%
                        }
                    }
                %>
            </div>
        </div>

        <%------------------------------------------Display student data (End)----------------------------------------%>

    </div>
</header>

<section id="about">
    <div class="container-fluid">
        <div class="row" style="margin-bottom: 80px">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase">Subjects</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12" style="padding: 0px">

                <%------------------------------------Display subject data (Start)------------------------------------%>

                <ul class="timeline">
                    <%
                        {
                            //-------Call the server (SubjectController(db_controller)) to retrieve subject data--------
                            student.setuId(sessionLogin.getAttribute("uId").toString());
                            List<Subject> subjects = new SubjectController().getSubjectsViaSemesterAndDegree(student);
                            for (Subject subject : subjects) {
                    %>

                    <li class="timeline-inverted <%= subject.isAllowed() ? "subjects" : ""%>" style="cursor: pointer">
                        <div class="timeline-image" <%= subject.isAllowed() ? "" : "style='background-color: #e9ecef'"%>>
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading" <%= subject.isAllowed() ? "" : "style='color: #6C757D'"%>><%= subject.getSubjectName()%>
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <input type="hidden" value="<%= subject.getSubjectId()%>">
                                <p class="text-muted"><%= subject.getSubjectId()%>
                                </p>
                                <p class="text-muted">Lecturer - <%= subject.getLecturerName()%>
                                </p>
                                <p class="text-muted">Credits - <%= subject.getCredits()%>
                                </p>
                            </div>
                        </div>
                    </li>
                    </a>
                    <%
                            }
                        }
                    %>
                </ul>

                <%------------------------------------Display subject data (End)--------------------------------------%>

            </div>
        </div>
    </div>
</section>
</div>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="/controller/student/landingPage(student)Controller.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>
