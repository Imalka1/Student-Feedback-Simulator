//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadSubjects();
    validateSubmission();
});

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
                $('#degreeBody').html('')
                var count = 0;
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Subjects[i].SubjectId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Subjects[i].SubjectName + '</td>' +
                        '<td style="text-align: center">' + obj.Subjects[i].Credits + '</td>';
                    if (obj.Subjects[i].Allowed === true) {
                        subjects += '<td class="btnChangeAllow" style="text-align: center;cursor: pointer"><i class="fa fa-check" style="color: green"></i></td>'
                    } else {
                        subjects += '<td class="btnChangeAllow" style="text-align: center;cursor: pointer"><i class="fa fa-times" style="color: red"></i></td>'
                    }
                    subjects +=
                        '<td class="btnViewSubject" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i></td>' +
                        '</tr>';
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
            url: window.location.origin + "/add_subject",
            data: {
                semesterId: $('#semester').val(),
                subjectId: $('#subjectId').val(),
                subjectTitle: $('#subjectTitle').val(),
                credits: $('#credits').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                    setFieldsToNewSubject();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Subject has been submitted successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add subject</div>')
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
            url: window.location.origin + "/update_subject",
            data: {
                semesterId: $('#semester').val(),
                subjectId: $('#subjectId').val(),
                subjectTitle: $('#subjectTitle').val(),
                credits: $('#credits').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                    setFieldsToNewSubject();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Subject has been updated successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to update subject</div>')
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
            url: window.location.origin + "/delete_subject",
            data: {
                subjectId: $('#subjectId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                    setFieldsToNewSubject();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Subject has been deleted successfully</div>');
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete subject</div>')
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

$(document).on('click', '.btnChangeAllow', function () {
    if ($(this).children().hasClass('fa-check')) {
        setSubjectAllow($(this).parent().children('td').eq(1).html(), false);
    } else if ($(this).children().hasClass('fa-times')) {
        setSubjectAllow($(this).parent().children('td').eq(1).html(), true);
    }
});

function setSubjectAllow(subjectId, allow) {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: window.location.origin + "/change_allow",
            data: {
                subjectId: subjectId,
                allow: allow
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                }
            },
            error: function () {

            }
        }
    );
}

//-------------------------------------------------Select subject on table----------------------------------------------

$(document).on('click', '.btnViewSubject', function () {
    $('#subjectId').val($(this).parent().children().eq(1).html())
    $('#subjectTitle').val($(this).parent().children().eq(2).html())
    $('#credits').val($(this).parent().children().eq(3).html())
    setFieldsToExistingSubject();
})

//---------------------------------------------New vs existing----------------------------------------------------------

var newSubject = true;

function setFieldsToExistingSubject() {
    newSubject = false;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#subjectId').prop("disabled", true);
}

function setFieldsToNewSubject() {
    newSubject = true;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#subjectId').prop("disabled", false);
    $('#subjectId').val('')
    $('#subjectTitle').val('')
    $('#credits').val('1')
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#subjectId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
    validateSubmission();
})

$('#subjectTitle').keyup(function () {
    validateSubmission();
})

$('#credits').keyup(function () {
    validateSubmission();
})

$('#btnClear').click(function () {
    setFieldsToNewSubject();
    validateSubmission();
})

function validateSubmission() {
    if (newSubject && $('#subjectId').val() !== '' && $('#subjectTitle').val() !== '' && $('#credits').val() !== '') {
        $('#btnAdd').prop("disabled", false);
        $('#btnUpdate').prop("disabled", true);
    } else if (!newSubject && $('#subjectId').val() !== '' && $('#subjectTitle').val() !== '' && $('#credits').val() !== '') {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", false);
    } else {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", true);
    }
}