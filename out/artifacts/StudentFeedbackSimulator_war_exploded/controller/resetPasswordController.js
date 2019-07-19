$('#nPassword').keyup(function () {
    checkPasswordConfirmation();
});

$('#cPassword').keyup(function () {
    checkPasswordConfirmation();
});

function checkPasswordConfirmation() {
    if ($('#nPassword').val() === $('#cPassword').val()) {
        $('#btnResetPassword').prop("disabled", false);
    } else {
        $('#btnResetPassword').prop("disabled", true);
    }
}

$('#btnResetPassword').click(function () {
    $.ajax(
        {
            type: "post",
            url: "http://" + window.location.hostname + ":8080/forgot_password",
            data: {
                userId: $('#uid').val(),
                password: $('#nPassword').val()
            },
            success: function (response) {
                if (JSON.parse(response) == true) {
                    $('#btnSubmit').prop("disabled", true);
                    $('#messageBox').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Password has been reset successfully</div>')
                } else {
                    $('#messageBox').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to reset Password</div>')
                }
                setTimeout(function () {
                    $('#messageBox').html('');
                }, 4000);
                window.scrollTo(0, 0);
            },
            error: function (err) {

            }
        }
    );
})

