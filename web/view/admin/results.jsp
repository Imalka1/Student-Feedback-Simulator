<jsp:include page="../header.jsp"/>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
%>
<div class="navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                <span>Menu</span><i class="fa fa-caret-down" style="margin-left: 5px"></i>
            </a>
            <div class="dropdown-content" style="border-top: 14px solid rgba(0,0,0,0)">
                <a href="landing_page(admin).jsp">Home</a>
                <a href="user_accounts.jsp">User Accounts</a>
                <a href="#">Subjetcs</a>
                <a href="#">Lecturers</a>
                <a href="#">Semesters</a>
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
<jsp:include page="../footer.jsp"/>