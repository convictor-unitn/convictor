<%-- 
    Document   : 500
    Created on : May 15, 2016, 4:20:33 PM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" session="false" %>

<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:error>
    
    <jsp:attribute name="errorCode">401</jsp:attribute>
    <jsp:attribute name="message"> Non hai i permessi di accesso a questa risorsa.</jsp:attribute>
    
</l:error>
