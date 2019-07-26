//---------------------------------------------------Initial Load-------------------------------------------------------
$(window).on("load", function () {
    loadDegrees();
});

//----------------------------------------------------Load Degree Programmes------------------------------------------------------------
$('#faculty').change(function () {
    loadDegrees();
})

function loadDegrees() {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: window.location.origin + "/load_degrees",
            data: {
                facultyId: $('#faculty').val()
            },
            success: function (response) {
                var degrees = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Degrees.length; i++) {
                    degrees += '<option value="' + obj.Degrees[i].DegId + '">' + obj.Degrees[i].DegreeName + '</option>';
                }
                $('#degree').html(degrees);
                loadSubjects();
            },
            error: function () {

            }
        }
    );
}

$('#degree').change(function () {
    loadSubjects();
})

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
                degreeId: $('#degree').val(),
                semesterId: $('#semester').val()
            },
            success: function (response) {
                console.log(response)
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects += '<option value="' + obj.Subjects[i].SubjectId + '">' + obj.Subjects[i].SubjectName + '</option>';
                }
                $('#subjects').html(subjects);
                loadLecturers();
            },
            error: function () {

            }
        }
    );
}

$('#subjects').change(function () {
    loadLecturers();
})

function loadLecturers() {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: window.location.origin + "/load_lecturers",
            data: {
                subjectId: $('#subjects').val()
            },
            success: function (response) {
                console.log(response)
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

function loadDates() {
    $.ajax(
        {
            // async: false,
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

function loadMarks() {
    $.ajax(
        {
            // async: false,
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
                for (var i = 0; i < obj.Marks.length; i++) {
                    tableData +=
                        '<tr>' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Marks[i].EvaluationCriteria + '</td>' +
                        '<td style="text-align: center">' + obj.Marks[i].Marks + '</td>' +
                        '<td style="text-align: center">' + obj.Marks[i].StudentsCount + '</td>' +
                        '</tr>';
                }
                $('#marksBody').html(tableData);
            },
            error: function () {

            }
        }
    );
}