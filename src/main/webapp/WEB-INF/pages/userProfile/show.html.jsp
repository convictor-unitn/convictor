<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>

<l:main>
	
	<jsp:attribute name="title">Accedi</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">

                <div class="text"> <h2> Il mio profilo </h2> </div>

                </br>

                <div class="text"> Nome: </div> 
                <div class="ui segment"> Nome </div>
                <div class="text"> Cognome: </div> 
                <div class="ui segment"> Cognome </div>
                <div class="text"> Email: </div> 
                <div class="ui segment"> Email </div>
                </br>
                <button class="fluid basic black ui button">Modifica le informazioni del profilo</button>
            </div>
            <div class="ui divider"></div>
            <partials:admin_notices />
            <partials:restaurant_notices />

	</jsp:attribute>        
		
</l:main>

