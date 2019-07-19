$('#uid').keyup(function () {
    $(this).val($(this).val().toUpperCase())
    if ($(this).val() !== '') {
        $('#btnSendEmail').prop("disabled", false);
    } else {
        $('#btnSendEmail').prop("disabled", true);
    }
});

var confirmationCode;

$('#btnSendEmail').click(function () {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: "http://" + window.location.hostname + ":8080/email_confirmation_code",
            data: {
                uid: $('#uid').val()
            },
            success: function (response) {
                confirmationCode = JSON.parse(response);
            },
            error: function (err) {
                console.log(err)
            }
        }
    );
});

$('#confirmationCode').keyup(function () {
    if (parseInt($(this).val()) === confirmationCode) {
        $('#nPassword').prop("disabled", false);
        $('#cPassword').prop("disabled", false);
    } else {
        $('#nPassword').prop("disabled", true);
        $('#cPassword').prop("disabled", true);
    }
});

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
                password: $('#nPassword').val()
            },
            success: function (response) {
                confirmationCode = JSON.parse(response);
            },
            error: function (err) {

            }
        }
    );
})

