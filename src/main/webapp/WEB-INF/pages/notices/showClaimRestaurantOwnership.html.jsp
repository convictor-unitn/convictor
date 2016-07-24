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
	<jsp:attribute name="bodyBackground">#eaeaea</jsp:attribute>	
	<jsp:attribute name="body">
			<dl>
				<dt>Ristorante</dt>
				<dd>${bean.restaurant.name}</dd>
				<dt>Utente richiedente</dt>
				<dd>${bean.registeredUser.name} ${bean.registeredUser.surname}</dd>
				<dt>Nome Azienda</dt>
				<dd>${bean.companyName}</dd>
				<dt>Codice fiscale</dt>
				<dd>${bean.taxCode}</dd>
				<dt>Contatto</dt>
				<dd>${bean.contactPhone}</dd>
			</dl>
			<a href="${pageContext.servletContext.contextPath}/restaurants/approveClaim?noticeId=${bean.id}&noticeApproved=true" class="ui button black">Approva</a>
			<a href="${pageContext.servletContext.contextPath}/restaurants/approveClaim?noticeId=${bean.id}&noticeApproved=false" class="ui button black">Rifiuta</a>
		<c:choose>
			<c:when test="${ bean.valid eq true && bean.approved == true }">
				<div class="ui blue icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
							Il ristorante è stato assegnato
						</div>
					</div>
					<a href="#" onclick="window.location.href = document.referrer" class="ui blue submit button">Indietro</a>
				</div>
			</c:when>
			
			<c:when test="${ bean.valid eq false }">
				<div class="ui red icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
							${bean.errors['restaurant']}
						</div>
					</div>
					<a href="#" onclick="window.location.href = document.referrer" class="ui blue submit button">Indietro</a>
				</div>
			</c:when>
		</c:choose>
	</jsp:attribute>
</l:main>
