<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<jsp:include page="../header.jsp"/>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    HttpSession sessionLogin = request.getSession(false);
%>

<style>
    .dropdown-content {
        display: none;
        position: absolute;

        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        /*float: none;*/
        background-color: #f9f9f9;
        color: black;
        padding: 5px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }

    .dropdown-content a:hover {
        background-color: #ddd;
    }

    .nav-item:hover .dropdown-content {
        display: block;
    }

    /*.dropdown:hover .dropbtn {*/
        /*background-color: #3e8e41;*/
    /*}*/
</style>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                <span>Menu</span><i class="fa fa-caret-down" style="margin-left: 5px"></i>
            </a>
            <div class="dropdown-content" style="border-top: 12px solid rgba(0,0,0,0)">
                <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>
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

<!-- Header -->
<header class="masthead">
    <div class="container">
        <div class="intro-text" style="padding-top: 100px">
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        Admin adminObj = new Admin();
                        adminObj.setUid(sessionLogin.getAttribute("uid").toString());
                        Admin admin = new AdminController().getAdminUsername(adminObj);
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

                    <li class="timeline-inverted" style="cursor: pointer" id="studentAccountsPage">
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

                    <li class="timeline-inverted" style="cursor: pointer" id="manageData">
                        <div class="timeline-image">
                            <img class="rounded-circle img-fluid" alt="">
                        </div>
                        <div class="timeline-panel" style="padding-top: 50px">
                            <div class="timeline-heading">
                                <h4 class="subheading">Manage Data
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">Manage Subjects, Lecturers, Semesters
                                </p>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</section>

<script src="/controller/admin/landingPage(admin)Controller.js"></script>
<jsp:include page="../footer.jsp"/>
<!-- Footer -->
