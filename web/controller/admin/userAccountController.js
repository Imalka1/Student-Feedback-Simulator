var page_no = 0;
$(window).on("load", function () {
    $('#pageNo').html(page_no + 1);
    loadStudents();
});

$('#incPageNo').click(function () {
    $('#pageNo').html(++page_no + 1);
    loadStudents();
})

$('#decPageNo').click(function () {
    if (page_no > 0) {
        $('#pageNo').html(--page_no + 1);
        loadStudents();
    }
})

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

$('#btnNewStudent').click(function () {
    setTextFieldsEmpty();
    setFieldsToNewStudent();
})

$(document).on('click', '.btnViewStudent', function () {
    $('#regNo').val($(this).parent().children().eq(0).html())
    $('#studetName').val($(this).parent().children().eq(1).html())
    $('#nationalId').val($(this).parent().children().eq(2).html())
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
                nationalId: $('#nationalId').val()
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
                nationalId: $('#nationalId').val()
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

function loadStudents() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_students",
            data: {
                degree: $('#degree').val(),
                year: $('#year').val(),
                batch: $('#batch').val(),
                page_no: page_no * 10
            },
            success: function (response) {
                var obj = JSON.parse(response);
                var tableData = '';
                console.log(obj)
                for (var i = 0; i < obj.Students.length; i++) {
                    tableData += '' +
                        '<tr>\n' +
                        '                    <td style="text-align: center">' + obj.Students[i].RegId +
                        '                    </td>\n' +
                        '                    <td style="padding-left: 5px">' + obj.Students[i].StudentName +
                        '                    </td>\n' +
                        '                    <td style="text-align: center">' + obj.Students[i].NationalId +
                        '                    </td>\n' +
                        '                    <td class="btnViewStudent" style="text-align: center;cursor: pointer"><i class="fa fa-search"></i>\n' +
                        '                    </td>\n' +
                        '                </tr>'
                }
                $('#studentsDataBody').html(tableData);
            },
            error: function () {

            }
        }
    );
}

function setTextFieldsEmpty() {
    $('#regNo').val('')
    $('#studetName').val('')
    $('#nationalId').val('')
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
    if ($('#regNo').val() !== '' && $('#studetName').val() !== '' && $('#nationalId').val() != '') {
        $('#btnAdd').prop("disabled", false);
    } else {
        $('#btnAdd').prop("disabled", true);
    }
}