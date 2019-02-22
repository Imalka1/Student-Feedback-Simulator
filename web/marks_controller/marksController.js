var that;
$('.tdMark').click(function () {
    if ($(this).css("background-color") == "rgb(38, 104, 22)") {
        $(this).css('background-color', "rgba(0, 0, 0, 0)");
        $(this).css('color', 'black');
    } else {
        $(this).parent().children('td.tdMark').css("background-color", "rgba(0, 0, 0, 0)");
        $(this).parent().children('td.tdMark').css("color", 'black');
        $(this).css('background-color', "rgb(38,104,22)");
        $(this).css('color', 'white');
    }
});