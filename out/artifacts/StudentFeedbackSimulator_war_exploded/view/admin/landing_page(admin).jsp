<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<%---------------------------------------------Add header(admin).jsp file---------------------------------------------%>
<jsp:include page="header(admin).jsp"/>
<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    HttpSession sessionLogin = request.getSession(false);
%>

<header class="masthead">
    <div class="container">
        <div class="intro-text" style="padding-top: 100px">
            <div class="col-center"
                 style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">
                <%
                    {
                        //--------Call the db server (AdminController(db_controller)) to retrieve admin data------------
                        Admin adminObj = new Admin();
                        adminObj.setuId(sessionLogin.getAttribute("uId").toString());
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
                                <h4 class="subheading">Results Portal
                                </h4>
                            </div>
                            <div class="timeline-body">
                                <p class="text-muted">View Results
                                </p>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</section>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="/controller/admin/landingPage(admin)Controller.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>
