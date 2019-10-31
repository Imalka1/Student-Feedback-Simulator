$('.subjects').click(function () {
    document.location.href = $('#contextPath').val() + "/view/student/mark_sheet.jsp?subjectId=" + $(this).children('div.timeline-panel').children('div.timeline-body').children('input').val()
})