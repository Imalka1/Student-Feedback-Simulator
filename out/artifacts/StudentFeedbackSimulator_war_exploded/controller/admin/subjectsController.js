//---------------------------------------------------Initial Load-------------------------------------------------------

$(window).on("load", function () {
    loadSubjects();
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
                        subjects += '<td class="btnChangeAllow" style="text-align: center;cursor: pointer"><i class="fa fa-check" style="color: green"></i></td></tr>';
                    } else {
                        subjects += '<td class="btnChangeAllow" style="text-align: center;cursor: pointer"><i class="fa fa-times" style="color: red"></i></td></tr>';
                    }
                }
                $('#subjectsBody').html(subjects);
            },
            error: function () {

            }
        }
    );
}

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