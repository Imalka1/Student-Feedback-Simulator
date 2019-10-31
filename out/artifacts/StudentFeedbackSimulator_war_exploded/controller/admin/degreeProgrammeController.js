//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadDegrees();
    loadSubjects();
    validateSubmission();
});

//------------------------------------------------Load degrees----------------------------------------------------------

function loadDegrees() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_degrees",
            // data: {
            //     facultyId: $('#faculty').val()
            // },
            success: function (response) {
                var degrees = '';
                var count = 0;
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Degrees.length; i++) {
                    degrees +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="padding-left: 5px"><input type="hidden" value="' + obj.Degrees[i].DegId + '"><span>' + obj.Degrees[i].DegreeName + '</span></td>' +
                        '<td class="btnViewDegree" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i></td></tr>';
                }
                $('#degreeBody').html(degrees);
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

//-------------------------------------------------Select degree on table----------------------------------------------

$(document).on('click', '.btnViewDegree', function () {
    $('#degreeId').val($(this).parent().children().eq(1).children('input').val())
    $('#degreeTitle').val($(this).parent().children().eq(1).children('span').html())
    $('#subjectDegreeTitle').html('<b>Subject :- </b>' + $(this).parent().children().eq(1).children('span').html())
    setFieldsToExistingDegree();
    loadSubjectsViaDegree($(this).parent().children().eq(1).children('input').val())
})

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
                    loadDegrees();
                    setFieldsToNewDegree();
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
                degreeId: $('#degreeId').val(),
                degreeTitle: $('#degreeTitle').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadDegrees();
                    setFieldsToNewDegree();
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
                degreeId: $('#degreeId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadDegrees();
                    setFieldsToNewDegree();
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

//---------------------------------------------New vs existing----------------------------------------------------------

var newDegree = true;

function setFieldsToExistingDegree() {
    newDegree = false;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
}

function setFieldsToNewDegree() {
    newDegree = true;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#degreeTitle').val('')
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#degreeTitle').keyup(function () {
    validateSubmission();
})

$('#btnClear').click(function () {
    setFieldsToNewDegree();
    validateSubmission();
})

//------------------------------------------------Validate Fields-------------------------------------------------------

function validateSubmission() {
    if (newDegree && $('#degreeTitle').val() !== '') {
        $('#btnAdd').prop("disabled", false);
        $('#btnUpdate').prop("disabled", true);
    } else if (!newDegree && $('#degreeTitle').val() !== '') {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", false);
    } else {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", true);
        $('#btnDelete').prop("disabled", true);
    }
}

//-----------------------------------------Subjects Of Degree Programme-------------------------------------------------

$(document).on('click', '.btnAddSubject', function () {
    var that = this;
    if ($('#degreeId').val() !== '') {
        $.ajax(
            {
                type: "post",
                url: window.location.origin + "/add_subject_degree",
                data: {
                    degreeId: $('#degreeId').val(),
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
            url: window.location.origin + "/delete_subject_degree",
            data: {
                degreeId: $('#degreeId').val(),
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