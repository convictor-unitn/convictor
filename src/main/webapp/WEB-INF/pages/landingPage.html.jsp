<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<jsp:useBean id="restaurant" scope="request" class="it.unitn.disi.webprog2016.convictor.app.beans.Restaurant" />

<l:main>
	<jsp:attribute name="bodyBackground">url("${pageContext.servletContext.contextPath}/images/background.png")</jsp:attribute>
	<jsp:attribute name="title">Landing Page</jsp:attribute>	
	<jsp:attribute name="body">
           

           <div class="ui vertical masthead center aligned segment">
               <c:if test="${sessionScope.user == null}" >
                <div class="ui container">
<!--              <div class="ui menu" style="background-color:rgba(256,256,256,0.4)">   
                    <div class="right item">
                      <a class="ui basic black button" href="${pageContext.request.contextPath}/sign_in"><b>Accedi</b></a>
                      <a class="ui basic black button" href="${pageContext.request.contextPath}/sign_up"><b>Registrati</b></a>
                    </div> 
                  </div>-->
                </div>
                </c:if>
                <div class="ui text container">
                  <h1 class="ui header">
                    <img class="ui small middle aligned circular image" src="${pageContext.servletContext.contextPath}/images/logo.png">
                    <span class="content">Convictor</span>
                  </h1>
                </div>
               
                 <div class="ui container">
                     <form class="ui very padded black basic segment" method="POST" action="${pageContext.request.contextPath}/restaurants/index" >   
                    <div class="ui mysegment">
                      <div class="ui medium fluid action input">
                        <input placeholder="Cerca per ristorante" type="text" name="name">
                        <button class="ui black submit button" type=submit>Cerca</button>
                      </div>
                    </div>
                  </form>
                </div>
               
                  
                

            </div>
            
            <div class="ui list">
                <div class="item">
                <a href="${pageContext.request.contextPath}/passwords/request_new">Reimposta Password</a>
                </div>
                <div class="item">
                <a href="#">Recupera Password</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/sign_in/edit?id=1">Reimposta Profilo</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/restaurants/show?id=1">Pagina ristorante</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/restaurants/edit?id=1">Modifica ristorante</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/restaurants/">Ricerca Ristoranti</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/restaurants/new">Nuovo ristorante</a>
                </div>
                <div class="item">
                <a href="${pageContext.request.contextPath}/">Home</a>
                </div>
            </div>


	</jsp:attribute>
                
                
		
</l:main>