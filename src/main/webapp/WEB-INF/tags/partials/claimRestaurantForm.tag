<%-- 
    Document   : claimRestaurantForm
    Created on : Jul 24, 2016, 11:24:16 AM
    Author     : umberto
--%>

<%@tag import="it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="bean" type="OwnershipNotice" %>

	<form action="${pageContext.servletContext.contextPath}/restaurants/claim" class="ui form error" method="POST">
		
	  <div class="ui middle aligned center aligned grid">
        <div class="column" style="max-width: 1000px;">
		  <label><h2 id="brown">Informazioni Personali</h2></label>
          <div class="ui divider"></div>
		  
		  <input type="hidden" name="restaurantId" value="${bean.restaurantId}" />
		  
		  <input type="hidden" name="registeredUserId" value="${bean.registeredUserId}" />
		  
		  <div class="field <c:if test="${bean.valid == false and !(bean.errors['company_name'] == null)}" >error</c:if>">
            <input type="text" name="companyName" placeholder="Nome Azienda" value="${bean.companyName}" />
			<c:if test="${bean.valid == false and !(bean.errors['company_name'] == null)}" >
				<div class="ui error message" >
					${bean.errors['company_name']}
				</div>
			</c:if>			
		  </div>
			
		  <div class="field <c:if test="${bean.valid == false and !( bean.errors['vat_number'] == null)}" >error</c:if>">
			<input type="text" name="vatNumber" placeholder="Partita Iva" value="${bean.vatNumber}" />
			<c:if test="${bean.valid == false and !( bean.errors['vat_number'] == null)}" >
				<div class="ui error message" >
					${bean.errors['vat_number']}
				</div>
			</c:if>	
		  </div>
		  <div class="field <c:if test="${bean.valid == false and !( bean.errors['tax_code'] == null)}" >error</c:if>">
			<input type="text" name="taxCode" placeholder="Codice Fiscale" value="${bean.taxCode}" />
			<c:if test="${bean.valid == false and !(bean.errors['tax_code'] == null)}" >
				<div class="ui error message" >
					${bean.errors['tax_code']}
				</div>
			</c:if>	
		  </div>
		  <div class="field <c:if test="${bean.valid == false and !( bean.errors['contact_phone'] == null)}" >error</c:if>">
			<input type="text" name="contactPhone" placeholder="Contatto telefonico" value="${bean.contactPhone}" />
			<c:if test="${bean.valid == false and !(bean.errors['contact_phone'] == null)}" >
				<div class="ui error message" >
					${bean.errors['contact_phone']}
				</div>
			</c:if>	
		  </div>
		  <div class="field actions">
			<a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${bean.restaurantId}" class="ui cancel basic black right floated button">Chiudi</a>
			<input class="ui black right floated  button" type="submit" value="Reclama" />
		  </div>
		</div>
	  </div>
	</form>
