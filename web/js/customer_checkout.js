/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#btnCheckCode').click(function () {
        var valCode = $('#txtCode').val();
        if (valCode !== '') {
            $.ajax({
                url: "checkCoupon",
                type: "POST",
                data: {
                    txtCode: valCode
                },
                success: function (result) {
                    var msg = JSON.parse(result);

                    if (msg.value) {
                        // Upate the discount value
                        $('#couponValue i').remove();
                        $('#couponValue').append("<i><i class='fa fa-dollar'></i> -" + msg.value + "</i>");
                        $('#txtCode').attr('readonly', true);
                        $('#txtCode').removeClass('input-material');
                        
                        // Re-caculating total
                        var total = parseFloat($('#txtTotal p').text());
                        var discount = parseFloat(msg.value);
                        var total = total - discount;
                        if(total < 0) {
                            total = 0;
                        }
                        
                        if(total <= 0) {
                            $('#btnPaypal').hide();
                        }
                        $('#txtTotal p').text(total);
                        
                        // Set hidden filed the code
                        $('#hiddenCode').val(valCode);
                        
                    } else if (msg.codeError) {
                        $('#couponValue i').remove();
                        $('#couponValue').append("<i>" + msg.codeError + "</i>");
                    }
                }
            });
        } else {
            $('#couponValue i').remove();
            $('#couponValue').append("<i>Input code</i>");
        }

    });
    
    
    $('#btnCheckout').click(function (){
        $('#formCheckout').attr('action', 'checkout');
        $('#formCheckout').submit();
    });
    
    $('#btnPaypal').click(function (){
        $('#formCheckout').attr('action', 'createPayment');
        $('#formCheckout').submit();
    });
});


