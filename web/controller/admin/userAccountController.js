//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadStudents();
    validateSubmission();
});

$('#regNo').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

$('#nationalId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

//--------------------------------------------------Change semester for all students------------------------------------

$('#btnChangeSem').click(function () {
    var optionVal = parseInt($('#semester').val()) + 1;
    $("#semester").val(optionVal).attr("selected", "selected");

    $.ajax(
        {
            type: "post",
            url: window.location.origin + $('#contextPath').val() + "/change_semester",
            data: {
                semesterId: $('#semester').val(),
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

$('#degree').change(function () {
    loadStudents();
})

$('#batch').change(function () {
    loadStudents();
})

$('#semester').change(function () {
    loadStudents();
})

//-----------------------------------------------Add Delete Update------------------------------------------------------

$('#btnAdd').click(function () {
    sendRequestToServer("/add_student", "Student has been submitted successfully", "Failed to add student");
})

$('#btnUpdate').click(function () {
    sendRequestToServer("/update_student", "Student has been updated successfully", "Failed to update student");
})

$('#btnDelete').click(function () {
    sendRequestToServer("/delete_student", "Student has been deleted successfully", "Failed to delete student");
})

function sendRequestToServer(urlExtension, successResponse, failResponse) {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + $('#contextPath').val() + urlExtension,
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
                    setFieldsToNewStudent();
                    $('#response').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">' + successResponse + '</div>')
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">' + failResponse + '</div>')
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
}

//-------------------------------------------------Students Table-------------------------------------------------------

function loadStudents() {
    var tableData = '';
    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + $('#contextPath').val() + "/load_students",
            data: {
                degreeId: $('#degree').val(),
                batchId: $('#batch').val(),
                semesterId: $('#semester').val()
            },
            success: function (response) {
                var students = JSON.parse(response).Students;
                for (var i = 0; i < students.length; i++) {
                    tableData += '' +
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

var newStudent = true;

function setFieldsToNewStudent() {
    newStudent = true;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#regNo').prop("disabled", false);
    $('#regNo').val('')
    $('#studetName').val('')
    $('#nationalId').val('')
    $('#emailAddress').val('')
}

function setFieldsToExistingStudent() {
    newStudent = false;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#regNo').prop("disabled", true);
}

//---------------------------------------------Validate input fields----------------------------------------------------

$('#regNo').keyup(function () {
    $(this).val($(this).val().toUpperCase())
    validateSubmission();
})

$('#studetName').keyup(function () {
    validateSubmission();
})

$('#nationalId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
    if (isNationalId($(this).val())) {
        $(this).css('border-color', '');
    } else {
        $(this).css('border-color', 'red');
    }
    validateSubmission();
})

$('#emailAddress').keyup(function () {
    if (isEmail($(this).val())) {
        $(this).css('border-color', '');
    } else {
        $(this).css('border-color', 'red');
    }
    validateSubmission();
});

function validateSubmission() {
    if (newStudent && $('#regNo').val() !== '' && $('#studetName').val() !== '' && $('#nationalId').val() !== '' && $('#emailAddress').val() !== '' && isEmail($('#emailAddress').val()) && isNationalId($('#nationalId').val())) {
        $('#btnAdd').prop("disabled", false);
        $('#btnUpdate').prop("disabled", true);
    } else if (!newStudent && $('#regNo').val() !== '' && $('#studetName').val() !== '' && $('#nationalId').val() !== '' && $('#emailAddress').val() !== '' && isEmail($('#emailAddress').val()) && isNationalId($('#nationalId').val())) {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", false);
    } else {
        $('#btnAdd').prop("disabled", true);
        $('#btnUpdate').prop("disabled", true);
    }
}

function isNationalId(nationalId) {
    var regex = /^([0-9]{9}V{1})*([0-9]{11}V{1})*$/;
    return regex.test(nationalId);
}

function isEmail(email) {
    var regex = /^([a-z])([a-z0-9])*([._-]([a-z0-9])+)*@+([a-z])*([._-]([a-z0-9])+)*([.]([a-z])+)+$/;
    return regex.test(email);
}

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setFieldsToNewStudent();
    validateSubmission();
})