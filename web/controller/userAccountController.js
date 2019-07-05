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
                        '                    <td style="text-align: center"><i class="fa fa-search"></i>\n' +
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