/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadHistoryRequest();
});

function loadHistoryRequest() {
    return $.ajax({
        url: "loadHistory",
        type: "POST",
        success: function (result) {
            loadAllHistory(result);
        }
    });
}

$('#formSearch').submit(function (e) {
    e.preventDefault();

    var form = $('#formSearch');
    $.ajax({
        url: form.attr('action'),
        type: form.attr('method'),
        data: form.serialize(),
        success: function (result) {
            loadAllHistory(result);
        }
    });
});

function loadAllHistory(result) {
    var list = JSON.parse(result);
    $('#tblHistory tr').remove();

    var rowContent;
    $.each(list, function (i, order) {
        rowContent = "<tr>";
        rowContent += "<td>" + (i + 1) + "</td>";
        rowContent += "<td>" + order.orderId + "</td>";
        rowContent += "<td>";
        $.each(order.listBook, function (x, book) {
            rowContent += "<i>" + book.title + " - x" + book.quantity + "</i></br>";
        });
        rowContent += "</td>";
        rowContent += "<td>$ " + order.total + "</td>";
        rowContent += "<td>" + order.createdDate + "</td>";
        rowContent += "</tr>";

        $('#tblHistory').append(rowContent);
    });
}
