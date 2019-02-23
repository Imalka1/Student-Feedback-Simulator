<%@ page import="db.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<%--<jsp:forward page="index.jsp"/>--%>
<%
        }
    }
    Connection connection = DBConnection.getDBConnection().getConnection();
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
                            Logout<i class="fa fa-sign-out" style="margin-left: 20px"></i></a>
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
                    PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name from user u,faculty f,degree d where f.facid=d.facid && d.degid=u.degid && u.uid=?");
                    preparedStatement.setObject(1, sessionLogin.getAttribute("uid"));
                    ResultSet rst = preparedStatement.executeQuery();
                    while (rst.next()) {
            %>
            <div class="intro-lead-in"><%= rst.getString(1)%>
            </div>
            <div class="intro-lead-in"><%= rst.getString(2)%>
            </div>
            <%
                    }
                }
            %>
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        PreparedStatement preparedStatement = connection.prepareStatement("select username from user where uid=?");
                        preparedStatement.setObject(1, sessionLogin.getAttribute("uid"));
                        ResultSet rst = preparedStatement.executeQuery();
                        while (rst.next()) {
                %>
                Online - <%= rst.getString(1)%>
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
    <div class="container">
        <div class="row" style="margin-bottom: 80px">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase">Subjects</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <ul class="timeline">
                    <li>
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" src="img/about/1.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Programming</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lecturer - Kamal Silva</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" src="img/about/2.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading">Database</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lecturer - Nimal Perera</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" src="img/about/3.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>December 2012</h4>
                                <h4 class="subheading">Transition to Full Service</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut
                                    voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit
                                    vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" src="img/about/4.jpg" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4>July 2014</h4>
                                <h4 class="subheading">Phase Two Expansion</h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut
                                    voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit
                                    vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                            </div>
                        </div>
                    </li>
                    <li class="timeline-inverted">
                        <div class="timeline-image">
                            <%--<h4>Be Part--%>
                            <%--<br>Of Our--%>
                            <%--<br>Story!</h4>--%>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<!-- Footer -->
