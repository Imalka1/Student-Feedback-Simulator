<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Agency - Start Bootstrap Theme</title>

    <!-- Bootstrap core CSS -->
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
    <link rel="stylesheet" href="/assets/font-awesome/latest/css/font-awesome.min.css">
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

        #mainNav {
            padding-top: 0px;
            padding-bottom: 0px;
        }
    </style>
</head>
<body id="page-top">
<div class="box">
    <div class="row1 content">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"
             style="background-color: rgba(35,35,35,0.81)">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger">Student Feedback Simulator</a>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ml-auto">
                        <li class="nav-item">
                            <a class="js-scroll-trigger"
                               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white"
                               href="../index.jsp">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
            <div class="row">
                <div class="col-12" style="padding: 0px" id="messageBox"></div>
            </div>
            <div class="row">
                <div class="col-12" style="font-size: 13px"><b>Note:-</b> Type your student / admin id and click on
                    send.
                    System will send a confirmation code to your email.
                </div>
            </div>
            <div class="row" style="margin-top: 20px">
                <div class="col-3">
                    Student / Admin ID :
                </div>
                <div class="col-7">
                    <input class="form-control" type="text" id="userId">
                </div>
                <div class="col-2">
                    <button id="btnSendEmail" class="btn" style="background-color: #ffbf05;width: 100%" disabled>Send
                    </button>
                </div>
            </div>
            <div class="row" style="margin-top: 20px">
                <div class="col-3">
                    Confirmation Code :
                </div>
                <div class="col-9">
                    <input class="form-control" type="text" id="confirmationCode">
                </div>
            </div>

            <hr style="margin-top: 50px;margin-bottom: 50px;background-color: #b0b0b0">

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
                    <input class="form-control" type="password" id="nPassword" disabled>
                </div>
            </div>
            <div class="row" style="margin-top: 20px">
                <div class="col-3">
                    Confirm Password :
                </div>
                <div class="col-9">
                    <input class="form-control" type="password" id="cPassword" disabled>
                </div>
            </div>
            <div class="row" style="margin-top: 50px;margin-bottom: 30px">
                <div class="col-2" style="float: none;margin: 0 auto">
                    <button id="btnResetPassword" class="btn"
                            style="background-color: #ffbf05;position: relative;left: 50%;transform: translateX(-50%)"
                            disabled>Reset Password
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="/controller/forgotPasswordController.js"></script>
<jsp:include page="footer.jsp"/>