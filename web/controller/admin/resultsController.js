//---------------------------------------------------Initial Load-------------------------------------------------------
$(window).on("load", function () {
    loadDegrees();
    loadStudents();
});

//----------------------------------------------------Filter------------------------------------------------------------
$('#faculty').change(function () {
    loadDegrees();
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