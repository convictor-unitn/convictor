<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Giovanni M Riva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="it.unitn.disi.webprog2016.convictor.app.beans.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials" %>

<c:set var="bean" scope="request" value="${requestScope.restaurant}" />

<l:main>
	<jsp:attribute name="bodyBackground">#eaeaea</jsp:attribute>
	<jsp:attribute name="title">Modifica Ristorante</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">
                <div class="ui middle aligned center aligned grid">
                  <div class="column">
                      
                    <h2 class="ui header">
                      <div class="content">
                        Modifica Ristorante
                      </div>
                    </h2>
                      <partials:formerrors />
                      
                      <partials:restaurantForm />
                  </div>
                </div>
            </div>
	</jsp:attribute>
		
</l:main>