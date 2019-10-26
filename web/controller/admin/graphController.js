var xaxisText = Array();
for (var i = 0; i < 22; i++) {
    xaxisText.push('Criteria ' + (i + 1));
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
    series: [{
        name: 'sales',
        data: xaxisData
    }],
    xaxis: {
        categories: xaxisText,

    },
    yaxis: {
        tickAmount: 1,
        min: 0,
        max: 5,
        labels: {
            show: true,
            rotate: -10,
            trim: true,
            minWidth: 50,
            style: {
                // colors: [],
                fontSize: '12px',
                // fontFamily: 'Helvetica, Arial, sans-serif'
            },
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

