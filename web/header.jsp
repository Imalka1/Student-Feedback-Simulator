<%--
  Created by IntelliJ IDEA.
  User: Imalka Gunawardana
  Date: 2019-02-18
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
        <div class="row" style="margin-top: 10px">
            <%--<div class="col-md-4" style="font-size: 20px;color: #007400;">--%>
                <%--Student Feedback Form--%>
            <%--</div>--%>
            <div class="col-md-10" style="text-align: right">
                <div style="padding: 5px;width: fit-content;float: right">Online - abc@gmail.com</div>
            </div>
            <div class="col-md-2">
                <form action="logout" method="post">
                    <button class="btn btn-success" style="width: 100%;background-color: #006900">Logout<i class="fa fa-sign-out" style="margin-left: 10px"></i>
                    </button>
                </form>
            </div>
        </div>