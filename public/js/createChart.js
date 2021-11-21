const CHART_COLORS = [
    'rgb(23, 109, 196)',
    'rgb(220, 53, 69)',
    'rgb(230, 190, 98)',
    'rgb(75, 192, 192)',
];

function addOpacity (rgb, opacity) {
    const [r, g, b,] = rgb.match(/[0-9]+/g)
    return `rgba(${Number(r)},${Number(g)},${Number(b)},${opacity})`;
}

function createChart (chartId, responseData, otherJobData, labels){
    let datasets = [];
    datasets[0] = {
        label: 'Your response',
        data: responseData,
        fill: true,
        backgroundColor: addOpacity(CHART_COLORS[0], 0.5),
        borderColor: CHART_COLORS[0]
    };
    for(let i = 0; i < otherJobData.length; i++) {
        datasets[i+1] = {
            label: otherJobData[i].title,
            data: otherJobData[i].data,
            fill: true,
            backgroundColor: addOpacity(CHART_COLORS[i+1], 0.5),
            borderColor: CHART_COLORS[i+1]
        }
    }
    let chartCanvas = document.getElementById(chartId);
    let myChart = new Chart(chartCanvas, {
        type: 'radar',
        data: {
            labels : labels,
            datasets : datasets
        },
        options: {
            elements: {
                line: {
                    borderWidth: 3
                }
            }
        }
    });
}
