<jsp:include page="header(admin).jsp"/>

<div style="margin-top: 75px;margin-left: 30px;margin-right: 30px">
    <input type="hidden" value="<%= request.getParameter("subjectId")%>" id="subjectId">

    <div class="row">
        <div class="col-12" style="background-color: #f0f0f0;padding: 5px">
            <span style="border-right: 2px solid black;padding-right: 10px;font-weight: bold">Lecturers</span>
        </div>
    </div>

    <div class="row">
        <div class="col-12" id="response">

        </div>
    </div>

    <div class="row" style="margin-top: 30px;text-align: center;font-size: 25px">
        <div class="col-12">
            ( <%= request.getParameter("subjectId")%> ) - <%= request.getParameter("subjectTitle")%>
        </div>
    </div>

    <div class="row" style="margin-top:30px;text-align: center;font-size: 19px">
        <div class="col-3">
            Lecturer ID
        </div>
        <div class="col-7">
            Lecturer Name
        </div>
    </div>
    <div class="row">
        <div class="col-3">
            <input class="form-control" type="text" id="lecturerId">
        </div>
        <div class="col-7">
            <input class="form-control" type="text" id="lecturerName">
        </div>
        <div class="col-2">
            <div>
                <button id="btnClear" class="btn" style="background-color: #ffbf05;width: 100%">Clear</button>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnAdd" class="btn" style="background-color: #ffbf05">Submit</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnUpdate" class="btn" style="background-color: #ffbf05" disabled>Update</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnDelete" class="btn" style="background-color: #ffbf05" disabled>Delete</button>
            </div>
        </div>
    </div>

    <hr style="margin-top: 30px;margin-bottom: 30px;background-color: #b0b0b0">

    <div class="row">
        <div class="col-12" style="font-size: 32px;text-align: center">
            Lecturers
        </div>
    </div>

    <div class="row" style="margin-bottom: 50px;margin-top: 10px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr style="font-size: 18px">
                    <th width="5%"></th>
                    <th width="15%" style="text-align: center">Lecturer ID</th>
                    <th width="70%" style="text-align: center">Lecturer Name</th>
                    <th width="5%" style="text-align: center">Current</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="lecturersBody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="/controller/admin/lecturerController.js"></script>
<jsp:include page="../footer.jsp"/>