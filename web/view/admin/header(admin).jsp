<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="../header.jsp"/>
<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
%>

<%---------------------------------------------------CSS dropdown(Start)----------------------------------------------%>

<style>
    .dropdown-content {
        display: none;
        position: absolute;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        background-color: #f9f9f9;
        color: black;
        padding: 10px 16px;
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
</style>

<%-----------------------------------------------------CSS dropdown(End)----------------------------------------------%>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">

        <%---------------------------------------------Dropdown menu (Start)------------------------------------------%>

        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                <span>Menu</span><i class="fa fa-caret-down" style="margin-left: 5px"></i>
            </a>
            <div class="dropdown-content" style="padding-top: 14px;font-size: 14px">
                <a href="landing_page(admin).jsp">Home</a>
                <a href="user_accounts.jsp">User Accounts</a>
                <a href="#">Subjetcs</a>
                <a href="#">Lecturers</a>
                <a href="#">Semesters</a>
                <a href="#">Evaluation Criterias</a>
            </div>
        </li>

        <%---------------------------------------------Dropdown menu (End)--------------------------------------------%>

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