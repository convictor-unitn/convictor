/* 
 * This file contains simple javascript code to implement some dinamic effects
 * in Restaurant's Profile Page
 */

//Enables five star rating function
$(document).ready(function(){
  $('.ui.rating').rating();
});

//Allows to switch tabs between reviews list and map tabs
$(document).ready(function(){
    $('.tabular.menu .item').tab();
});
