<%-- 
    Document   : claimRestaurantOwnership.html
    Created on : Jul 24, 2016, 11:22:52 AM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials/" %>

<c:set var="bean" value="${requestScope.ownershipNotice}" />

<l:main>
	<jsp:attribute name="title">Reclamo ristorante</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="ui one centered column grid">
			<div class="row">
				<div class="thirteen wide column">
					<div class="ui centered card">
						  <div class="content">
							  <div class="ui large header center aligned">${bean.restaurant.name}</div>
							  <div class="ui center aligned list">
								  <div class="item">
									  <div class="ui sub header">Nome</div>
									<div class="description">
									  ${bean.registeredUser.name}
									</div>
								  </div>
								  <div class="item">
									  <div class="ui sub header">Cognome</div>
									<div class="description">
									  ${bean.registeredUser.surname}
									</div>
								  </div>
								  <div class="item">
									  <div class="ui sub header">Nome Azienda</div>
									<div class="description">
									  ${bean.companyName}
									</div>
								  </div>
								  <div class="item">
									  <div class="ui sub header">P Iva</div>
									<div class="description">
									  ${bean.taxCode}
									</div>
								  </div>
								  <div class="item">
									  <div class="ui sub header">Contatto</div>
									<div class="content">
									  ${bean.contactPhone}
									</div>
								  </div>
								</div>
						  </div>
						  <div class="ui two bottom attached buttons">
							<a href="${pageContext.servletContext.contextPath}/restaurants/approveClaim?noticeId=${bean.id}&noticeApproved=true" class="ui button black">Approva</a>
							<a href="${pageContext.servletContext.contextPath}/restaurants/approveClaim?noticeId=${bean.id}&noticeApproved=false" class="ui button black">Rifiuta</a>
						  </div>
					</div>
				</div>
			</div>					  
		</div>					  				
		
	</jsp:attribute>
</l:main>
