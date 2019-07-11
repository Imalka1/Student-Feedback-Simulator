$('.tdMark').click(function () {
    // console.log($(this).parent())
    if ($(this).css("background-color") == "rgb(48, 118, 29)") {
        $(this).css('background-color', "rgba(0, 0, 0, 0)");
        $(this).css('color', 'black');
    } else {
        $(this).parent().children('td.tdMark').css("background-color", "rgba(0, 0, 0, 0)");
        $(this).parent().children('td.tdMark').css("color", 'black');
        $(this).css('background-color', "rgb(48, 118, 29)");
        $(this).css('color', 'white');
    }
});

$('#btnSubmit').click(function () {
    var marks = Array();
    var ecids = Array();
    for (var i = 0; i < $('td.tdMark').length; i++) {
        if ($('td.tdMark').eq(i).css("background-color") == "rgb(48, 118, 29)") {
            // ecids += $('td.tdMark').eq(i).parent().children().eq(1).children('input').val() + ","
            // marks += $('td.tdMark').eq(i).text() + ","
            ecids.push($('td.tdMark').eq(i).parent().children().eq(1).children('input').val());
            marks.push($('td.tdMark').eq(i).text());
        }
    }
    // console.log(JSON.stringify(marks))
    $.ajax(
        {
            type: "post",
            url: "http://" + window.location.hostname + ":8080/processMarks",
            data: {
                uid: $('#uid').val(),
                sublecid: $('#sublecid').val(),
                marks: JSON.stringify(marks),
                ecids: JSON.stringify(ecids)
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    $('#btnSubmit').prop("disabled", true);
                }
            },
            error: function () {

            }
        }
    );
})

$('#backBtn').click(function () {
    if (window.location.pathname == '/view/mark_sheet.jsp') {
        document.location.href = "subjects.jsp";
    }
})