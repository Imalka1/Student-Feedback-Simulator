<%@ page import="model.*" %>
<%@ page import="controller.db_controller.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<%---------------------------------------------------Add header.jsp file----------------------------------------------%>
<jsp:include page="../header.jsp"/>
<%
    //----------------------------------------------URL to logout-------------------------------------------------------
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";

    //--------------------------Get Subject ID from query string which binded with URL----------------------------------
    String subjectId = request.getParameter("subjectId");

    //--------------------------------------------Load the current session----------------------------------------------
    HttpSession sessionLogin = request.getSession(false);

    //---------------------Call the db server (StudentController(db_controller)) to retrieve student data---------------
    Student studentUserID = new Student();
    studentUserID.setuId(sessionLogin.getAttribute("uId").toString());
    Student student = new StudentController().getStudentLandingPageData(studentUserID);

    //---------------------------------------------Get current date-----------------------------------------------------
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">

        <%----------------------------------------------Page back (Start)---------------------------------------------%>

        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white"
               href="landing_page(student).jsp">Back</a>
        </li>

        <%-----------------------------------------------Page back (End)----------------------------------------------%>

        <%----------------------------------------------Logout tab (Start)--------------------------------------------%>

        <form action="logout" method="post">
            <li class="nav-item">
                <a id="btnLogout" class="js-scroll-trigger" href="<%= logout%>"
                   style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                    Logout
                </a>
            </li>
        </form>

        <%----------------------------------------------Logout tab (End)----------------------------------------------%>

    </ul>
</div>
</div>
</nav>

<%-------------------------------------------------Navigation bar (End)-----------------------------------------------%>

<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px;">

    <%----------------------------------------------Error message box (Start)-----------------------------------------%>

    <div class="row">
        <div class="col-12" style="padding: 0px" id="messageBox"></div>
    </div>

    <%-----------------------------------------------Error message box (End)------------------------------------------%>

    <div class="row">
        <div class="col-12" style="background-color: #FFB508;height: 30px"></div>
    </div>

    <%-----------------------------------------------Subject details (Start)------------------------------------------%>

    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Degree Programme
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%= student.getDegreeName()%>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Year and Semester
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%= student.getSemesterName()%>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Course Unit Title / Code / Number of Credits / Lecturer
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    //---------Call the db server (SubjectController(db_controller)) to retrieve subject data-----------
                    Subject subjectObj = new Subject();
                    subjectObj.setSubjectId(subjectId);
                    Subject subject = new SubjectController().getSubjectNameAndCredits(subjectObj);
                    if (subject != null) {
            %>
            <%= subject.getSubjectName()%> / <%= subjectId%> / <%= subject.getCredits()%> / <%= subject.getLecturerName()%>

            <%-------------------------------Store subject lecturer ID for Js stuff(Start)----------------------------%>

            <input type="hidden" id="subjectLecturerId" value="<%= subject.getSubjectLecturerId()%>">

            <%-------------------------------Store subject lecturer ID for Js stuff(End)------------------------------%>

            <%
                    }
                }
            %>
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

    <%------------------------------------------------Subject details (End)-------------------------------------------%>

    <div class="row" style="margin-bottom: 50px">
        <div class="col-12" style="padding: 0px">

            <%--------------------------------------Display mark sheet (Start)----------------------------------------%>

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
                <%
                    {
                        int value = 0;

                        //--------Call the server (CriteriaController(db_controller)) to retrieve criteria heads--------
                        List<Criteria> criteriaHeadings = new CriteriaController().getCriteriaHeadings();
                        for (Criteria criteriaHeadDTO : criteriaHeadings) {
                %>
                <tr>
                    <td colspan="7" class="padding_5_txt"
                        style="font-weight: 600"><%= criteriaHeadDTO.getCriteriaHeadingName()%>
                    </td>
                </tr>
                <%
                    {
                        //--------Call the server (CriteriaController(db_controller)) to retrieve criterias-------------
                        Criteria criteriaObj = new Criteria();
                        criteriaObj.setEchId(criteriaHeadDTO.getEchId());
                        List<Criteria> criterias = new CriteriaController().getCriterias(criteriaObj);
                        for (Criteria criteria : criterias) {
                %>
                <tr id="tr<%= ++value%>" class="trMarks">
                    <td style="text-align: right;padding-right: 5px"><%= value%>
                    </td>
                    <td style="padding-left: 5px">
                        <%= criteria.getCriteriaName()%><input type="hidden" value="<%= criteria.getEcId()%>">
                    </td>
                    <td class="tdMark" style="text-align: center;cursor: pointer">5</td>
                    <td class="tdMark" style="text-align: center;cursor: pointer">4</td>
                    <td class="tdMark" style="text-align: center;cursor: pointer">3</td>
                    <td class="tdMark" style="text-align: center;cursor: pointer">2</td>
                    <td class="tdMark" style="text-align: center;cursor: pointer">1</td>
                </tr>
                <%
                                }
                            }
                        }
                    }
                %>
            </table>

            <%---------------------------------------Display mark sheet (End)-----------------------------------------%>

        </div>
    </div>

    <%--------------------------------------------Submit marks button (Start)-----------------------------------------%>

    <div class="row">
        <div class="col-12">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnSubmit" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Submit
                    Marks
                </button>
            </div>
        </div>
    </div>

    <%---------------------------------------------Submit marks button (End)------------------------------------------%>

</div>
</div>

<%-------------------------------------------Javascript controller of this page---------------------------------------%>
<script src="/controller/student/marksController.js"></script>

<%---------------------------------------------------Add footer.jsp file----------------------------------------------%>
<jsp:include page="../footer.jsp"/>