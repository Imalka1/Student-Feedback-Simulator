//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadLecturers();
    loadSubjects();
});

//------------------------------------------------Load degrees----------------------------------------------------------

$('#btnAddDegree').click(function () {
    var degrees =
        '<tr style="font-size: 17px">' +
        '<td style="padding-left: 5px"><input type="hidden" value="' + $('#degree').children('option:selected').val() + '">' + $('#degree').children('option:selected').html() + '</td>' +
        '<td class="btnDegreeRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';

    $('#degreeBody').append(degrees);
})

function loadLecturers() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_lecturers",
            // data: {
            //     facultyId: $('#faculty').val()
            // },
            success: function (response) {
                var count = 0;
                var lecturers = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Lecturers.length; i++) {
                    lecturers +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Lecturers[i].LecturerId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Lecturers[i].LecturerName + '</td>' +
                        '<td class="btnViewLecturer" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i></td>' +
                        '</tr>';
                }
                $('#lecturersBody').html(lecturers);
            },
            error: function () {

            }
        }
    );
}

function loadSubjectsViaDegree(degreeId) {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_subjects_via_degree",
            data: {
                degreeId: degreeId
            },
            success: function (response) {
                var count = 0;
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Subjects[i].SubjectId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Subjects[i].SubjectName + '</td>' +
                        '<td style="text-align: center">' + obj.Subjects[i].Semester + '</td>' +
                        '<td class="btnSubjectRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';
                }
                $('#subjectDegreeBody').html(subjects);
            },
            error: function () {

            }
        }
    );
}

//-----------------------------------------------------Load subjects----------------------------------------------------

$('#semester').change(function () {
    loadSubjects();
})

function loadSubjects() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_subjects",
            data: {
                semesterId: $('#semester').val()
            },
            success: function (response) {
                var count = 0;
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Subjects[i].SubjectId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Subjects[i].SubjectName + '</td>' +
                        '<td style="text-align: center">' + obj.Subjects[i].Credits + '</td>' +
                        '<td class="btnAddSubject" style="text-align: center;cursor: pointer"><i style="font-size: 20px" class="fa fa-plus-circle"></i></td></tr>'
                }
                $('#subjectsBody').html(subjects);
            },
            error: function () {

            }
        }
    );
}

//-----------------------------------------------Add Delete Update------------------------------------------------------

$('#btnAdd').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/add_degree",
            data: {
                degreeTitle: $('#degreeTitle').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Degree programme has been submitted successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add degree programme</div>')
                }
                window.scrollTo(0, 0);
                setTimeout(function () {
                    $('#response').html('');
                }, 3000);
            },
            error: function () {

            }
        }
    );
})

$('#btnUpdate').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/update_degree",
            data: {
                degreeTitle: $('#degreeTitle').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Degree programme has been updated successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to update degree programme</div>')
                }
                window.scrollTo(0, 0);
                setTimeout(function () {
                    $('#response').html('');
                }, 3000);
            },
            error: function () {

            }
        }
    );
})

$('#btnDelete').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/delete_degree",
            data: {
                subjectId: $('#subjectId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Degree programme has been deleted successfully</div>');
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete degree programme</div>')
                }
                window.scrollTo(0, 0);
                setTimeout(function () {
                    $('#response').html('');
                }, 4000);
            },
            error: function () {

            }
        }
    );
})

//-------------------------------------------------Select degree on table----------------------------------------------

$(document).on('click', '.btnViewLecturer', function () {
    $('#lecturerId').val($(this).parent().children().eq(1).html())
    $('#lecturerName').val($(this).parent().children().eq(2).html())
    $('#subjectLecturerName').html('<b>Lecturer :- </b>' + $(this).parent().children().eq(2).html())
    setFieldsToExistingLecturer();
    // loadSubjectsViaDegree($(this).parent().children().eq(1).children('input').val())
})

//---------------------------------------------New vs existing----------------------------------------------------------

function setFieldsToExistingLecturer() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
}

function setFieldsToNewLecturer() {
    $('#btnAdd').prop("disabled", false);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewLecturer();
})

function setTextFieldsEmpty() {
    $('#lecturerId').val('');
    $('#lecturerName').val('');
    setFieldsToNewLecturer();
}

$(document).on('click', '.btnAddSubject', function () {
    var that = this;
    if ($('#lecturerId').val() !== '') {
        $.ajax(
            {
                type: "post",
                url: window.location.origin + "/add_subject_lecturer",
                data: {
                    lecturerId: $('#lecturerId').val(),
                    subjectId: $(that).parent().children().eq(1).html()
                },
                success: function (response) {
                    if (JSON.parse(response) == true) {
                        var subjects =
                            '<tr style="font-size: 17px">' +
                            '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ($('#subjectDegreeBody').children().length + 1) + '</td>' +
                            '<td style="text-align: center;font-weight: bold">' + $(that).parent().children().eq(1).html() + '</td>' +
                            '<td style="padding-left: 5px">' + $(that).parent().children().eq(2).html() + '</td>' +
                            '<td style="text-align: center">' + $('#semester').children('option:selected').html().trim() + '</td>' +
                            '<td class="btnSubjectRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';
                        $('#subjectDegreeBody').append(subjects);
                    } else {
                        $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add subject</div>')
                        window.scrollTo(0, 0);
                        setTimeout(function () {
                            $('#response').html('');
                        }, 3000);
                    }
                },
                error: function () {

                }
            }
        );
    } else {
        $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Please select a degree programme to add subjects</div>')
        window.scrollTo(0, 0);
        setTimeout(function () {
            $('#response').html('');
        }, 3000);
    }
});

$(document).on('click', '.btnSubjectRemove', function () {
    var that = this;
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/delete_subject_lecturer",
            data: {
                lecturerId: $('#lecturerId').val(),
                subjectId: $(that).parent().children().eq(1).html()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    $(that).parent().remove();
                    for (var i = 0; i < $('#subjectDegreeBody').children().length; i++) {
                        $('#subjectDegreeBody').children().eq(i).children().eq(0).html(i + 1)
                    }
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete subject</div>')
                    window.scrollTo(0, 0);
                    setTimeout(function () {
                        $('#response').html('');
                    }, 3000);
                }
            },
            error: function () {

            }
        }
    );
});