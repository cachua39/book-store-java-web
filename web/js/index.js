/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    // Call all ajax requests in asysnchronous way.
    $.when(loadAllCategoryRequest, loadAllBookRequest);
});

// Ajax for loading all books data from server
var loadAllBookRequest = $.ajax({
    url: "loadAllBookByUser",
    type: "POST",
    success: function (result) {
        loadAllBook(result);
    }
});

// Ajax for loading all categories data from server
var loadAllCategoryRequest = $.ajax({
    url: "loadAllCategory",
    type: "POST",
    success: function (result) {
        loadAllCategory(result);
    }
});

// Function for drawing all books to table in html
function loadAllBook(result) {
    // Parse the result to js object
    var list = JSON.parse(result);

    // Clear all row before load new data
    $('#tblBookByUser tr').remove();

    // Load new data fetched from ajax
    var rowContent;
    var tbl = $('#tblBookByUser');
    $.each(list, function (i, book) {
        rowContent = "<tr>";
        rowContent += "<td class='align-middle'> " + (++i) + " </td>";
        rowContent += "<td><img class='rounded tbl-img' src='" + book.photo + "'></td>";
        rowContent += "<td class='align-middle'> " + book.title + " </td>";
        rowContent += "<td class='align-middle'> " + book.author + " </td>";
        rowContent += "<td class='align-middle'> " + book.categoryName + " </td>";
        rowContent += "<td class='align-middle'> " + book.price + " </td>";
        rowContent += "<td class='align-middle'><button type='button' onclick='addBookToCart(\"" + book.bookId + "\")' class='btn btn-link btn-material'><i class='text-secondary fa fa-lg fa-cart-plus'></i></button></td>";
        rowContent += "</tr>";

        tbl.append(rowContent);
    });
}

// Function for drawing the categories to <seclect> tag in html
function loadAllCategory(result) {
    // Parse the result to js object
    var list = JSON.parse(result);
    // Clear all <select>'s <option> before write new data
    $('#categorySelect option').remove();

    // Load new data fetched from server
    var categorySelect = $('#categorySelect');
    categorySelect.append("<option>All</option>");
    $.each(list, function (i, category) {
        categorySelect.append("<option>" + category.name + "</option>");
    });
}

// Function when user adding book to cart
// Use ajax to send request to server
function addBookToCart(bookId) {
    $.ajax({
        url: "addToCart",
        type: "POST",
        data: {
            bookId: bookId
        },
        success: function (result) {
            $('#titleHeader h5').remove();
            $('#msgAddSuccess p').remove();
            if (result === "redirect") {
                window.location.replace("login.jsp");
            } else if (result === "failed") {
                $('#titleHeader').append("<h5 class='modal-title text-danger'>Failed</h5>");
                $('#msgAddSuccess').append("<p>Add to cart failed :(</p>");
                $('#addToCartModal').modal('show');
            } else {
                $('#titleHeader').append("<h5 class='modal-title text-success'>Success</h5>");
                $('#msgAddSuccess').append("<p>Add Success :))</p>");
                $('#msgAddSuccess').append("<p>" + result + "</p>");
                $('#addToCartModal').modal('show');
            }

        }
    });
}


$('#formSearch').submit(function(e){
    e.preventDefault();
    
    var form = $('#formSearch');
    $.ajax({
        url: form.attr('action'),
        type: form.attr('method'),
        data: form.serialize(),
        success: function (result) {
            loadAllBook(result);
        }
    });
});

