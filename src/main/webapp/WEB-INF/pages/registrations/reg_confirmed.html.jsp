<%-- 
    Document   : reg_confirmed
    Created on : 19-lug-2016, 15.13.23
    Author     : federica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/partials" %>

<c:set var="bean" value="${requestScope.user}" scope="request" />

<l:main>
	
  </br>

  <h2> La registrazione a Convitor è ora completa. </br>
       Benvenuto nel nostro sito!! :) </h2>
  
  </br>
  
  <h3> Puoi usare il menu in alto per raggiungere la pagina del tuo profilo, 
       oppure per effettuare una ricerca cliccando <a href="">qui</a> raggingerai la home page. </h3>
    
</l:main>
