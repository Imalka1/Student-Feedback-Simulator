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
            },
            error: function () {

            }
        }
    );
}