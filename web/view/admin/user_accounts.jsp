<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>

<jsp:include page="../header.jsp"/>
<%
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="../../index.jsp"/>
<%
        }
    }
%>
<style>
    @media (min-width: 992px) {
        #mainNav {
            padding-top: 0px;
            padding-bottom: 0px;
        }
    }

    .padding_5_txt {
        padding-left: 5px;
    }
</style>
<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px;">
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
                        ArrayList<CriteriaDTO> criteriaHeadings = CriteriaController.getCriteriaHeadings();
                        for (CriteriaDTO criteriaHeadDTO : criteriaHeadings) {
                %>
                <tr>
                    <td colspan="7" class="padding_5_txt"
                        style="font-weight: 600"><%= criteriaHeadDTO.getCriteriaHeadingName()%>
                    </td>
                </tr>
                <%
                    {
                        ArrayList<CriteriaDTO> criterias = CriteriaController.getCriterias(criteriaHeadDTO.getEchid());
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
    <%--<div class="row">--%>
    <%--<div class="col-12" style="background-color: #FFB508;height: 30px"></div>--%>
    <%--</div>--%>
</div>
</div>
<%--</div>--%>
<jsp:include page="../footer.jsp"/>