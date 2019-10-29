//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadDegrees();
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

function loadDegreesViaSubject(subjectId) {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_degrees_via_subject",
            data: {
                subjectId: subjectId
            },
            success: function (response) {
                var obj = JSON.parse(response);
                var degrees = '';
                for (var i = 0; i < obj.Degrees.length; i++) {
                    degrees +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="padding-left: 5px"><input type="hidden" value="' + obj.Degrees[i].DegId + '"><span>' + obj.Degrees[i].DegreeName + '</span></td>' +
                        '<td class="btnDegreeRemove" style="text-align: center;cursor: pointer"><i style="color: red" class="fa fa-times"></i></td></tr>';
                }
                $('#subjectDegreeBody').html(degrees);
            },
            error: function () {

            }
        }
    );
}

$(document).on('click', '.btnDegreeRemove', function () {
    $(this).parent().remove();
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
                $('#subjectDegreeBody').html('')
                var count = 0;
                var subjects = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Subjects.length; i++) {
                    subjects +=
                        '<tr style="font-size: 17px">' +
                        '<td style="padding-right: 5px;text-align: right;font-weight: bold">' + ++count + '</td>' +
                        '<td style="text-align: center;font-weight: bold">' + obj.Subjects[i].SubjectId + '</td>' +
                        '<td style="padding-left: 5px">' + obj.Subjects[i].SubjectName + '</td>' +
                        '<td style="text-align: center">' + obj.Subjects[i].Credits + '</td>'+
                        '<td class="btnViewSubject" style="text-align: center;cursor: pointer"><i style="font-size: 20px" class="fa fa-plus-circle"></i></td></tr>'
                }
                $('#subjectsBody').html(subjects);
            },
            error: function () {

            }
        }
    );
}

//-----------------------------------------------Add Delete Update------------------------------------------------------

function collectDegreeProgrammes() {
    var degreeProgrammes = Array();
    for (var i = 0; i < $('#degreeBody').children().length; i++) {
        degreeProgrammes.push($('#degreeBody').children().eq(i).children().eq(0).children('input').val())
    }
    return degreeProgrammes;
}

$('#btnAdd').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/add_subject",
            data: {
                degreeIds: JSON.stringify(collectDegreeProgrammes()),
                semesterId: $('#semester').val(),
                subjectId: $('#subjectId').val(),
                subjectTitle: $('#subjectTitle').val(),
                credits: $('#credits').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                    setTextFieldsEmpty();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Student has been submitted successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to add student</div>')
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
                degreeIds: JSON.stringify(collectDegreeProgrammes()),
                semesterId: $('#semester').val(),
                subjectId: $('#subjectId').val(),
                subjectTitle: $('#subjectTitle').val(),
                credits: $('#credits').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadSubjects();
                    setTextFieldsEmpty();
                    setFieldsToNewDegree();
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
                    setTextFieldsEmpty();
                    setFieldsToNewDegree();
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

//-------------------------------------------------Select subject on table----------------------------------------------

$(document).on('click', '.btnViewDegree', function () {
    $('#degreeId').val($(this).parent().children().eq(1).children('input').val())
    $('#degreeTitle').val($(this).parent().children().eq(1).children('span').html())
    setFieldsToExistingDegree();
    loadDegreesViaSubject($(this).parent().children().eq(2).html())
})

//---------------------------------------------New vs existing----------------------------------------------------------

function setFieldsToExistingDegree() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
}

function setFieldsToNewDegree() {
    $('#btnAdd').prop("disabled", false);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewDegree();
})

function setTextFieldsEmpty() {
    $('#degreeTitle').val('')
    setFieldsToNewDegree();
}