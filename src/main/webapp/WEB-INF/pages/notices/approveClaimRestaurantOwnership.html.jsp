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
		<div class="ui blue icon message">
			<i class="check circle icon"></i>
			<div class="content">
				<div class="header">
					DA IMPLEMENTARE
				</div>
			</div>
			<a href="#" onclick="window.location.href = document.referrer" class="ui blue submit button">Indietro</a>
		</div>
	</jsp:attribute>
</l:main>
