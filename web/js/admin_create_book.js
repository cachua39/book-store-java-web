$(document).ready(function () {
    // Load all categories 
    $.ajax({
        url: "loadAllCategory",
        type: "POST",
        success: function (result) {
            loadAllCategory(result);
        }
    });
    
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


function loadAllCategory(result) {
    // Convert json to js object
    var list = JSON.parse(result);
    
    // Remove all <select>'s <option> before add new data
    $('#categorySelect option').remove();
    
    // Loading all data to <select>
    var categorySelect = $('#categorySelect');
    categorySelect.append("<option value='All'>Choose a category</option>");
    $.each(list, function (i, category) {
        categorySelect.append("<option>"+ category.name +"</option>");
    });
}

// Handle when submit button is clicked
$('#submitButton').click(function(){
    var val = $('#categorySelect').val().toString();
    if(val === "All"){
        $('#categorySelect')[0].setCustomValidity("You must choose a category!");
    }else{
        $('#categorySelect')[0].setCustomValidity("");
    }
});
