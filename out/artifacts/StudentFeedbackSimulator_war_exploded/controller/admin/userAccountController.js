//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadDegrees();
    loadStudents();
});

$('#regNo').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

//--------------------------------------------------Change semester for all students------------------------------------

$('#btnChangeSem').click(function () {
    var optionVal = parseInt($('#semester').val());
    $('#semester option[value=' + ++optionVal + ']').attr('selected', 'selected');

    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + "/change_semester",
            data: {
                semesterId: $('#semester').val(),
                facultyId: $('#faculty').val(),
                batchId: $('#batch').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadStudents();
                }
            },
            error: function () {

            }
        }
    );
})

//----------------------------------------------------Filter------------------------------------------------------------

$('#faculty').change(function () {
    loadDegrees();
    loadStudents();
})

$('#degree').change(function () {
    loadStudents();
})

$('#batch').change(function () {
    loadStudents();
})

$('#semester').change(function () {
    loadStudents();
})

//------------------------------------------------Load degrees----------------------------------------------------------

function loadDegrees() {
    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + "/load_degrees",
            data: {
                facultyId: $('#faculty').val()
            },
            success: function (response) {
                // console.log(response)
                var degrees = '';
                var obj = JSON.parse(response);
                for (var i = 0; i < obj.Degrees.length; i++) {
                    degrees += '<option value="' + obj.Degrees[i].DegId + '">' + obj.Degrees[i].DegreeName + '</option>';
                }
                $('#degree').html(degrees);
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
            url: window.location.origin + "/add_student",
            data: {
                degreeId: $('#degree').val(),
                batchId: $('#batch').val(),
                semesterId: $('#semester').val(),
                regNo: $('#regNo').val(),
                studetName: $('#studetName').val(),
                nationalId: $('#nationalId').val(),
                emailAddress: $('#emailAddress').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadStudents();
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
            url: window.location.origin + "/update_student",
            data: {
                degreeId: $('#degree').val(),
                batchId: $('#batch').val(),
                semesterId: $('#semester').val(),
                regNo: $('#regNo').val(),
                studetName: $('#studetName').val(),
                nationalId: $('#nationalId').val(),
                emailAddress: $('#emailAddress').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadStudents();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Student has been updated successfully</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to update student</div>')
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
            url: window.location.origin + "/delete_student",
            data: {
                regNo: $('#regNo').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    loadStudents();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Student has been deleted successfully</div>');
                    setTextFieldsEmpty();
                    setFieldsToNewStudent();
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete student</div>')
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

//--------------------------------------------------Search--------------------------------------------------------------

$('#btnSearchStudent').click(function () {
    for (var i = 0; i < students.length; i++) {
        if ($('#regNo').val() === students[i].RegId) {
            $('#studetName').val(students[i].StudentName)
            $('#emailAddress').val(students[i].EmailAddress)
            $('#nationalId').val(students[i].NationalId)
            setFieldsToExistingStudent();
        }
    }
})

//-------------------------------------------------Students Table-------------------------------------------------------
var students = Array();

function loadStudents() {
    var tableData='';
    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + "/load_students",
            data: {
                degreeId: $('#degree').val(),
                batchId: $('#batch').val(),
                semesterId: $('#semester').val()
            },
            success: function (response) {
                students = JSON.parse(response).Students;
                // console.log(obj.Students)
                for (var i = 0; i < students.length; i++) {
                    tableData = '' +
                        '<tr>' +
                        '<td style="text-align: center">' + students[i].RegId + '</td>' +
                        '<td style="padding-left: 5px">' + students[i].StudentName + '</td>' +
                        '<td style="text-align: center">' + students[i].NationalId + '</td>' +
                        '<td style="padding-left: 5px">' + students[i].EmailAddress + '</td>' +
                        '<td class="btnViewStudent" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i></td>' +
                        '</tr>';
                }
            },
            error: function () {

            }
        }
    );
    $('#studentsDataBody').html(tableData);
}

//-------------------------------------------------Select student on table----------------------------------------------

$(document).on('click', '.btnViewStudent', function () {
    $('#regNo').val($(this).parent().children().eq(0).html())
    $('#studetName').val($(this).parent().children().eq(1).html())
    $('#nationalId').val($(this).parent().children().eq(2).html())
    $('#emailAddress').val($(this).parent().children().eq(3).html())
    setFieldsToExistingStudent();
})

//---------------------------------------------New vs existing----------------------------------------------------------

function setFieldsToExistingStudent() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#regNo').prop("disabled", true);
}

function setFieldsToNewStudent() {
    $('#btnAdd').prop("disabled", false);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#regNo').prop("disabled", false);
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewStudent();
})

function setTextFieldsEmpty() {
    $('#regNo').val('')
    $('#studetName').val('')
    $('#nationalId').val('')
    $('#emailAddress').val('')
    setFieldsToNewStudent();
}