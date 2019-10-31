//----------------------------------------------Select marks------------------------------------------------------------

$('.tdMark').click(function () {
    if ($(this).css("background-color") === "rgb(48, 118, 29)") {
        $(this).css('background-color', "rgba(0, 0, 0, 0)");
        $(this).css('color', 'black');
    } else {
        $(this).parent().children('td.tdMark').css("background-color", "rgba(0, 0, 0, 0)");
        $(this).parent().children('td.tdMark').css("color", 'black');
        $(this).css('background-color', "rgb(48, 118, 29)");
        $(this).css('color', 'white');
    }
});

//--------------------------------------------Submit marks--------------------------------------------------------------

$('#btnSubmit').click(function () {
    var marks = Array();
    var ecids = Array();
    for (var i = 0; i < $('td.tdMark').length; i++) {
        if ($('td.tdMark').eq(i).css("background-color") === "rgb(48, 118, 29)") {
            ecids.push($('td.tdMark').eq(i).parent().children().eq(1).children('input').val());
            marks.push($('td.tdMark').eq(i).text());
        }
    }

    if (marks.length === $('.trMarks').length) {
        $.ajax(
            {
                type: "post",
                url: "http://" + window.location.hostname + ":8080/processMarks",
                data: {
                    uid: $('#uId').val(),
                    subjectId: $('#subjectId').val(),
                    lecturerId: $('#lecturerId').val(),
                    marks: JSON.stringify(marks),
                    ecids: JSON.stringify(ecids)
                },
                success: function (response) {
                    if (JSON.parse(response) == true) {
                        document.location.href = "success_page.jsp";
                    } else {
                        $('#messageBox').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to submit marks</div>')
                        setTimeout(function () {
                            $('#messageBox').html('');
                        }, 4000);
                        window.scrollTo(0, 0);
                    }
                },
                error: function () {

                }
            }
        );
    } else {
        $('#messageBox').html('<div class="alert alert-warning" style="text-align: center;font-weight: bold">You have ' + ($('.trMarks').length - marks.length) + ' fields remaining. Please select all fields to submit</div>');
        // setTimeout(function () {
        //     $('#messageBox').html('');
        // }, 4000);
        window.scrollTo(0, 0);
    }
})