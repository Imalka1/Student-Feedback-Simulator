//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadSubjects();
});

//-----------------------------------------------------Load subjects----------------------------------------------------

$('#semester').change(function () {
    loadSubjects();
})

function loadSubjects() {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: window.location.origin + "/load_subjects",
            data: {
                semesterId: $('#semester').val()
            },
            success: function (response) {
                // console.log(response)
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects += '<option value="' + obj.Subjects[i].SubjectId + '">(' + obj.Subjects[i].SubjectId + ') - ' + obj.Subjects[i].SubjectName + '</option>';
                }
                $('#subjects').html(subjects);
                loadLecturers();
            },
            error: function () {

            }
        }
    );
}

//----------------------------------------------------Load lecturers----------------------------------------------------

$('#subjects').change(function () {
    loadLecturers();
})

function loadLecturers() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_lecturers",
            data: {
                subjectId: $('#subjects').val()
            },
            success: function (response) {
                // console.log(response)
                var lecturers = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Lecturers.length; i++) {
                    lecturers += '<option value="' + obj.Lecturers[i].LecturerId + '">' + obj.Lecturers[i].LecturerName + '</option>';
                }
                $('#lecturers').html(lecturers);
                loadDates();
            },
            error: function () {

            }
        }
    );
}

//-------------------------------------------------Load dates-----------------------------------------------------------

function loadDates() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_dates",
            data: {
                subjectId: $('#subjects').val(),
                lecturerId: $('#lecturers').val()
            },
            success: function (response) {
                var dates = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Dates.length; i++) {
                    dates += '<option value="' + obj.Dates[i].DateOfSubmission + '">' + obj.Dates[i].DateOfSubmission + '</option>';
                }
                $('#dates').html(dates);
                loadMarks();
            },
            error: function () {

            }
        }
    );
}

//-------------------------------------------------Load marks-----------------------------------------------------------

$('#dates').change(function () {
    loadMarks();
})

function loadMarks() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_marks",
            data: {
                subjectId: $('#subjects').val(),
                lecturerId: $('#lecturers').val(),
                dateOfSubmission: $('#dates').val()
            },
            success: function (response) {
                var count = 0;
                var tableData = '';
                var obj = JSON.parse(response);
                console.log(response)
                console.log(obj)
                for (var j = 0; j < obj.length; j++) {
                    tableData +=
                        '<tr>' +
                        '<td colspan="5" style="padding-left: 5px;font-weight: bold">' + obj[j].EvaluationCriteriaHeading + '</td>' +
                        '</tr>';
                    for (var i = 0; i < obj[j].Marks.length; i++) {
                        tableData +=
                            '<tr>' +
                            '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                            '<td style="padding-left: 5px">' + obj[j].Marks[i].EvaluationCriteria + '</td>' +
                            '<td style="text-align: center">' + obj[j].Marks[i].Marks + '</td>' +
                            '<td style="text-align: center">' + obj[j].Marks[i].StudentsCount + '</td>';
                        if (parseInt(obj[j].Marks[i].StudentsCount) == 0) {
                            tableData += '<td style="text-align: center">0</td></tr>';
                        } else {
                            tableData += '<td style="text-align: center">' + parseFloat(parseInt(obj[j].Marks[i].Marks) / parseInt(obj[j].Marks[i].StudentsCount)).toFixed(2) + '</td></tr>';
                        }
                    }
                }
                $('#marksBody').html(tableData);
            },
            error: function () {

            }
        }
    );
}

$('#btnGraph').click(function () {
    document.location.href = "graph.jsp";
})