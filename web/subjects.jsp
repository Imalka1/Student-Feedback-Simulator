<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="index.jsp"/>
<%
        }
    }
    DegreeDTO degreeDTO;
    SemesterDTO semesterDTO;
%>
<jsp:include page="header.jsp"/>

<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger">Student Feedback Form</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
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

<!-- Header -->
<header class="masthead">
    <div class="container">
        <div class="intro-text">
            <%
                {
                    degreeDTO = DegreeController.getDegreeData(sessionLogin.getAttribute("uid").toString());
                    if (degreeDTO != null) {
            %>
            <div class="intro-lead-in"><%= degreeDTO.getFacultyName()%>
            </div>
            <div class="intro-lead-in"><%= degreeDTO.getDegreeName()%>
            </div>
            <%
                    }
                }
            %>
            <%
                {
                    BatchDTO batchDTO = BatchController.getYearAndMonthDiff(sessionLogin.getAttribute("uid").toString());
                    semesterDTO = new SemesterDTO();
                    if (batchDTO != null) {
                        semesterDTO.setSemid((batchDTO.getYearDiff() * 12 + batchDTO.getMonthDiff()) / 6 + 1);
                        SemesterDTO semesterName = SemesterController.getSemesterName(semesterDTO);
                        if (semesterName != null) {
            %>
            <div class="intro-lead-in" style="padding-top: 40px;color: #FFB508"><%= semesterName.getSemesterName()%>
            </div>
            <%
                        }
                    }
                }
            %>
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        UserDTO userDTO = UserController.getUsername(sessionLogin.getAttribute("uid").toString());
                        if (userDTO != null) {
                %>
                Online - <%= userDTO.getUsername()%>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</header>

<!-- About -->
<section id="about">
    <div class="container-fluid">
        <div class="row" style="margin-bottom: 80px">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase">Subjects</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12" style="padding: 0px">
                <ul class="timeline">
                    <%
                        {
                            ArrayList<SubjectDTO> subjects = SubjectController.getSubjectsViaSemesterAndDegree(degreeDTO.getDegid(), semesterDTO.getSemid());
                            for (SubjectDTO subjectDTO : subjects) {
                    %>
                    <li class="timeline-inverted subjects" style="cursor: pointer">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading"><%= subjectDTO.getSubjectName()%>
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <input type="hidden" value="<%= subjectDTO.getSubjectId()%>">
                                <p class="text-muted"><%= subjectDTO.getSubjectId()%>
                                </p>
                                <p class="text-muted">Lecturer - <%= subjectDTO.getLecturerName()%>
                                </p>
                                <p class="text-muted">Credits - <%= subjectDTO.getCredits()%>
                                </p>
                            </div>
                        </div>
                    </li>
                    </a>
                    <%
                            }
                        }
                    %>
                    <%--<li class="timeline-inverted">--%>
                    <%--<div class="timeline-image" style="background-color: #c8a52a"></div>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<!-- Footer -->
