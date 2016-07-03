<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Giovanni M Riva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>

<c:set var="bean" value="${requestScope.restaurant}" scope="request" />
<c:set var="allCusines" value="${requestScope.allCusines}" scope="request" />

<l:main>
	
	<jsp:attribute name="title">Nuovo Ristorante</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">
                <div class="ui middle aligned center aligned grid">
                  <div class="column">
                     
                    <h2 class="ui header">
                      <div id="s_text" class="content">
                        Aggiungi Ristorante
                      </div>
                    </h2>
                    <p:restaurantForm />
                  </div>
                </div>
            </div>

	</jsp:attribute>
		
</l:main>