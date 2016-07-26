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
		<c:choose>
			<c:when test="${ bean.valid eq false }">
				<div class="ui red icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
							${bean.errors['restaurant']}
						</div>
					</div>
					<a href="#" onclick="window.location.href = document.referrer" class="ui red submit button">Indietro</a>
				</div>
			</c:when>
			<c:when test="${bean.approved == true}">
				<div class="ui blue icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
							Il ristorante è stato assegnato.
						</div>
					</div>
					<a href="${pageContext.servletContext.contextPath}/userProfile/show" class="ui blue submit button">Indietro</a>
				</div>
			</c:when>
			<c:when test="${bean.approved == false}">
				<div class="ui red icon message">
					<i class="remove circle icon"></i>
					<div class="content">
						<div class="header">
							La richiesta di assegnazione è stata rifiutata!
						</div>
					</div>
					<a href="${pageContext.servletContext.contextPath}/userProfile/show" class="ui blue submit button">Indietro</a>
				</div>
			</c:when>
		</c:choose>
	</jsp:attribute>
</l:main>
