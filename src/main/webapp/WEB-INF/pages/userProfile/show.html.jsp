<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana

    Variabili che arrivano dal controller
    - user (già settata con c:set da requestScope)
    - user.notices (riferirsi alla interfaccia Notice dei beans)

--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- These JSTL tag are used to set correctly the pagination URL request --%>
<c:set var="nextPagination" scope="request" value="${requestScope.nextPagination}" />
<c:set var="requestURL" scope="request" value="${requestScope['javax.servlet.forward.query_string']}" />
<c:if test="${empty param.noticePage}">
  <c:set var="requestURLFilters" scope="request" value="${requestURL}" />
  <c:set var="actualPage" scope="request" value="0" />  
</c:if>
<c:if test="${!empty param.noticePage}">
  <c:set var="requestURLFilters" scope="request" value="${fn:substringBefore(requestURL, '&noticePage')}" />  
  <c:set var="actualPage" scope="request" value="${param.noticePage}" />
</c:if>

<c:set var="bean" scope="request" value="${requestScope.user}" />

<l:main>
	
	<jsp:attribute name="title"> ${bean.name} ${bean.surname}| Convictor </jsp:attribute>
	<jsp:attribute name="bodyBackground"></jsp:attribute>
	<jsp:attribute name="body">
            <div class="ui container">

                </br>
                
                <div class="text"> <h2> Il mio profilo </h2> </div>

                </br>

                <div class="text"> Nome: </div> 
                <div class="ui segment"> ${bean.name} </div>
                <div class="text"> Cognome: </div> 
                <div class="ui segment"> ${bean.surname} </div>
                <div class="text" > Email: </div> 
                <div class="ui segment"> ${bean.email} </div>
                </br>
                <a href="${pageContext.request.contextPath}/userProfile/edit" class="fluid ui button" id="p_button">Modifica le informazioni del profilo</a>
            </div>
            <div class="ui divider"></div>
            
            <!--restaurant_owner-->
            <c:if test="${user.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner'}" >
               <partials:restaurant_notices />
            </c:if>
            
            <!--admin-->           
            <c:if test="${user.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.Administrator'}" >
			    <div class="ui container">
					<a href="${pageContext.request.contextPath}/restaurants/new" class="ui black fluid button">Clicca qui per aggiungere un nuovo ristorante</a>
				</div>
				<div class="ui divider"></div>
                <partials:admin_notices />
            </c:if>

	</jsp:attribute>        
		
</l:main>

