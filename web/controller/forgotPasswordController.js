//---------------------------------------------------Enter ID-----------------------------------------------------------

$('#userId').keyup(function () {
    $(this).val($(this).val().toUpperCase())
    if ($(this).val() !== '') {
        $('#btnSendEmail').prop("disabled", false);
    } else {
        $('#btnSendEmail').prop("disabled", true);
    }
});

//-------------------------------------------------Send email-----------------------------------------------------------

var confirmationCode;

$('#btnSendEmail').click(function () {
    $.ajax(
        {
            // async: false,
            type: "post",
            url: "http://" + window.location.hostname + ":8080/email_confirmation_code",
            data: {
                userId: $('#userId').val()
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

//---------------------------------------------Check confirmation code--------------------------------------------------

$('#confirmationCode').keyup(function () {
    if (parseInt($(this).val()) === confirmationCode) {
        $('#nPassword').prop("disabled", false);
        $('#cPassword').prop("disabled", false);
    } else {
        $('#nPassword').prop("disabled", true);
        $('#cPassword').prop("disabled", true);
    }
});

//------------------------------------------Check password confirmation-------------------------------------------------

$('#nPassword').keyup(function () {
    checkPasswordConfirmation();
});

$('#cPassword').keyup(function () {
    checkPasswordConfirmation();
});

function checkPasswordConfirmation() {
    if ($('#nPassword').val() !== '' && $('#cPassword').val() !== '' && $('#nPassword').val() === $('#cPassword').val()) {
        $('#btnResetPassword').prop("disabled", false);
    } else {
        $('#btnResetPassword').prop("disabled", true);
    }
}

//--------------------------------------------Reset password------------------------------------------------------------

// $('#btnResetPassword').click(function () {
//     $.ajax(
//         {
//             type: "post",
//             url: "http://" + window.location.hostname + ":8080/forgot_password",
//             data: {
//                 userId: $('#userId').val(),
//                 password: $('#nPassword').val()
//             },
//             success: function (response) {
//                 if (JSON.parse(response) == true) {
//                     $('#btnSubmit').prop("disabled", true);
//                     $('#messageBox').html('<div class="alert alert-success" style="text-align: center;font-weight: bold">Password has been reset successfully</div>')
//                 } else {
//                     $('#messageBox').html('<div class="alert alert-danger" style="text-align: center;font-weight: bold">Failed to reset Password</div>')
//                 }
//                 setTimeout(function () {
//                     $('#messageBox').html('');
//                 }, 4000);
//                 window.scrollTo(0, 0);
//             },
//             error: function (err) {
//
//             }
//         }
//     );
// })
//
