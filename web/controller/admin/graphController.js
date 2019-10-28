$(window).on("load", function () {
    setCriteriaType();
    loadMarks();
});

var criteriaType;

function setCriteriaType() {
    criteriaType = $('#graph').val()
}

// var xaxisText = Array();
// for (var i = 0; i < 22; i++) {
//     xaxisText.push('Criteria ' + (i + 1));
// }

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

                if (criteriaType === 'ec') {
                    $('#graphHeading').text('Evaluation Criteria');
                    for (var j = 0; j < obj.length; j++) {
                        for (var i = 0; i < obj[j].Marks.length; i++) {
                            criterias.push(obj[j].Marks[i].EvaluationCriteria);
                            marks.push(parseFloat(parseInt(obj[j].Marks[i].Marks) / parseInt(obj[j].Marks[i].StudentsCount)).toFixed(2));
                        }
                    }
                } else if(criteriaType === 'ech'){
                    $('#graphHeading').text('Evaluation Criteria Heading');
                    var headingAvgMarks;
                    for (var j = 0; j < obj.length; j++) {
                        headingAvgMarks = 0;
                        criterias.push(obj[j].EvaluationCriteriaHeading);
                        for (var i = 0; i < obj[j].Marks.length; i++) {
                            headingAvgMarks += parseFloat(parseInt(obj[j].Marks[i].Marks) / parseInt(obj[j].Marks[i].StudentsCount));
                        }
                        marks.push(parseFloat(headingAvgMarks / obj[j].Marks.length).toFixed(2));
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
    // $('#creiterias').html(setData);
}

var xaxisData = Array();
for (var i = 0; i < 22; i++) {
    xaxisData.push(i + 1);
}

var options = {
    chart: {
        type: 'bar',
        width: '97%',
        height: 800
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
            horizontal: false
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
        categories: criterias,
        labels: {
            show: true,
            rotate: -55,
            rotateAlways: true,
            // trim: true,
            minHeight: 200,
            maxHeight: 600,
            // style: {
            // colors: [],
            // fontSize: '12px',
            // fontFamily: 'Helvetica, Arial, sans-serif'
            // },
        }
    },
    yaxis: {
        forceNiceScale: true,
        // floating:false,
        tickAmount: 1,
        min: 0,
        max: 5
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