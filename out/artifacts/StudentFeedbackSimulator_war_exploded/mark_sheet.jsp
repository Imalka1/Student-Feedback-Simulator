<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("login") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="index.jsp"/>
<%
        }
    }
%>
<%
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
%>
<jsp:include page="header.jsp"/>
<style>
    @media (min-width: 992px) {
        #mainNav {
            padding-top: 0px;
            padding-bottom: 0px;
        }
    }
    .padding_5_txt{
        padding-left: 5px;
    }
</style>
<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color: #232323">
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

<%--<!-- Header -->--%>
<%--<header class="masthead">--%>
<%--<div class="container">--%>
<%--<div class="intro-text">--%>
<%--<div class="intro-lead-in">Department of Computer Science</div>--%>
<%--<div class="intro-lead-in">BSc (Computer Science)</div>--%>
<%--<div class="col-center"--%>
<%--style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">--%>
<%--Online - abc@gmail.com--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</header>--%>
<div class="box" style="margin-top: 80px;margin-left: 30px;margin-right: 30px;">
    <div class="row1 content">
        <div class="row">
            <div class="col-12" style="background-color: #FFB508;height: 30px"></div>
        </div>
        <div class="row">
            <div class="col-md-4" style="border: 1px solid black">
                Degree Programme
            </div>
            <div class="col-md-8" style="border: 1px solid black;color: #747474">
                BSc(Computer Science)
            </div>
        </div>
        <div class="row">
            <div class="col-md-4" style="border: 1px solid black">
                Year and semester
            </div>
            <div class="col-md-8" style="border: 1px solid black;color: #747474">
                Year 1 / Semester 1
            </div>
        </div>
        <div class="row">
            <div class="col-md-4" style="border: 1px solid black">
                Course Unit Title, Code and Number of credit
            </div>
            <div class="col-md-8" style="border: 1px solid black;color: #747474">
                Course Unit Title, Code and Number of credit
            </div>
        </div>
        <div class="row">
            <div class="col-md-4" style="border: 1px solid black">
                Date of Evaluation
            </div>
            <div class="col-md-8" style="border: 1px solid black;color: #747474">
                <%= date%>
            </div>
        </div>
        <div class="row">
            <div class="col-12" style="background-color: #FFB508;height: 30px"></div>
        </div>
        <div class="row">
            <div class="col-12" style="padding: 0px">
                <table border="1px" style="width: 100%">
                    <tr>
                        <th width="5%" style="text-align: center">Srt.No</th>
                        <th width="45%" style="text-align: center">Evaluation Criteria</th>
                        <th width="10%" style="text-align: center">Strongly Agree</th>
                        <th width="10%" style="text-align: center">Agree</th>
                        <th width="10%" style="text-align: center">Cannot Comment</th>
                        <th width="10%" style="text-align: center">Disagree</th>
                        <th width="10%" style="text-align: center">Strongly Disagree</th>
                    </tr>
                    <tr>
                       <td colspan="7" class="padding_5_txt">Enthusiasm</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;padding-right: 5px">1</td>
                        <td style="padding-left: 5px">Lecturer was punctual</td>
                        <td style="text-align: center">5</td>
                        <td style="text-align: center">4</td>
                        <td style="text-align: center">3</td>
                        <td style="text-align: center">2</td>
                        <td style="text-align: center">1</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>