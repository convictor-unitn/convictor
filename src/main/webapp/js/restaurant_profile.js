/* 
 * This file contains simple javascript code to implement some dinamic effects
 * in Restaurant's Profile Page
 */

$(document).ready(function(){
  //Star rating initializer
  $('.ui.rating').rating();

  //Tabular menu initializer
  $('.tabular.menu .item').tab();

  //Hides all reply forms in reviews
  $('.comments .reply.form').hide();

  $('a[data-tab=mappa]').on("click", function() {
	  initMap();
	 google.maps.event.trigger(map, 'resize'); 
  });

  //Manages to show/hide reviews
  $('.actions .reply').click(function(){
    $(this).parent().next().toggle();
  });

  $('.comments .reply.form .button').click(function(){
    $(this).parent().toggle();
  });

  //Add new image modal initializer
 $(".add_image").click(function(){
    $('.ui.modal.add_image_modal').modal('show');
  });

  //Ownership form modal initializer

  $(".ownership").click(function(){
    $('.ui.modal.ownership_modal').modal('show');
  });
  
  //New/Edit restaurant day opening checkboxes initializer
  
  $('.ui.checkbox.mycheckbox').checkbox({
          
        onChecked: 
            function(){
                $(this).parent().parent().parent().toggleClass('disabled'); 
            },
        
        onUnchecked: 
            function(){
                $(this).parent().parent().parent().toggleClass('disabled'); 
            }
    });
	
	$('#myTable').stacktable();
  
});
