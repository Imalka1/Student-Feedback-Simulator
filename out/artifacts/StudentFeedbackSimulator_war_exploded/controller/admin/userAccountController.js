$('#degree').change(function () {
    loadStudents();
})

$('#year').change(function () {
    loadStudents();
})

$('#batch').change(function () {
    loadStudents();
})

$('#regNo').keyup(function () {
    $(this).val($(this).val().toUpperCase())
})

$('#btnClear').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewStudent();
})

$(document).on('click', '.btnViewStudent', function () {
    $('#regNo').val($(this).parent().children().eq(0).html())
    $('#studetName').val($(this).parent().children().eq(1).html())
    $('#nationalId').val($(this).parent().children().eq(2).html())
    $('#emailAddress').val($(this).parent().children().eq(3).html())
    setFieldsToExistingStudent();
})

$('#regNo').keyup(function () {
    validateSubmission();
})

$('#studetName').keyup(function () {
    validateSubmission();
})

$('#nationalId').keyup(function () {
    validateSubmission();
})

$('#emailAddress').keyup(function () {
    validateSubmission();
})

$('#btnAdd').click(function () {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/add_student",
            data: {
                degree: $('#degree').val(),
                year: $('#year').val(),
                batch: $('#batch').val(),
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
                degree: $('#degree').val(),
                year: $('#year').val(),
                batch: $('#batch').val(),
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
                } else {
                    $('#response').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to delete student</div>')
                }
                setTimeout(function () {
                    $('#response').html('');
                }, 4000);
            },
            error: function () {

            }
        }
    );
})

//----------------------------------------------Students Table (Start)--------------------------------------------------

var pageNo = 0;
var pagesCount = 0;
var studentsArray;

$(window).on("load", function () {
    loadStudents();
    $('#pageNo').html((pageNo + 1) + ' / ' + ((studentsArray.length - 1) / 10 + 1));
});

function loadStudents() {
    $.ajax(
        {
            async: false,
            type: "post",
            url: window.location.origin + "/load_students",
            data: {
                degree: $('#degree').val(),
                year: $('#year').val(),
                batch: $('#batch').val()
            },
            success: function (response) {
                var obj = JSON.parse(response);
                // console.log(obj.Students)
                studentsArray = Array();
                for (var i = 0; i < obj.Students.length; i++) {
                    studentsArray.push(obj.Students[i]);
                }
                pagesCount = (studentsArray.length - 1) / 10 + 1;
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
    }
    $('#studentsDataBody').html(tableData);
}

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

//-----------------------------------------------Students Table (End)---------------------------------------------------

function setTextFieldsEmpty() {
    $('#regNo').val('')
    $('#studetName').val('')
    $('#nationalId').val('')
    $('#emailAddress').val('')
}

function setFieldsToNewStudent() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", true);
    $('#btnDelete').prop("disabled", true);
    $('#regNo').prop("disabled", false);
}

function setFieldsToExistingStudent() {
    $('#btnAdd').prop("disabled", true);
    $('#btnUpdate').prop("disabled", false);
    $('#btnDelete').prop("disabled", false);
    $('#regNo').prop("disabled", true);
}

function validateSubmission() {
    if ($('#regNo').val() !== '' && $('#studetName').val() !== '' && $('#nationalId').val() !== '' && $('#emailAddress').val() !== '') {
        $('#btnAdd').prop("disabled", false);
    } else {
        $('#btnAdd').prop("disabled", true);
    }
}