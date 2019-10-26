//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadLecturers();
});

$('#lecturerId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

//--------------

function loadLecturers() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_lecturers",
            data: {
                subjectId: $('#subjectId').val()
            },
            success: function (response) {
                var count = 0;
                var lecturers = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Lecturers.length; i++) {
                    lecturers +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Lecturers[i].LecturerId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Lecturers[i].LecturerName + '</td>';
                    if (obj.Lecturers[i].Current === true) {
                        lecturers += '<td style="text-align: center;cursor: pointer" class="btnCurrentLecturer"><i class="fa fa-check" style="color: green"></i></td>'
                    } else {
                        lecturers += '<td style="text-align: center;cursor: pointer" class="btnCurrentLecturer"><i class="fa fa-times" style="color: red"></i></td>'
                    }
                    lecturers +=
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

//-----------------------------------------------Add Delete Update------------------------------------------------------

$('#btnAdd').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/add_lecturer",
            data: {
                lecturerId: $('#lecturerId').val(),
                lecturerName: $('#lecturerName').val(),
                subjectId: $('#subjectId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadLecturers();
                    setTextFieldsEmpty();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer has been submitted successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add lecturer</div>')
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
                lecturerName: $('#lecturerName').val(),
                subjectId: $('#subjectId').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadLecturers();
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer has been updated successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to update lecturer</div>')
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
                    loadLecturers();
                    setTextFieldsEmpty();
                    setFieldsToNewLecturer();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Lecturer has been deleted successfully</div>');
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete lecturer</div>')
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

//-------------------------------------------------Select subject on table----------------------------------------------

$(document).on('click', '.btnCurrentLecturer', function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/change_current_lecturer",
            data: {
                subjectId: $('#subjectId').val(),
                lecturerId: $(this).parent().children().eq(1).html()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadLecturers();
                }
            }
        }
    );
})

$(document).on('click', '.btnViewLecturer', function () {
    $('#lecturerId').val($(this).parent().children().eq(1).html())
    $('#lecturerName').val($(this).parent().children().eq(2).html().split('(')[0])
    setFieldsToExistingLecturer();
})

//---------------------------------------------New vs existing----------------------------------------------------------

function setFieldsToExistingLecturer() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#lecturerId').prop("disabled", true);
}

function setFieldsToNewLecturer() {
    $('#btnAdd').prop("disabled", false);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#lecturerId').prop("disabled", false);
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewLecturer();
})

function setTextFieldsEmpty() {
    $('#lecturerId').val('')
    $('#lecturerName').val('')
    setFieldsToNewLecturer();
}