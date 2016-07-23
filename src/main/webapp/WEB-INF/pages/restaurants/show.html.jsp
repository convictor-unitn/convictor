  <%-- 
    Document   : restaurant_profile.html
    Created on : 14-giu-2016, 15.29.02
    Author     : Giovanni Maria Riva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- These JSTL tag are used to set correctly the pagination URL request --%>
<c:set var="nextPagination" scope="request" value="${requestScope.nextPagination}" />
<c:set var="requestURL" scope="request" value="${requestScope['javax.servlet.forward.query_string']}" />
<c:if test="${empty param.reviewPage}">
  <c:set var="requestURLFilters" scope="request" value="${requestURL}" />
  <c:set var="actualPage" scope="request" value="0" />
</c:if>
<c:if test="${!empty param.reviewPage}">
  <c:set var="requestURLFilters" scope="request" value="${fn:substringBefore(requestURL, '&reviewPage')}" />  
  <c:set var="actualPage" scope="request" value="${param.reviewPage}" />
</c:if>

<jsp:useBean id="restaurant" scope="request" class="it.unitn.disi.webprog2016.convictor.app.beans.Restaurant" />
<c:set var="bean" value="${requestScope.restaurant}" scope="request" />

<l:main>
    <jsp:attribute name="title"> ${restaurant.name} </jsp:attribute>
    <jsp:attribute name="bodyBackground"></jsp:attribute>
	<jsp:attribute name="body">
    <div class="ui container">

      <!-- Restaurant's Name -->
      <div class="ui center aligned grid">
        <div class="column">
          <div class="ui header large">
            ${restaurant.name}
          </div>
        </div>
      </div>
      <!-- Restaurant Image -->
      <div class="ui stackable two column centered grid">
        <div class="column">
			<c:forEach var="photo" items="${bean.photos}">
				<img class="ui centered image mySlides" src="${photo.url}">
			</c:forEach>
            
            <div class="w3-center w3-section w3-large w3-text-white w3-display-bottomleft" style="width:100%;" id="trasp">
              <div class="w3-left w3-padding-left w3-hover-text-blue w3-text-blue" onclick="plusDivs(-1)" style="background-color:rgba(255,255,255,0.8);">&#10094;</div>
              <div class="w3-right w3-padding-right w3-hover-text-blue w3-text-blue" onclick="plusDivs(1)" style="background-color:rgba(255,255,255,0.8);">&#10095;</div>
			  <c:set var="photoCounter" value="1" scope="page" />
			  <c:forEach var="photo" items="${bean.photos}">
				<span class="w3-badge demo w3-border w3-blue w3-hover-blue" onclick="currentDiv(${photoCounter})"></span>
				<c:set var="photoCounter" value="${photoCounter+1}" />
			  </c:forEach>
            </div>
        </div>
      </div>

      <!-- Rating Infos & Reviews Infos & QR Code & Personal Infos -->
      <div class="ui center aligned middle aligned five column stackable grid">
        <div class="stretched column">
          <div class="row">
            <div class="ui small statistic">
              <div class="value" id="brown">
                5
              </div>
              <div class="label" id="brown">
                Posizione in Lombardia
              </div>
            </div>
          </div>
          <div class="ui statistic">
              <div class="ui horizontal list">
                <c:forEach var="i" begin="0" end="${restaurant.rating}" step="1">
                    <c:if test="${i!=0}">
                        <div class="item">
                            <i class="star icon"> </i>
                        </div>
                    </c:if>
                </c:forEach>
                    <c:forEach begin="${restaurant.rating}" end="4" step="1">
                        <div class="item">
                            <i class="empty star icon"> </i>
                        </div>
                </c:forEach>
              </div>
              <div class="label" id="brown">
                Valutazione media
              </div>
          </div>
        </div>
        <div class="stretched column">
			<img class="ui small centered image" src="${pageContext.servletContext.contextPath}/restaurants/qrcode?id=${restaurant.id}" />
        </div>
        <div class="stretched column">
          <div class="ui list">
            <div class="item">
              <div class="meta">
                <span>${restaurant.street}</span>
                <span>${restaurant.zipCode}</span>
                <span>${restaurant.city}</span>
              </div>
            </div>
            <div class="item">
              <div class="meta">${restaurant.email}</div>
            </div>
            <div class="item">
              <div class="meta">${restaurant.phone}</div>
            </div>
            <div class="item">
              <div class="meta"><a href="#">${restaurant.website}</a></div>
            </div>
          </div>
        </div>
      </div>
			
			
			</br>
			<div class="segment" style="text-align:center">
		
					${restaurant.description}
					</div>
			</br>
			</br>


      <!-- Restaurant Infos -->
      <div class="ui center aligned four column stackable grid">
        <div class="row">
          <div class="column">
            <div clas="ui grid">
              <div class="column">
                <div class="ui sub header">Cucina</div>
              </div>
              <div class="ui divider"></div>
              <div class="column">
                <div class="ui list">
                    <c:forEach var="cusine" items="${restaurant.cusine}">
                        <div class="item">
                            ${cusine.name}
                        </div>
                    </c:forEach>
                </div>
              </div>
            </div>
          </div>
           
			<div class="column">
				<div clas="ui center aligned grid">
				  <div class="column">
					<div class="ui sub header">Fascia di prezzo</div>
				  </div>
				  <div class="ui divider"></div>
				  <div class="column">
					<div class="ui list">
					  ${restaurant.slotPrice}
					</div>
				  </div>
				</div>
			  </div>	
		</div>	  
			  
				
		  <div class="eight wide column">
            <div clas="ui grid">
				<div class="row">
					<div class="column">
						<div class="ui sub header">Orari di apertura</div>
					</div>
				</div>
				<div class="row">
					<div class="ui divider"></div>
				</div>
				<div class="row">
					<div class="column">
						<table id="myTable" class="ui center aligned table">
							<thead>
								<tr> 
									<th></th>
									<th>
									  Mattina
									</th>
									<th>
									  Pomeriggio
									</th>											
								</tr>  
							</thead>
							<tbody>								
									<c:forEach var="openingTime" items="${restaurant.openingTimes}">
									<tr>
										<td>
										  ${openingTime.dayString}
										</td>
										<c:if test="${openingTime.dayoff != true}">
									  <td>	 
										  ${openingTime.openAt} - 
										  ${openingTime.closeAt} 
									  </td>

									  </c:if>
									  <c:if test="${openingTime.dayoff == true}">
										  <td>CHIUSO</td>
									  </c:if>
										<c:if test="${openingTime.dayoff != true}">
										  <td>
										  ${openingTime.openAtAfternoon} - 
										  ${openingTime.closeAtAfternoon}  
										  </td>
									  </c:if>
									  <c:if test="${openingTime.dayoff == true}">
										  <td >CHIUSO</td>
									  </c:if>  
									</tr>
										
									</c:forEach>
									
								</tr>
								  <c:forEach var="openingTime" items="${restaurant.openingTimes}">
									  
								  </c:forEach>
							  <tr>								
								  <c:forEach var="openingTime" items="${restaurant.openingTimes}">
									  
								  </c:forEach>
							  </tr>				
							</tbody>  
						</table>
						

						
						
					</div>
				</div>              			  			  			  
            </div>

          </div>

      </div>

      <!-- Show Reviews/Map Buttons -->
      <div class="ui stackable center aligned three columns grid ">

        <div class="row">
          <div class="column">
            <div class="ui four item tabular menu">
              <a class="item active" data-tab="recensioni" id="brown">Recensioni</a>
              <a class="item " data-tab="mappa" id="brown">Mappa</a>
              <a class="item" data-tab="reclama" id="brown">Reclama</a>
              <a class="item" data-tab="addimage" id="brown">Carica Immagine</a>
            </div>
          </div>
        </div>
      </div>

    <!-- Show Reviews/Map TABS -->

    <div id ="tabs"class="ui stackable grid">
      <div class="column">
        <!-- Recensioni Tab -->
        <div class="ui tab active" data-tab="recensioni">
          <div class="ui grid">
            <div class="row">
              <div class="column">
                  <div class="ui buttons">
                    <div class="ui basic black button">
                        <c:if test="${actualPage-1 < 0}">
                            <a href="?${requestURLFilters}&reviewPage=0"> 
                                <i class="left arrow icon"></i>
                            </a>
                        </c:if>
                        <c:if test="${actualPage-1 >= 0}">
                            <a href="?${requestURLFilters}&reviewPage=${actualPage-1}"> 
                                <i class="left arrow icon"></i>
                            </a>
                        </c:if>
                    </div>
                  </div>
                  <div class="ui basic black button">                      
                        <a href="?${requestURLFilters}&reviewPage=${requestScope.nextPagination}"> 
                            <i class="right arrow icon"></i>
                        </a>                      
                  </div>
                      <div class="ui basic label">
                        2,048
                  </div>
              </div>  
            </div>   
            <div class="row">
              <div class="column">
                  <div class="ui comments">
                <!-- Reviews List -->
                  <c:forEach var="review" items="${restaurant.reviews}">
                      <div class="comment">
                      <div class="content">
                          <a class="author" id="brown"">${review.registeredUserName}</a>
                          <div class="metadata">
                              <div class="date" id="brown"">2 days ago</div>
                              <div class="rating">
                                  <div class="ui horizontal list">
                                      <c:forEach var="i" begin="0" end="${review.rating}" step="1">
                                      <c:if test="${i!=0}">
                                         <div class="item">
                                              <i class="star icon"> </i>
                                          </div>
                                      </c:if>
                                  </c:forEach>
                                  <c:forEach begin="${review.rating}" end="4" step="1">
                                      <div class="item">
                                          <i class="empty star icon"> </i>
                                      </div>
                                  </c:forEach>
                                  </div>                                    
                              </div>
                          </div>
                      <div class="text" id="brown">
                        ${review.description}
                      </div>
                      <div class="actions">
                        <a class="reply">Rispondi</a>
                      </div>                            
                      <form class="ui reply form">
                        <div class="field">
                          <textarea></textarea>
                        </div>
                        <div class="ui basic black submit labeled icon button">
                          <i class="icon edit"></i> Rispondi
                        </div>
                      </form>
                    </div>
                      </div>                      
                  </c:forEach>
                  </div>
                  
              </div>
  
                <!-- End Reviews List -->
            </div>
              <!-- Add a review textbox -->
              <%--<p:formerrors /> --%>
              <div class="sixteen wide column">
                <div class="ui center aligned grid">
                  <div class="column">
                    <div class="ui segment">
                    <c:choose>    
                      <c:when test="${!empty sessionScope.user}">
                        <div class="ui header">Inserisci una recensione</div>
                        <div id="rating-selector" class="ui large center rating" data-rating="1" data-max-rating="5"></div>
                        <div class="ui center comment">
                          <form class="ui small reply form" method="POST" action="${pageContext.servletContext.contextPath}/restaurants/addReview">
                            <input type="hidden" name="idRestaurant" value="${restaurant.id}"/>
                            <input type="hidden" id="ratingFormHidden" name="rating" value="" />
                            <div class="field">
                              <textarea name="reviewText"></textarea>
                            </div>
                            <!--<div class="ui basic black submit button">-->
                              <input class="ui black button" type="submit" onclick="setInputValue()" value="Inserisci una recensione" class="icon edit">
                            <!--</div>-->
                          </form>
                        </div>
                      </c:when>
                      <c:otherwise>
                        <div class="ui header">
                          <a href="${pageContext.servletContext.contextPath}/sign_in">Accedi</a>
                          per recensire questo ristorante!
                        </div>
                      </c:otherwise>
                    </c:choose>
                    </div>
                  </div>
                </div>
              </div>
          </div>
         </div>
              
          <!-- End Recensioni Tab -->
          <!-- Mappa Tab -->
          <div class="ui tab" data-tab="mappa">
            <div class="ui center aligned grid">
              <div id="map" class="column">
                Mappa
              </div>
            </div>
          </div>
          <!-- End Mappa Tab -->
          <!-- Reclama Tab -->
          <div class="ui tab" data-tab="reclama">
            <div class="ui center aligned grid">
              <div class="column">
                <div class="ui center aligned grid">
                    <div class="column">
						Questo ristorante è tuo? Clicca qui sotto, compila il form e ti contatteremo. </br></br>
                        <button class="ui basic black button ownership">Reclama Ristorante</button>
                        <div class="ui modal ownership_modal">
                            <div class="header center">
                                 Richiesta Reclamo
                            </div>
                            <div class="content">
                            <form class="ui form">
                                <div class="field">
                                  <input type="text" name="nomeazienda" placeholder="Nome Azienda">
                                 </div>
                                 <div class="field">
                                   <input type="text" name="piva" placeholder="Partita Iva">
                                  </div>
                                  <div class="field">
                                    <input type="text" name="cf" placeholder="Codice Fiscale">
                                   </div>
                                <div class="field actions">
                                    <div class="ui cancel basic black button">Close</div>
                                    <input class="ui basic black button" type="submit"/>
                                </div>
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
              </div>
            </div>
          </div>
          <!-- End Reclama Tab -->
          <!-- Add Image Tab -->
          <div class="ui tab" data-tab="addimage">
              <div class="ui center aligned grid">
                  <div class="column">
					  Scatta una foto e condividila con tutti! </br></br>
                      <button class="ui basic black button add_image">Aggiungi Immagine</button>
                      <div class="ui modal add_image_modal">
                          <div class="header center">
                               Aggiungi Nuova Immagine
                          </div>
                          <div class="content">
							  <form class="ui form" action="${pageContext.servletContext.contextPath}/restaurants/uploadPhoto" method="POST" enctype="multipart/form-data" action="">
								  <input type="hidden" value="${bean.id}" name="id" />
								  <div class="field">
									  <input id="fileupload" type="file" name="files" />
								  </div>
								  <div class="field actions">
									  <div class="ui cancel basic black button">Close</div>
									  <input class="ui black button" type="submit" value="Upload"/>
								  </div>
							  </form>
                          </div>
								  
                      </div>
                  </div>
              </div>
          </div>
          <!-- End Add Image Tab -->
        </div>
      </div>
    </div>
    <!-- End Reviews/Map TABS -->

        <script type="text/javascript">
            // Image slider control functions

            var slideIndex = 1;
            showDivs(slideIndex);

            function plusDivs(n) {
              showDivs(slideIndex += n);
            }

            function currentDiv(n) {
              showDivs(slideIndex = n);
            }

            function showDivs(n) {
              var i;
              var x = document.getElementsByClassName("mySlides");
              var dots = document.getElementsByClassName("demo");
              if (n > x.length) {slideIndex = 1;}
              if (n < 1) {slideIndex = x.length;}
              for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
              }
              for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" w3-white", "");
              }
              x[slideIndex-1].style.display = "block";
              dots[slideIndex-1].className += " w3-white";              
            }   
        </script>
        <script type="text/javascript">
            function initMap() {
                var myLatLng = {lat: -25.363, lng: 131.044};

                var map = new google.maps.Map(document.getElementById('map'), {
                  zoom: 4,
                  center: myLatLng
                });

                var marker = new google.maps.Marker({
                  position: myLatLng,
                  map: map,
                  title: 'Hello World!'
                });

            }

        </script>
        <script type="text/javascript">
          // Use to load the rating inside the form
          function setInputValue() {
            var val = document.getElementById("rating-selector").getElementsByClassName("icon active").length;
            document.getElementById("ratingFormHidden").setAttribute('value', val);
          }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbiud33G2KsodO5JvP-5HQzoSTuWiI0a8&callback=initMap" type="text/javascript"></script>

	</jsp:attribute>
                
                
		
</l:main>

