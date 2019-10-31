<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

    //------------------------------------Check whether the session variable is alive-------------------------------
    if (sessionLogin.getAttribute("accountType") != null) {
        if (sessionLogin.getAttribute("accountType").equals("admin")) {
%>

<%------------------------------------------Navigate to admin landing page--------------------------------------------%>
<jsp:forward page="view/admin/results.jsp"/>
<%
} else {
%>

<%-----------------------------------------Navigate to student landing page-------------------------------------------%>
<jsp:forward page="view/student/landing_page(student).jsp"/>
<%
        }
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Student Feedback Simulator</title>

    <!---------------------------------------------Bootstrap CSS------------------------------------------------------->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <!----------------------------------- Custom fonts for this template ---------------------------------------------->
    <%--<link href="/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">--%>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!--------------------------------- Custom styles for this template ----------------------------------------------->
    <link href="${pageContext.request.contextPath}/assets/css/agency.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/latest/css/font-awesome.min.css">

    <%-------------------------------------------------Jquery---------------------------------------------------------%>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>

    <%-----------------------------------------------CSS(Start)-------------------------------------------------------%>

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

        body {
            background-image: url("${pageContext.request.contextPath}/assets/img/image.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            position: relative;
            min-height: 500px;
            overflow-y: scroll;
        }

        .box {
            display: flex;
            flex-flow: column;
            height: 100%;
        }

        .box .row1.content {
            flex: 1 1 auto;
        }

        .box .row1 .footer {
            flex: 0 1;
        }

        #mainNav {
            padding-top: 0px;
            padding-bottom: 0px;
        }
    </style>

    <%-------------------------------------------------CSS(End)-------------------------------------------------------%>

</head>

<%---------------------------------------------------Body (Start)-----------------------------------------------------%>

<body id="page-top">
<div class="box">
    <div class="row1 content">

        <%----------------------------------------------Navigation bar (Start)----------------------------------------%>

        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"
             style="background-color: rgba(35,35,35,0.81)">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger">Student Feedback Simulator</a>
            </div>
        </nav>

        <%----------------------------------------------Navigation bar (End)------------------------------------------%>

        <div class="row">
            <div class="col-12">

                <%--------------------Submit data to server (LoginController(url_controller)) (Start)-----------------%>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="row" style="margin-top:150px;margin-bottom: 25px">
                        <div class="col-md-4 col-center">
                            <input type="text" class="form-control" autofocus placeholder="Student / Admin ID"
                                   style="font-size: 16px"
                                   name="username" id="username">
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 25px">
                        <div class="col-md-4 col-center">
                            <input type="password" class="form-control" placeholder="Password" style="font-size: 16px"
                                   name="password">
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 15px">
                        <div class="col-md-4 col-center">
                            <button class="btn" type="submit"
                                    style="width: 100%;height: 45px;background-color: #ffbf05;font-size: 20px">
                                Login<i class="fa fa-sign-in" style="margin-left: 10px"></i>
                            </button>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 30px">
                        <div class="col-12 col-center" style="text-align: center;font-size: 14px;color: #422f02">
                            Login using your Student / Admin ID and Password
                        </div>
                    </div>

                    <%-------------------------------------Error message box (Start)----------------------------------%>

                    <%
                        if (request.getParameter("error") != null) {
                    %>
                    <div class="row">
                        <div class="col-md-4 col-center">
                            <div class="alert alert-danger" style="width: 100%;text-align: center">
                                Incorrect Student / Admin ID or Password
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                    <%-------------------------------------Error message box (End)------------------------------------%>

                </form>

                <%----------------------Submit data to server (LoginController(url_controller)) (End)-----------------%>

            </div>
        </div>

        <script>

            //------------------------------------------Username to uppercase---------------------------------------------------

            $('#username').keyup(function () {
                $(this).val($(this).val().toUpperCase())
            })
        </script>

        <%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="view/footer.jsp"/>