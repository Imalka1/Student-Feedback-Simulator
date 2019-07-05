<%@ page import="model.*" %>
<%@ page import="controller.db_controller.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<jsp:include page="header.jsp"/>
<%
    String subjectId = request.getParameter("subjectId");
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="../index.jsp"/>
<%
        }
    }

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
%>

<%--<!-- Header -->--%>
<%--<header class="masthead">--%>
<%--<div class="container">--%>
<%--<div class="intro-text">--%>
<%--<div class="intro-lead-in">Department of Computer Science</div>--%>
<%--<div class="intro-lead-in">BSc (Computer Science)</div>--%>
<%--<div class="col-center"--%>
<%--style="background-color: #ffb508;width: fit-content;color: #402901;padding: 20px;padding-left: 30px;padding-right: 30px;font-size: 18px;border-radius: 35px;margin-top: 80px;font-weight: bold">--%>
<%--Online - abc@gmail.com--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</header>--%>
<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px;">
    <div class="row">
        <div class="col-12" style="padding: 0px">
            <div class="alert alert-success" style="text-align: center;font-weight: bold">
                Marks have been submitted successfully
            </div>
            <div class="alert alert-danger" style="text-align: center;font-weight: bold">
                Failed to submit marks
            </div>
        </div>
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
                    DegreeDTO degreeName = DegreeController.getDegreeName(sessionLogin.getAttribute("uid").toString());
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
            Year and semester
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    BatchDTO batchDTO = BatchController.getYearAndMonthDiff(sessionLogin.getAttribute("uid").toString());
                    if (batchDTO != null) {
                        SemesterDTO semesterDTO = new SemesterDTO();
                        semesterDTO.setSemid((batchDTO.getYearDiff() * 12 + batchDTO.getMonthDiff()) / 6 + 1);
                        SemesterDTO semesterName = SemesterController.getSemesterName(semesterDTO);
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
            Course Unit Title / Code / Number of credits
        </div>
        <div class="col-md-8" style="border: 1px solid black;color: #747474">
            <%
                {
                    SubjectDTO subjectDTO = SubjectController.getSubjectNameAndCredits(subjectId);
                    if (subjectDTO != null) {
            %>
            <%= subjectDTO.getSubjectName()%> / <%= subjectId%> / <%= subjectDTO.getSubjectName()%>
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
                        List<CriteriaDTO> criteriaHeadings = CriteriaController.getCriteriaHeadings();
                        for (CriteriaDTO criteriaHeadDTO : criteriaHeadings) {
                %>
                <tr>
                    <td colspan="7" class="padding_5_txt"
                        style="font-weight: 600"><%= criteriaHeadDTO.getCriteriaHeadingName()%>
                    </td>
                </tr>
                <%
                    {
                        List<CriteriaDTO> criterias = CriteriaController.getCriterias(criteriaHeadDTO.getEchid());
                        for (CriteriaDTO criteriaDTO : criterias) {
                %>
                <tr id="tr<%= ++value%>" class="trMarks">
                    <td style="text-align: right;padding-right: 5px"><%= value%>
                    </td>
                    <td style="padding-left: 5px">
                        <%= criteriaDTO.getCriteriaName()%><input type="hidden" value="<%= criteriaDTO.getEcid()%>">
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
<jsp:include page="footer.jsp"/>