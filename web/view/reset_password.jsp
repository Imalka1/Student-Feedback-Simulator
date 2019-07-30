<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="header.jsp"/>
<%
    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

    //----------If an login error occurred, it retrieves the error data via query string and stores to variable---------
    String error = "";
    if (request.getParameter("error") != null) {
        error = request.getParameter("error");
    }
%>
<div class="navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">

        <%----------------------------------------------Login tab (Start)---------------------------------------------%>

        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white"
               href="../index.jsp">Login</a>
        </li>

        <%----------------------------------------------Login tab (End)-----------------------------------------------%>

    </ul>
</div>
</div>
</nav>

<%-------------------------------------------------Navigation bar (End)-----------------------------------------------%>

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">

    <%----------------------------------------------Error message box (Start)-----------------------------------------%>

    <%
        if (error.equals("errorReset")) {
    %>
    <div class="row">
        <div class="col-12" style="padding: 0px">
            <div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to reset Password</div>
        </div>
    </div>
    <%
        }
    %>

    <%-----------------------------------------------Error message box (End)------------------------------------------%>

    <%-----------------------Submit data to server (ResetController(url_controller)) (Start)--------------------------%>

    <form action="/reset_password" method="post">

        <%-------------------------------------------Store uid for Js stuff(Start)------------------------------------%>

        <input type="hidden" id="uid" name="userId" value="<%= sessionLogin.getAttribute("uid")%>">

        <%--------------------------------------------Store uid for Js stuff(End)-------------------------------------%>

        <div class="row">
            <div class="col-12" style="font-size: 32px;text-align: center">
                Reset Password
            </div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-3">
                New Password :
            </div>
            <div class="col-9">
                <input class="form-control" type="password" id="nPassword" name="password">
            </div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-3">
                Confirm Password :
            </div>
            <div class="col-9">
                <input class="form-control" type="password" id="cPassword">
            </div>
        </div>
        <div class="row" style="margin-top: 50px;margin-bottom: 30px">
            <div class="col-2" style="float: none;margin: 0 auto">
                <button id="btnResetPassword" type="submit" class="btn" disabled
                        style="background-color: #ffbf05;position: relative;left: 50%;transform: translateX(-50%)">Reset
                    Password
                </button>
            </div>
        </div>
    </form>

    <%-------------------------Submit data to server (ResetController(url_controller)) (End)--------------------------%>

</div>
</div>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="/controller/resetPasswordController.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="footer.jsp"/>