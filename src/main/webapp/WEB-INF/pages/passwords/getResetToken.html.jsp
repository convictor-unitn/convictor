<%-- 
    Document   : getResetToken.html
    Created on : Jul 20, 2016, 2:44:51 PM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Reimposta Password</jsp:attribute>
	<jsp:attribute name="body">
		
		<c:choose>
			<c:when test="${requestScope.status eq 'success'}">
				<div class="ui blue icon message">
					<i class="check circle icon"></i>
					<div class="content">
						<div class="header">
							Ti è stata inviata una email con le istruzioni per il recupero della password.
						</div>
					</div>
					<a href="${pageContext.servletContext.contextPath}/" class="ui blue submit button">Vai alla home</a>
				</div>
			</c:when>
			
			<c:when test="${requestScope.status eq 'failure'}">
				<div class="ui negative icon message">
					<i class="Frown icon"></i>
					<div class="content">
						<div class="header">
							Questo indirizzo email non è stato trovato. 
						</div>
					</div>
					<a href="${pageContext.servletContext.contextPath}/passwords/request_new" class="ui red submit button">Indietro</a>
				</div>
			</c:when>
		</c:choose>
		
	</jsp:attribute>
		
</l:main>
