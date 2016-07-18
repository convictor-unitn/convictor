<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="results" scope="request" value="${requestScope.results}" />
<c:set var="query" scope="request" value="${requestScope.queryString}" />

<l:main>
	
	<jsp:attribute name="title">Lista risultati</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui two column stackable grid container">

            
                <div class="three wide column">
                <form class="form" method="GET" action="${pageContext.servletContext.contextPath}/restaurants">
                <input type="hidden" value="${queryString}" name="query"/>
                <div id="choices" class="ui form">
                  <div class="grouped fields">
                    <label>Ordina per:</label>
                    <div class="field">
                      <div class="ui radio checkbox">
                        <input name="sorting" value="nameSorting" type="radio">
                        <label>nome</label>
                      </div>
                    </div>              
                    <div class="field">
                      <div class="ui radio checkbox">
                        <input name="sorting" value="priceAscSorting" type="radio">
                        <label>prezzo (dal piu' basso al piu' alto)</label>
                      </div>
                    </div>
                    <div class="field">
                      <div class="ui radio checkbox">
                        <input name="sorting" value="priceDescSorting" type="radio">
                        <label>prezzo (dal piu' alto al piu' basso)</label>
                      </div>
                    </div>
                  </div>
                </div>

                </br>

                <div id="choices" class="ui form">
                  <div class="grouped fields">
                    <label>Filtra per tipologia di cucina:</label>
                    <c:forEach var="cusine" items="${allCusines}">
                       <div class="field">
                          <div class="ui checkbox">
                            <input name="${cusine.id}" type="checkbox">
                            <label>${cusine.name}</label>
                          </div>
                        </div> 
                    </c:forEach>                
                  </div>
                </div>

                    </br>
                    <input class="ui fluid basic black button" type="submit" value="Filtra">                    
                </form>
                </div>

              <div class="thirteen wide column">

                <div id="results" class="ui three stackable cards">
                    <c:forEach var="rest" items="${results}">
                        <c:set var="main_p_index" value="${rest.mainPhotoId}"/>
                      <div class="ui card">
                          <div class="image">
                            <img src="${rest.photos[main_p_index]}">
                          </div>
                        <div class="content">
                          <a class="header" href="restaurants/show?id=${rest.id}">${rest.name}</a>
                            <div class="ui horizontal list">
                                <c:forEach var="i" begin="0" end="${rest.rating}" step="1">
                                    <c:if test="${i!=0}">
                                        <div class="item">
                                            <i class="heart icon"> </i>
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <c:forEach begin="${rest.rating}" end="4" step="1">
                                    <div class="item">
                                        <i class="empty heart icon"> </i>
                                    </div>
                                </c:forEach>
                            </div>                  
                          <div class="meta">
                            <span class="date">${fn:length(rest.reviews)} recensioni</span>                
                          </div>
                          <div class="text"><!-- {rest.position} --> Posizione in classifica</div>
                          <div class="description">
                                <c:forEach var="cusine" items="${rest.cusine}">${cusine.name}</c:forEach> 
                          </div>  
                        </div>            
                    </div>  
                    </c:forEach>               
                </div>
              </div>
            </div>
	</jsp:attribute>
		
</l:main>
