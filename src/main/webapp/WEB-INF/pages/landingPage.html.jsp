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
	<jsp:attribute name="bodyBackground">url("${pageContext.servletContext.contextPath}/images/background.jpg")</jsp:attribute>
	<jsp:attribute name="title">Convictor</jsp:attribute>	
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
                    <img class="ui small middle aligned circular image" src="${pageContext.servletContext.contextPath}/images/logo_landing.png">
                    <img class="ui middle aligned big image" src="${pageContext.servletContext.contextPath}/images/convictor.png">
                  </h1>
                </div>
                
                <div class="ui grid">
                    <div class="ui computer tablet only row">
                     <div class="ui container">

                         <form class="ui very padded black basic segment" style="color: #4f3e3e;" method="GET" action="${pageContext.request.contextPath}/restaurants" >   
                        <div class="ui mysegment">
                          <div class="ui medium fluid action input">
                            <input placeholder="Cerca.." type="text" name="query" style="color: #4f3e3e;">

                            <button class="ui black submit button" type=submit>Cerca</button>
                          </div>
                        </div>
                      </form>
                     </div>
                    </div>
               
                    <div class="ui mobile only row">
                        <div class="column">
                            <form class="ui black basic segment" method="GET" action="${pageContext.request.contextPath}/restaurants" >                           
                                <div class="ui fluid input">
                                    <input placeholder="Cerca.." type="text" name="query">                            
                                </div>                        
                                <button class="ui black submit fluid button" type=submit>Cerca</button>
                            </form>
                        </div>
                    </div>
                </div>
           </div>                


	</jsp:attribute>
                
                
		
</l:main>