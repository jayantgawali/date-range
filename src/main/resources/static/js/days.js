$(document).ready(function () {

    $("#date-form").submit(function (event) {

        event.preventDefault();

        callCalculate();

    });

});

function callCalculate() {

    var dateForm = {}
    dateForm["startDate"] = $("#startDate").val();
    dateForm["endDate"] = $("#endDate").val();

    $("#calculate-btn").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/calculate",
        data: JSON.stringify(dateForm),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {


            $('#result').find('.modal-title').text(data.message);
            console.log("SUCCESS : ", data);
            console.log("SUCCESS : ", data.message);
            $("#calculate-btn").prop("disabled", false);
            $('#result').modal('show');
        },
        error: function (data) {

             $('#result').find('.modal-title').text(data.responseJSON.message);
            console.log("ERROR : ", data);
            console.log("ERROR : ", data.responseJSON.message);
            $("#calculate-btn").prop("disabled", false);
            $('#result').modal('show');
        }
    });

}