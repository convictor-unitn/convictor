<%-- 
    Document   : reportPhoto.html
    Created on : Jul 24, 2016, 5:10:56 PM
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
						La richiesta di rimozione foto è stata inviata con successo.
					</div>
				</div>
			<a href="${pageContext.servletContext.contextPath}/userProfile/show" class="ui blue submit button">Indietro</a>
		</div>
	</jsp:attribute>
</l:main>
