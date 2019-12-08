/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $.ajax({
        url: "loadAllRole",
        type: "POST",
        success: function(result) {
            var list = JSON.parse(result)
            loadRole(list);
        }
    });
});

function loadRole(list) {
    var selectRole = $("#selectRole").get(0);
    
    /**
     * Check and remove all options before add new option when load the page
     */
    if(selectRole.length > 0) {
        for(var i = 0; i < selectRole.length; i++) {
            selectRole.remove(i);
        }
    }
    
    $.each(list, function(i, role){
        var option = document.createElement("option");
        option.text = role.name;
        selectRole.add(option, i);
    });
}
