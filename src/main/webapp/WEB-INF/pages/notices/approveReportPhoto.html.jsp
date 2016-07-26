<%-- 
    Document   : approveReportPhoto.html
    Created on : Jul 24, 2016, 5:13:13 PM
    Author     : uriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials/" %>

<c:set var="bean" value="${requestScope.notice}" />

<l:main>
	<jsp:attribute name="title">Segnala Fotografia</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="ui blue icon message">
			<i class="check circle icon"></i>
				<div class="content">
					<div class="header">
						<c:choose>
							<c:when test="${requestScope.status == false}">
								La richiesta di rimozione è stata rifiutata.
							</c:when>
							<c:otherwise>
								La foto è stata eliminata con successo.
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			<a href="${pageContext.servletContext.contextPath}/userProfile/show" class="ui blue submit button">Indietro</a>
		</div>
	</jsp:attribute>
</l:main>

