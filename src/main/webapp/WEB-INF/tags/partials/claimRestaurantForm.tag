<%-- 
    Document   : claimRestaurantForm
    Created on : Jul 24, 2016, 11:24:16 AM
    Author     : umberto
--%>

<%@tag import="it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="ownershipNotice" type="OwnershipNotice" %>

	<form action="${pageContext.servletContext.contextPath}/restaurants/claim" class="ui form" method="POST">
		
	  <div class="ui middle aligned center aligned grid">
        <div class="column" style="max-width: 1000px;">
		  <label><h2 id="brown">Informazioni Personali</h2></label>
          <div class="ui divider"></div>
		  
		  <input type="hidden" name="restaurantId" value="${ownershipNotice.restaurantId}" />
		  
		  <input type="hidden" name="registeredUserId" value="${ownershipNotice.registeredUserId}" />
		  
		  <div class="field">
			<input type="text" name="companyName" placeholder="Nome Azienda" value="${ownershipNotice.companyName}" />
		  </div>
		  <div class="field">
			<input type="text" name="vatNumber" placeholder="Partita Iva" value="${ownershipNotice.vatNumber}" />
		  </div>
		  <div class="field">
			<input type="text" name="taxCode" placeholder="Codice Fiscale" value="${ownershipNotice.taxCode}" />
		  </div>
		  <div class="field">
			<input type="text" name="contactPhone" placeholder="Contatto telefonico" value="${ownershipNotice.contactPhone}" />
		  </div>
		  <div class="field actions">
			<div class="ui cancel basic black right floated button">Chiudi</div>
			<input class="ui black right floated  button" type="submit" value="Reclama" />
		  </div>
		</div>
	  </div>
	</form>
