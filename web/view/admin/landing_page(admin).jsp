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
        <div class="intro-text" style="padding-top: 100px">
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        Admin admin = new AdminController().getAdminUsername(sessionLogin.getAttribute("uid").toString());
                        if (admin != null) {
                %>
                Online - <%= admin.getAdminName()%>
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
        <div class="row" style="margin-bottom: 30px">
            <div class="col-lg-12 text-center">
                <%--<h2 class="section-heading text-uppercase">Subjects</h2>--%>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12" style="padding: 0px">
                <ul class="timeline">

                    <li class="timeline-inverted" style="cursor: pointer" id="resultsPage">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel" style="padding-top: 50px">
                            <div class="timeline-heading">
                                <h4 class="subheading">Results
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">View Results
                                </p>
                            </div>
                        </div>
                    </li>

                    <li class="timeline-inverted" style="cursor: pointer" id="userAccountsPage">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel" style="padding-top: 50px">
                            <div class="timeline-heading">
                                <h4 class="subheading">User Accounts (Students)
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Manage Student Accounts
                                </p>
                            </div>
                        </div>
                    </li>

                    <%--</a>--%>
                    <%--<li class="timeline-inverted">--%>
                    <%--<div class="timeline-image" style="background-color: #c8a52a"></div>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../footer.jsp"/>
<!-- Footer -->
