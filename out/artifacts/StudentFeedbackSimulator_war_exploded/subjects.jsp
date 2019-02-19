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
<jsp:include page="header.jsp"/>
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
                    <button class="btn btn-success" style="width: 100%;background-color: #006900">Logout<i
                            class="fa fa-sign-out" style="margin-left: 10px"></i>
                    </button>
                </form>
            </div>
        </div>
        <div class="row" style="margin-top: 10px">
            <div class="col-12">
                <div style="text-align: center;background-color: #006900;border-radius: 25px;padding: 10px;margin-left: 10px;margin-right: 10px;font-size: 19px;color: #fdfdfd">
                    BSc(Computer Science) - Department of Computer Science
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 30px">
            <div class="col-md-4">
                <div style="background-color: #f0bc06;border-radius: 30px;padding: 10px;text-align: center;margin: 20px;margin-top: 10px">
                    abc
                </div>
            </div>
            <div class="col-md-4">
                <div style="background-color: #f0bc06;border-radius: 30px;padding: 10px;text-align: center;margin: 20px;margin-top: 10px">
                    abc
                </div>
            </div>
            <div class="col-md-4">
                <div style="background-color: #f0bc06;border-radius: 30px;padding: 10px;text-align: center;margin: 20px;margin-top: 10px">
                    abc
                </div>
            </div>
            <div class="col-md-4">
                <div style="background-color: #f0bc06;border-radius: 30px;padding: 10px;text-align: center;margin: 20px;margin-top: 10px">
                    abc
                </div>
            </div>
        </div>
    </div>
    <div class="row1 footer">
        <div class="row">
            <div class="col-12" style="text-align: center;border-top: #dcdcdc 1px solid;font-size: 11px;padding: 10px">
                <div>&copy; Copyright - 2019</div>
            </div>
        </div>
    </div>

</div>

<%--<hr style="margin-right: 10px;margin-left: 10px;bottom: 10px">--%>

</body>
</html>
