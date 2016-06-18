/* 
 * This file contains simple javascript code to implement some dinamic effects
 * in Restaurant's Profile Page
 */

$(document).ready(function(){
  //Enables five star rating function
  $('.ui.rating').rating();

  //Enables toggle funtion to switch between reviews, map and reclame
  $('.tabular.menu .item').tab();

  //Hide all reply forms for admin
  $('.comments .reply.form').hide();

  //Hide/Show clicked reply form for admin
  $('.actions .reply').click(function(){
    $(this).parent().next().toggle();
  });

  //Hide/Show clicked reply form for admin once reply submitted
  $('.comments .reply.form .button').click(function(){
    $(this).parent().toggle();
  });
});
