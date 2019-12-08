$(document).ready(function () {
    // Load image to browser when admin choose a photo's path
    var inputFile = $("#fileInput").get(0);
    var imgPreview = $("#imgPreview").get(0);

    inputFile.addEventListener('change', function () {
        var reader = new FileReader();
        reader.onload = function (event) {
            imgPreview.src = event.target.result;
        }
        reader.readAsDataURL(inputFile.files[0]);
    });
});

// Handle when submit button is clicked
$('#submitButton').click(function(){
    var val = $('#categorySelect').val().toString();
    if(val === "All"){
        $('#categorySelect')[0].setCustomValidity("You must choose a category!");
    }else{
        $('#categorySelect')[0].setCustomValidity("");
    }
});
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


