$(document).ready(function () {
    // Show modal inform customer cart is updated
    if($('#toggleModal').val() === 'showModal'){
        $('#toggleModal').val('hideModal');
        $('#modalUpdateCart').modal('show');
    }
});

function plus(id) {
    var strId = "#" + id.toString();
    var quantity = $(strId).val();

    quantity = parseInt(quantity) + 1;
    
    $(strId).val(quantity);
    
    var subTotal = parseFloat($('#bookPrice'+id).text()) * quantity;
    $('#bookSubTotal'+id).text(subTotal.toFixed(1));
};



function minus(id) {
    var strId = "#" + id.toString();
    var quantity = $(strId).val();

    quantity = parseInt(quantity) - 1;

    if (quantity < 1) {
        quantity = 1;
    }

    $(strId).val(quantity);
    
    var subTotal = parseFloat($('#bookPrice'+id).text()) * quantity;
    $('#bookSubTotal'+id).text(subTotal.toFixed(1));
}