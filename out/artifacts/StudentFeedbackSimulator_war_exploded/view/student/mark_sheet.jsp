<%@ page import="model.*" %>
<%@ page import="controller.db_controller.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<jsp:include page="../header.jsp"/>
<%
    String logout = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/logout";
    String subjectId = request.getParameter("subjectId");
    HttpSession sessionLogin = request.getSession(false);

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
%>

<div class="collapse navbar-collapse" id="navbarResponsive">
    <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item" style="margin-right: 50px">
            <a class="js-scroll-trigger"
               style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white"
               href="landing_page(student).jsp">Back</a>
        </li>
        <form action="logout" method="post">
            <li class="nav-item">
                <a id="btnLogout" class="js-scroll-trigger" href="<%= logout%>"
                   style="cursor: pointer;font-family: Montserrat,-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,'Helvetica Neue',Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol','Noto Color Emoji';text-decoration: none;color: white">
                    Logout
                    <%--<i class="fa fa-sign-out" style="margin-left: 20px"></i>--%>
                </a>
            </li>
        </form>
    </ul>
</div>
</div>
</nav>
<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px;">
    <div class="row">
        <div class="col-12" style="padding: 0px" id="messageBox"></div>
    </div>
    <div class="row">
        <div class="col-12" style="background-color: #FFB508;height: 30px"></div>
    </div>
    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Degree Programme
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    Student studentObj = new Student();
                    studentObj.setUid(sessionLogin.getAttribute("uid").toString());
                    Degree degreeName = new DegreeController().getDegreeName(studentObj);
                    if (degreeName != null) {
            %>
            <%= degreeName.getDegreeName()%>
            <%
                    }
                }
            %>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Year and Semester
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    Student studentObj = new Student();
                    studentObj.setUid(sessionLogin.getAttribute("uid").toString());
                    Batch batch = new BatchController().getYearAndMonthDiff(studentObj);
                    if (batch != null) {
                        Semester semester = new Semester();
                        semester.setSemid((batch.getYearDiff() * 12 + batch.getMonthDiff()) / 6 + 1);
                        Semester semesterName = new SemesterController().getSemesterName(semester);
                        if (semesterName != null) {
            %>
            <%= semesterName.getSemesterName()%>
            <%
                        }
                    }
                }
            %>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4" style="border: 1px solid black">
            Course Unit Title / Code / Number of Credits / Lecturer
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    Subject subjectObj = new Subject();
                    subjectObj.setSubjectId(subjectId);
                    Subject subject = new SubjectController().getSubjectNameAndCredits(subjectObj);
                    if (subject != null) {
            %>
            <%= subject.getSubjectName()%> / <%= subjectId%> / <%= subject.getCredits()%>
            / <%= subject.getLecturerName()%>
            <input type="hidden" id="sublecid" value="<%= subject.getSublecId()%>">
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
    <div class="row" style="margin-bottom: 50px">
        <div class="col-12" style="padding: 0px">
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
                        Criteria criteriaObj = new Criteria();
                        criteriaObj.setEchid(criteriaHeadDTO.getEchid());
                        List<Criteria> criterias = new CriteriaController().getCriterias(criteriaObj);
                        for (Criteria criteria : criterias) {
                %>
                <tr id="tr<%= ++value%>" class="trMarks">
                    <td style="text-align: right;padding-right: 5px"><%= value%>
                    </td>
                    <td style="padding-left: 5px">
                        <%= criteria.getCriteriaName()%><input type="hidden" value="<%= criteria.getEcid()%>">
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
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnSubmit" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Submit
                    Marks
                </button>
            </div>
        </div>
    </div>
</div>
</div>

<script src="/controller/student/marksController.js"></script>
<jsp:include page="../footer.jsp"/>