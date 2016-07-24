/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#autocomplete").autocomplete({
        source: function (request, response) {
            $.ajax({
                contentType: "application/json",
                type: "GET",
                url: "http://localhost:8084/Exercise1/services/languages/" + request.term,
                data: {
                    term: request.term
                },
                success: function (data, textStatus, jqXHR) {
                    response(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    jqXHR;
                }
            });
        },
        minLength: 2,
        delay: 500
    });