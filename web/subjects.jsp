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
<jsp:forward page="index.jsp"/>
<%
        }
    }
    Connection connection = DBConnection.getDBConnection().getConnection();
    int degid = 0;
    int semid = 0;
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
                    PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid from user u,faculty f,degree d where f.facid=d.facid && d.degid=u.degid && u.uid=?");
                    preparedStatement.setObject(1, sessionLogin.getAttribute("uid"));
                    ResultSet rst = preparedStatement.executeQuery();
                    if (rst.next()) {
                        degid = rst.getInt(3);
            %>
            <div class="intro-lead-in"><%= rst.getString(1)%>
            </div>
            <div class="intro-lead-in"><%= rst.getString(2)%>
            </div>
            <%
                    }
                }
            %>
            <%
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("select year(curdate())-year(b.intake),month(curdate())-month(b.intake) from user u,batch b where u.batchid=b.batchid && u.uid=?");
                    preparedStatement.setObject(1, sessionLogin.getAttribute("uid"));
                    ResultSet rst = preparedStatement.executeQuery();
                    if (rst.next()) {
                        int yearDiff = rst.getInt(1);
                        int monthDiff = rst.getInt(2);
                        semid = (yearDiff * 12 + monthDiff) / 6 + 1;
                        preparedStatement = connection.prepareStatement("select text from semester where semid=?");
                        preparedStatement.setObject(1, semid);
                        rst = preparedStatement.executeQuery();
                        if (rst.next()) {
            %>
            <div class="intro-lead-in" style="padding-top: 40px;color: #FFB508"><%= rst.getString(1)%>
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
                            PreparedStatement preparedStatement = connection.prepareStatement("select s.subid,title,l.name,credits from subject s,lecturer l where l.lecid=s.lecid && degid=? && semid=?");
                            preparedStatement.setObject(1, degid);
                            preparedStatement.setObject(2, semid);
                            ResultSet rst = preparedStatement.executeQuery();
                            while (rst.next()) {
                    %>
                    <li class="timeline-inverted subjects" style="cursor: pointer">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="subheading"><%= rst.getString(2)%>
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <input type="hidden" value="<%= rst.getString(1)%>">
                                <p class="text-muted"><%= rst.getString(1)%>
                                </p>
                                <p class="text-muted">Lecturer - <%= rst.getString(3)%>
                                </p>
                                <p class="text-muted">Credits - <%= rst.getString(4)%>
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
