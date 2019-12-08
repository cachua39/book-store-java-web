/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadAllBookRequest();
});

// Ajax for loading all books from server
function loadAllBookRequest() {
    return $.ajax({
        url: "loadAllBookByAdmin",
        type: "POST",
        success: function (results) {
            loadAllBook(results);
        }
    });
}

// Function for drawing all books to table
function loadAllBook(result) {
    // Parse the result to js object
    var list = JSON.parse(result);

    // Remove all table row before drawing new data
    $('#tblBookByAdmin tr').remove();

    // Drawing new data to book table
    var rowContent;
    $.each(list, function (i, book) {
        rowContent = "<tr>";
        rowContent += "<td class='align-middle'>" + (++i) + "</td>";
        rowContent += "<td><img class='rounded tbl-img' src='" + book.photo + "'></td>";
        rowContent += "<td class='align-middle'>" + book.title + "</td>";
        rowContent += "<td class='align-middle'>" + book.author + "</td>";
        rowContent += "<td class='align-middle'>" + book.categoryName + "</td>";
        rowContent += "<td class='align-middle'>" + book.price + "</td>";
        rowContent += "<td class='align-middle'>" + book.quantity + "</td>";
        rowContent += "<td class='align-middle'><a href='editBookByAdmin?bookId=" + book.bookId + "' class='text-primary'><i class='fa fa-lg fa-cog'></i></a></td>";
        rowContent += "<td class='align-middle'><button type='button' onclick=\"deleteBook('" + book.bookId + "')\" class='btn btn-link text-primary'><i class='fa fa-lg fa-times'></i></button></td>";

        rowContent += "</tr>";

        $('#tblBookByAdmin').append(rowContent);
    });
}

// Function for deleting a book
function deleteBook(bookId) {
    // Adding the click event to modal 
    $('.btnYes').click(function () {
        $('#modalRemoveBook').modal('hide');
        $.ajax({
            url: "deleteBookByAdmin",
            type: "POST",
            data: {
                bookId: bookId
            },
            success: function (result) {
                loadAllBookRequest();
            }
        });
    });

    // Show modal for warning customer about book deleting.
    $('#modalRemoveBook').modal('show');
}


