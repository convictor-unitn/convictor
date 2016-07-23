<%-- 
    Document   : 500
    Created on : May 15, 2016, 4:20:33 PM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>

<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:error>
    
    <jsp:attribute name="errorCode">500</jsp:attribute>
    <jsp:attribute name="message"> Si e' verificato un errore interno al sistema.</jsp:attribute>
    
</l:error>
