<%
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="../index.jsp"/>
<%
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Student Feedback Simulator</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <!-- Custom fonts for this template -->
    <link href="/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
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


        @media (min-width: 992px) {
            #mainNav {
                padding-top: 0px;
                padding-bottom: 0px;
            }
        }
    </style>
</head>
<body id="page-top">
<input type="hidden" id="uid" value="<%= sessionLogin.getAttribute("uid")%>">
<div class="box">
    <div class="row1 content">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color: rgba(35,35,35,0.81)">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger">Student Feedback Simulator</a>
                <%--<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"--%>
                        <%--data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"--%>
                        <%--aria-label="Toggle navigation">--%>
                    <%--Menu--%>
                    <%--<i class="fas fa-bars"></i>--%>
                <%--</button>--%>