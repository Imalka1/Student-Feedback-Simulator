//---------------------------------------------------Initial Load-------------------------------------------------------
$(window).on("load", function () {
    loadDegrees();
    loadStudents();
});

//--------------------------------------------------Change semester for all students------------------------------------
$('#btnChangeSem').click(function () {
    var optionVal = parseInt($('#semester').val());
    $('#semester option[value=' + ++optionVal + ']').attr('selected','selected');

    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + "/change_semester",
            data: {
                semesterId: $('#semester').val()
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
                console.log(response)
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

//-------------------------------------------------Students Table-------------------------------------------------------

var pageNo = 0;
var pagesCount = 0;
var studentsArray;

function loadStudents() {
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
                var obj = JSON.parse(response);
                // console.log(obj.Students)
                studentsArray = Array();
                for (var i = 0; i < obj.Students.length; i++) {
                    studentsArray.push(obj.Students[i]);
                }
                pagesCount = Math.ceil((studentsArray.length) / 10);
                $('#pageNo').html((pageNo + 1) + ' / ' + pagesCount);
                fillTheTable();
            },
            error: function () {

            }
        }
    );
}

$('#incPageNo').click(function () {
    if (pageNo + 1 < pagesCount) {
        $('#pageNo').html((++pageNo + 1) + ' / ' + pagesCount);
        fillTheTable();
    }
})

$('#decPageNo').click(function () {
    if (pageNo > 0) {
        $('#pageNo').html((--pageNo + 1) + ' / ' + pagesCount);
        fillTheTable();
    }
})

function fillTheTable() {
    var count = 0;
    var tableData = '';
    for (var i = pageNo * 10; i < studentsArray.length; i++) {
        tableData +=
            '<tr>' +
            '<td style="text-align: center">' + studentsArray[i].RegId + '</td>' +
            '<td style="padding-left: 5px">' + studentsArray[i].StudentName + '</td>' +
            '<td style="text-align: center">' + studentsArray[i].NationalId + '</td>' +
            '<td style="padding-left: 5px">' + studentsArray[i].EmailAddress + '</td>' +
            '<td class="btnViewStudent" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i></td>' +
            '</tr>';

        count++;
        if (count == 10) {
            break;
        }
    }
    $('#studentsDataBody').html(tableData);
}

//--------------------------------------------------Search--------------------------------------------------------------

$('#btnSearchStudent').click(function () {
    for (var i = 0; i < studentsArray.length; i++) {
        if ($('#regNo').val() === studentsArray[i].RegId) {
            $('#studetName').val(studentsArray[i].StudentName)
            $('#emailAddress').val(studentsArray[i].EmailAddress)
            $('#nationalId').val(studentsArray[i].NationalId)
            setFieldsToExistingStudent();
        }
    }
})

//--------------------------------------------------Clear fields--------------------------------------------------------

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewStudent();
})

//-------------------------------------------------Select student on table----------------------------------------------

$(document).on('click', '.btnViewStudent', function () {
    $('#regNo').val($(this).parent().children().eq(0).html())
    $('#studetName').val($(this).parent().children().eq(1).html())
    $('#nationalId').val($(this).parent().children().eq(2).html())
    $('#emailAddress').val($(this).parent().children().eq(3).html())
    setFieldsToExistingStudent();
})

//----------------------------------------------New vs existing---------------------------------------------------------

var newStudent = true;

function setFieldsToNewStudent() {
    newStudent = true;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#regNo').prop("disabled", false);
}

function setFieldsToExistingStudent() {
    newStudent = false;
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#regNo').prop("disabled", true);
}

function setTextFieldsEmpty() {
    $('#regNo').val('')
    $('#studetName').val('')
    $('#nationalId').val('')
    $('#emailAddress').val('')
}