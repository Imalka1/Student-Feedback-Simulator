$('#emailAddress').keyup(function () {
    console.log(isEmail($(this).val()))
    if (isEmail($(this).val())) {
        $(this).css('border-color', '');
    } else {
        $(this).css('border-color', 'red');
    }
})

function isEmail(email) {
    // var regex = /^([a-z0-9_.+-])+\@(([a-z0-9-])+\.)+([a-z0-9]{2,4})+$/;
    // var regex = /^[a-z]([a-z0-9]+\\.)*[a-z0-9]+@[a-z0-9]+([a-z0-9]+\\.)*([a-z0-9]+)+$/;
    var regex = /^([a-z])*([._-]([a-z0-9])+)*@+([a-z])*([._-]([a-z0-9])+)*([.]([a-z])+)+$/;
    return regex.test(email);
}

