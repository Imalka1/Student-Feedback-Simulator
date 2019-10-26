<%
    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

    //------------------------------------Check whether the session variable is alive-----------------------------------
    if (sessionLogin.getAttribute("uId") == null) {
%>

<%-----------------------------------------------Navigate to login page-----------------------------------------------%>
<jsp:forward page="../index.jsp"/>
<%
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

    <!---------------------------------------------Bootstrap CSS------------------------------------------------------->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <!------------------------------------- Custom fonts for this template -------------------------------------------->
    <%--<link href="/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">--%>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!------------------------------------ Custom styles for this template -------------------------------------------->
    <link href="/assets/css/agency.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/font-awesome/latest/css/font-awesome.min.css">
    <%-------------------------------------------------Jquery---------------------------------------------------------%>
    <script src="/assets/js/jquery-3.2.1.min.js"></script>

    <script>
        $(window).bind("pageshow", function(event) {
            console.log(window.performance.navigation.type)
            var historyTraversal = event.persisted || ( window.performance !== undefined && window.performance.navigation.type === 2 );
            if ( historyTraversal ) {
                // Handle page restore.
                window.location.reload();
            }
        });
    </script>

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

<body id="page-top">

<%----------------------------------------------Store uId for Js stuff(Start)-----------------------------------------%>

<input type="hidden" id="uId" value="<%= sessionLogin.getAttribute("uId")%>">

<%-----------------------------------------------Store uId for Js stuff(End)------------------------------------------%>

<div class="box">
    <div class="row1 content">

        <%----------------------------------------------Navigation bar (Start)----------------------------------------%>

        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav"
             style="background-color: rgba(35,35,35,0.81)">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger">Student Feedback Simulator</a>