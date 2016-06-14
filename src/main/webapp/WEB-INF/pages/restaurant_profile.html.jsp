<%-- 
    Document   : restaurant_profile.html
    Created on : 14-giu-2016, 15.29.02
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">{Restaurant's Name} Profile Page</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container stackable internally celled grid">
                <!-- Restaurant Image -->
                <div class="five wide column">
                  <div class="ui segment">
                    immagine
                  </div>

                  <!-- Show Reviews/Map Buttons -->
                  <div class="ui center aligned equal width grid">
                    <div class="ui large buttons">
                      <button class="ui button">Recensioni</button>
                      <div class="or"></div>
                      <button class="ui button active">Mappa</button>
                    </div>
                  </div>
                </div>


                <div class="eleven wide column">
                    <div class="ui segment">

                        <!-- Restaurant Name -->
                        <div class="row">
                          <div class="ui two centered column row ">
                            <div class="ui header">Ristorante 1</div>
                          </div>
                        </div>

                        <!-- Rating Infos & Reviews Infos -->
                        <div class="row">
                            <div class="ui vertical segment">
                                <div class="ui star rating" data-rating="5">Rating</div>
                                <div class="ui mini horizontal statistics">
                                    <div class="statistic">
                                        <div class="value">
                                            2,204
                                        </div>
                                        <div class="label">
                                            total reviews
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Restaurant Infos -->
                            <div class="ui vertical segment">
                                <div class="ui equal width grid">
                                  <div class="column">Cucina</div>
                                  <div class="column">Orari di apertura</div>
                                </div>
                                <div class="ui equal width grid">
                                  <div class="column">Fascia di prezzo</div>
                                  <div class="column">Giorno di chiusura</div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

	</jsp:attribute>
                
                
		
</l:main>

