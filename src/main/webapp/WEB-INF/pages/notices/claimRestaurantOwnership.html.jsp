<%-- 
    Document   : claimRestaurantOwnership.html
    Created on : Jul 24, 2016, 11:22:52 AM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>

<c:set var="bean" value="${requestScope.ownershipNotice}" />

<l:main>
	<jsp:attribute name="title">Reclamo ristorante</jsp:attribute>
		
	<jsp:attribute name="body">
		<c:choose>
			<c:when test="${ bean.valid eq true }">
				<div class="ui blue icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
						La richiesta è stata inviata. Sarai contattato dall'amministratore.
						</div>
					</div>
					<a href="#" onclick="window.location.href = document.referrer" class="ui blue submit button">Indietro</a>
				</div>
			</c:when>
			
			<c:when test="${ bean.valid eq false }">
				<partials:claimRestaurantForm bean="${bean}" />
			</c:when>
		</c:choose>
	</jsp:attribute>
</l:main>
