$('.tdMark').click(function () {
    console.log($(this).parent())
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
    var marks = "";
    var ecids = "";
    for (var i = 0; i < $('td.tdMark').length; i++) {
        if ($('td.tdMark').eq(i).css("background-color") == "rgb(48, 118, 29)") {
            ecids += $('td.tdMark').eq(i).parent().children().eq(1).children('input').val() + ","
            marks += $('td.tdMark').eq(i).text() + ","
        }
    }
    marks = marks.slice(0, -1);
    ecids = ecids.slice(0, -1);
    $.ajax(
        {
            type: "post",
            url: "http://" + window.location.hostname + ":8080/processMarks",
            data: {
                marks: marks,
                ecids: ecids
            },
            success: function (response) {

            },
            error: function () {

            }
        }
    );
})

$('#backBtn').click(function () {
    if(window.location.pathname=='/view/mark_sheet.jsp'){
        document.location.href = "subjects.jsp";
    }
})