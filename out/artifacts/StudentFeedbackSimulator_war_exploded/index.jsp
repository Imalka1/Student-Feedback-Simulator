<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="main_header.jsp"/>
<%
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("login") != null) {
            response.sendRedirect("landing_page(student).jsp");
        }
    }

    String error = "";
    if (request.getParameter("error") != null) {
        error = request.getParameter("error");
    }
%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<style>
    body {
        background-image: url("/assets/img/image.jpg");
        background-size: cover;
        background-repeat: no-repeat;
        /*background-position: center center;*/
        background-attachment: fixed;
        position: relative;
        min-height: 500px;
        overflow-y: scroll;
        /*background-color: #D49E00;*/
    }
</style>
<body class="container-fluid">
<div class="box">
    <div class="row1 content">
        <div class="row">
            <div class="col-12">
                <form action="/login" method="post">
                    <div class="row" style="margin-bottom: 60px">
                        <div class="col-12 col-center"
                             style="text-align: center;margin-top: 80px;font-size: 30px;color: #ffffff">
                            Student Feedback Form
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 25px">
                        <div class="col-md-4 col-center">
                            <input type="text" class="form-control" autofocus placeholder="Student ID"
                                   style="font-size: 16px"
                                   name="username">
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 25px">
                        <div class="col-md-4 col-center">
                            <input type="password" class="form-control" placeholder="Password" style="font-size: 16px"
                                   name="password">
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 25px">
                        <div class="col-md-4 col-center">
                            <button class="btn" type="submit"
                                    style="width: 100%;height: 45px;background-color: #ffbf05;font-size: 20px">
                                Login<i class="fa fa-sign-in" style="margin-left: 10px"></i>
                            </button>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 30px">
                        <div class="col-12 col-center" style="text-align: center;font-size: 14px;color: #422f02">
                            Login using your Student ID and Password
                        </div>
                    </div>
                    <%
                        if (error.equals("error")) {
                    %>
                    <div class="row">
                        <div class="col-md-4 col-center">
                            <div class="alert alert-danger" style="width: 100%;text-align: center">
                                Incorrect Student ID or Password
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>
    </div>
<jsp:include page="view/footer.jsp"/>