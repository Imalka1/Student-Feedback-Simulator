<%--
  Created by IntelliJ IDEA.
  User: Imalka Gunawardana
  Date: 2019-02-18
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("login") != null) {
            response.sendRedirect("subjects.jsp");
        }
    }

    String error = "";
    if (request.getParameter("error") != null) {
        error = request.getParameter("error");
    }
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/latest/css/font-awesome.min.css">
    <title>$Title$</title>
    <style>
        .col-center {
            float: none;
            margin: 0 auto
        }

        html,
        body {
            height: 100%;
            margin: 0
        }

        .box {
            display: flex;
            flex-flow: column;
            height: 100%;
        }

        .box .row1 {
            /*border: 1px dotted grey;*/
        }

        /*.box .row1.header {*/
        /*flex: 0 1 auto;*/
        /*!* The above is shorthand for:*/
        /*flex-grow: 0,*/
        /*flex-shrink: 1,*/
        /*flex-basis: auto*/
        /**!*/
        /*}*/

        .box .row1.content {
            flex: 1 1 auto;
        }

        .box .row1.footer {
            flex: 0 1;
        }
    </style>
</head>
<body class="container-fluid">
<div class="box">
    <div class="row1 content">
        <div class="row">
            <div class="col-12">
                <form action="login" method="post">
                    <div class="row" style="margin-bottom: 30px">
                        <div class="col-12 col-center"
                             style="text-align: center;margin-top: 60px;font-size: 30px;color: #007400;">
                            Student Feedback Form
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 30px">
                        <div class="col-12 col-center" style="text-align: center;font-size: 14px;color: #004800">
                            Login using your Student ID and Password
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
                            <button class="btn btn-success" type="submit"
                                    style="width: 100%;height: 45px;background-color: #006900;font-size: 20px">
                                Login<i class="fa fa-sign-in" style="margin-left: 10px"></i>
                            </button>
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

<jsp:include page="footer.jsp"/>