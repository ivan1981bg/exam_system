$('#loadResults').click(() => {
    reloadResults()
});

function reloadResults() {
    $("#results-container").empty();

    fetch("http://localhost:8000/results/api").
    then(response => response.json()).
    then(json => json.forEach(result => {
        let tableRow = '<tr>' +
            '<td>' + result.fullName + '</td>' +
            '<td>' + result.examName + '</td>' +
            '<td>' + result.totalCorrect+ '</td>' +
            '<td>' + result.numberOfQuestions+ '</td>' +
            '</tr>'
        $("#results-container").append(tableRow)
    }))
}





