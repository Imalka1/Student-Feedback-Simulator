<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="../header.jsp"/>

<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";

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

<div id="chart" style="margin-top: 100px;margin-left: 20px"></div>

<div class="row" style="margin: 10px">
    <div class="col-6" style="border: 1px solid black">
        <div class="row">
            <div class="col-12" style="text-align: center;border-bottom: 1px solid black">
                X - Axis
            </div>
            <div class="col-2" style="padding-left: 5px;border-right: 1px solid black">
                Criteria 1
            </div>
            <div class="col-10" style="padding-left: 5px">
                Lecturer was punctual
            </div>
        </div>
    </div>
    <div class="col-6" style="border: 1px solid black">
        <div class="row">
            <div class="col-12" style="text-align: center;border-bottom: 1px solid black">
                Y - Axis
            </div>
            <div class="col-2" style="padding-left: 5px;border-right: 1px solid black">
                1
            </div>
            <div class="col-10" style="padding-left: 5px">
                Strongly Disagree
            </div>
        </div>
    </div>
</div>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="/controller/admin/graphController.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>