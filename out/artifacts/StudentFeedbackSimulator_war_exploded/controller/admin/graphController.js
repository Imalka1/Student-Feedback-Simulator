$(window).on("load", function () {
    loadMarks();
});

var xaxisText = Array();
for (var i = 0; i < 22; i++) {
    xaxisText.push('Criteria ' + (i + 1));
}

var marks = Array();
var criterias = Array();

function loadMarks() {
    $.ajax(
        {
            type: "post",
            url: window.location.origin + "/load_marks",
            data: {
                subjectId: $('#subjectId').val(),
                lecturerId: $('#lecturerId').val(),
                dateOfSubmission: $('#dateOfSubmission').val()
            },
            success: function (response) {
                var obj = JSON.parse(response);

                for (var j = 0; j < obj.length; j++) {
                    for (var i = 0; i < obj[j].Marks.length; i++) {
                        criterias.push(obj[j].Marks[i].EvaluationCriteria);
                        marks.push(parseFloat((parseInt(obj[j].Marks[i].Marks) / (parseInt(obj[j].Marks[i].StudentsCount) * 5.00)) * 100).toFixed(2));
                    }
                }
                setCriterias();
            },
            error: function () {

            }
        }
    );
}

function setCriterias() {
    var setData =
        '<div class="col-12" style="text-align: center;border-bottom: 1px solid black">X - Axis</div>';
    for (var i = 0; i < criterias.length; i++) {
        setData +=
            '<div class="col-2" style="padding-left: 5px;border-right: 1px solid black;border-bottom: 1px solid black">Criteria ' + (i + 1) + '</div>' +
            '<div class="col-10" style="padding-left: 5px;border-bottom: 1px solid black">' + criterias[i] + '</div>';
    }
    $('#creiterias').html(setData);
}

var xaxisData = Array();
for (var i = 0; i < 22; i++) {
    xaxisData.push(i + 1);
}

var options = {
    chart: {
        type: 'bar',
        width: '97%',
        height: 1200
    },
    grid: {
        xaxis: {
            lines: {
                show: true,
            }
        },
        yaxis: {
            lines: {
                show: true,
            }
        },
    },
    plotOptions: {
        bar: {
            horizontal: true
        }
    },
    // series: [{
    //     name: 'sales',
    //     data: xaxisData
    // }],
    series: [
        {
            name: 'sales1',
            data: marks
        }
        // {
        //     name: 'sales2',
        //     data: xaxisData
        // }
    ],
    xaxis: {
        categories: xaxisText,

    },
    yaxis: {
        // forceNiceScale: false,
        // floating:false,
        tickAmount: 10,
        min: 0,
        max: 100,
        labels: {
            // show: true,
            rotate: -10
            // trim: true,
            // minWidth: 50,
            // style: {
            // colors: [],
            // fontSize: '12px',
            // fontFamily: 'Helvetica, Arial, sans-serif'
            // },
        }
    },
    tooltip: {
        y: {
            show: false,
            title: {
                formatter: function (val, opts) {
                    return "Value"
                }
            }
        },
    }
}

var chart = new ApexCharts(document.querySelector("#chart"), options);

chart.render();