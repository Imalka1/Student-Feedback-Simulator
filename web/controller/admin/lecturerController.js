//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadLecturers();
    loadSubjects();
});

$('#lecturerId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

//------------------------------------------------Load degrees----------------------------------------------------------

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

function loadSubjectsViaLecturer(lecturerId) {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_subjects_via_lecturer",
            data: {
                lecturerId: lecturerId
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
                        '<td style="text-align: center">' + obj.Subjects[i].Semester + '</td>';
                    if (obj.Subjects[i].Current === true) {
                        subjects += '<td style="text-align: center;cursor: pointer" class="btnCurrentLecturer"><i class="fa fa-check" style="color: green"></i></td>'
                    } else {
                        subjects += '<td style="text-align: center;cursor: pointer" class="btnCurrentLecturer"><i class="fa fa-times" style="color: red"></i></td>'
                    }
                    subjects +=
                        '<td class="btnSubjectRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';
                }
                $('#subjectLecturerBody').html(subjects);
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
            url: window.location.origin + "/add_lecturer",
            data: {
                lecturerId: $('#lecturerId').val(),
                lecturerName: $('#lecturerName').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer  has been submitted successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add lecturer </div>')
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
            url: window.location.origin + "/update_lecturer",
            data: {
                lecturerId: $('#lecturerId').val(),
                lecturerName: $('#lecturerName').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer  has been updated successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to update lecturer </div>')
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
            url: window.location.origin + "/delete_lecturer",
            data: {
                lecturerId: $('#lecturerId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer  has been deleted successfully</div>');
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete lecturer </div>')
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
    loadSubjectsViaLecturer($(this).parent().children().eq(1).html())
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
                            '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ($('#subjectLecturerBody').children().length + 1) + '</td>' +
                            '<td style="text-align: center;font-weight: bold">' + $(that).parent().children().eq(1).html() + '</td>' +
                            '<td style="padding-left: 5px">' + $(that).parent().children().eq(2).html() + '</td>' +
                            '<td style="text-align: center">' + $('#semester').children('option:selected').html().trim() + '</td>' +
                            '<td style="text-align: center;cursor: pointer" class="btnCurrentLecturer"><i class="fa fa-times" style="color: red"></i></td>' +
                            '<td class="btnSubjectRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';
                        $('#subjectLecturerBody').append(subjects);
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
        $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Please select a lecturer to add subjects</div>')
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
                if (JSON.parse(response) === true) {
                    $(that).parent().remove();
                    for (var i = 0; i < $('#subjectLecturerBody').children().length; i++) {
                        $('#subjectLecturerBody').children().eq(i).children().eq(0).html(i + 1)
                    }
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete lecturer</div>')
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

$(document).on('click', '.btnCurrentLecturer', function () {
    var that = this;
    console.log($(that).html())
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/change_current_lecturer",
            data: {
                lecturerId: $('#lecturerId').val(),
                subjectId: $(that).parent().children().eq(1).html()
            },
            success: function (response) {
                if (JSON.parse(response) === true) {
                    if ($(that).children().attr('class') === 'fa fa-check') {
                        $(that).html('<i class="fa fa-times" style="color: red"></i>')
                    } else if ($(that).children().attr('class') === 'fa fa-times') {
                        $(that).html('<i class="fa fa-check" style="color: green"></i>')
                    }
                }
            }
        }
    );
})