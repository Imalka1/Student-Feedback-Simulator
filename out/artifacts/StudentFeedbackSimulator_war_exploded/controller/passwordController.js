$('#emailAddress').keyup(function () {
    if (isEmail($(this).val())) {
        $(this).css('border-color', '');
        $('#btnSendEmail').prop("disabled", false);
    } else {
        $(this).css('border-color', 'red');
        $('#btnSendEmail').prop("disabled", true);
    }
});

$('#btnSendEmail').click(function () {
    $.ajax(
        {
            async: false,
            type: "post",
            url: "http://" + window.location.hostname + ":8080/email_confirmation_code",
            data: {
                email: $('#emailAddress').val()
            },
            success: function (response) {
                console.log(response);
            },
            error: function (err) {
                console.log(err)
            }
        }
    );
});

function isEmail(email) {
    var regex = /^([a-z])([a-z0-9])*([._-]([a-z0-9])+)*@+([a-z])*([._-]([a-z0-9])+)*([.]([a-z])+)+$/;
    return regex.test(email);
}

