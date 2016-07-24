<%-- 
    Document   : 500
    Created on : May 15, 2016, 4:20:33 PM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>


<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:error>
  
	<jsp:attribute name="title">404 | Convictor</jsp:attribute>
    <jsp:attribute name="errorCode">404</jsp:attribute>
    <jsp:attribute name="message">Non e' stato possibile recuperare la risorsa.</jsp:attribute>

    
</l:error>
