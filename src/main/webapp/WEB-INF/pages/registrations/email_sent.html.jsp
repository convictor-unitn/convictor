<%-- 
    Document   : email_sent
    Created on : 19-lug-2016, 15.26.15
    Author     : federica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/partials" %>

<c:set var="bean" value="${requestScope.user}" scope="request" />

<l:main>
	
  </br>

  <h2> La registrazione a Convitor è quasi completa. </h2>
  
  </br>
  
  <h3> Ti abbiamo inviato un'email all'indirizzo da te indicato tramite la quale potrai completare l'iscrizione. </h3>
    
</l:main>
