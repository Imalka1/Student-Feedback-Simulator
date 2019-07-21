<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Agency - Start Bootstrap Theme</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <!-- Custom fonts for this template -->
    <link href="/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="/assets/css/agency.min.css" rel="stylesheet">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <link rel="stylesheet" href="/assets/font-awesome/latest/css/font-awesome.min.css">
    <title>$Title$</title>
    <script src="/assets/js/jquery-3.2.1.min.js"></script>
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

        .box .row1.content {
            flex: 1 1 auto;
        }

        .box .row1 .footer {
            flex: 0 1;
        }

        @media (min-width: 992px) {
            #mainNav {
                padding-top: 0px;
                padding-bottom: 0px;
            }
        }
    </style>
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
                        <div id="btnForgotPassword" class="col-12"
                             style="text-align: center;font-size: 14px;color: #422f02;cursor: pointer">
                            (Forgot password?)
                        </div>
                    </div>
                    <div class="row" style="margin-bottom: 30px">
                        <div class="col-12 col-center" style="text-align: center;font-size: 14px;color: #422f02">
                            Login using your Student / Admin ID and Password
                        </div>
                    </div>
                    <%
                        if (error.equals("error")) {
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
                </form>
            </div>
        </div>
    </div>
    <div class="container footer">
        <div class="row">
            <div class="col-12">
                <hr style="margin-bottom: 10px">
                <div style="text-align: center;padding: 10px">Copyright &copy; - 2019</div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="/assets/js/jquery-3.2.1.min.js"></script>
<script>
    $('#username').keyup(function () {
        $(this).val($(this).val().toUpperCase())
    })

    $('#btnForgotPassword').click(function () {
        document.location.href = "view/forgot_password.jsp";
    })
</script>
</body>

</html>