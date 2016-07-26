<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="results" scope="request" value="${requestScope.results}" />
<c:set var="query" scope="request" value="${requestScope.queryString}" />

<%-- These JSTL tag are used to set correctly the pagination URL request --%>
<c:set var="nextPagination" scope="request" value="${requestScope.nextPagination}" />
<c:set var="requestURL" scope="request" value="${requestScope['javax.servlet.forward.query_string']}" />
<c:if test="${empty param.page}">
  <c:set var="requestURLFilters" scope="request" value="${requestURL}" />
  <c:set var="actualPage" scope="request" value="0" />
</c:if>
<c:if test="${!empty param.page}">
  <c:set var="requestURLFilters" scope="request" value="${fn:substringBefore(requestURL, '&page')}" />  
  <c:set var="actualPage" scope="request" value="${param.page}" />
</c:if>


<l:main>
	<jsp:attribute name="bodyBackground"></jsp:attribute>
	<jsp:attribute name="title"> Ricerca | Convictor</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui two column stackable grid container">
                <div class="three wide column">
                <form class="form" method="GET" action="${pageContext.servletContext.contextPath}/restaurants">
                    <input type="hidden" value="${queryString}" name="query"/>
                    <div id="choices" class="ui form">
                        <div class="grouped fields">
                            <label id="brown">Ordina per:</label>
                        <div class="field">
                        <div class="ui radio checkbox">
							<c:choose>
								<c:when test="${ param.sorting == 'nameSorting' }">
									<input name="sorting" value="nameSorting" type="radio" checked="checked">
								</c:when>
								<c:otherwise>
									<input name="sorting" value="nameSorting" type="radio">
								</c:otherwise>
							</c:choose>
                            <label id="brown">nome</label>
                        </div>
                        </div>              
                        <div class="field">
                            <div class="ui radio checkbox">
								<c:choose>
									<c:when test="${ param.sorting == 'priceAscSorting' }">
										<input name="sorting" value="priceAscSorting" type="radio" checked="checked">
									</c:when>
									<c:otherwise>
										<input name="sorting" value="priceAscSorting" type="radio">
									</c:otherwise>
								</c:choose>
                                <label id="brown">prezzo (dal piu' basso al piu' alto)</label>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui radio checkbox">
								<c:choose>
									<c:when test="${ param.sorting == 'priceDescSorting' }">
										<input name="sorting" value="priceDescSorting" type="radio" checked="checked">
									</c:when>
									<c:otherwise>
										<input name="sorting" value="priceDescSorting" type="radio">
									</c:otherwise>
								</c:choose>
                                <label id="brown">prezzo (dal piu' alto al piu' basso)</label>
                            </div>
                        </div>
                    </div>
                    </div>
                    </br>
                    <div id="choices" class="ui form">
                    <div class="grouped fields">
                        <label id="brown">Filtra per tipologia di cucina:</label>
                        <c:forEach var="cusine" items="${allCusines}">
                        <div class="field">
                            <div class="ui checkbox">
								<c:choose>	
									<c:when test="${param[cusine.idString] == 'on'}">
										<input name="${cusine.id}" type="checkbox" checked="checked">
									</c:when>
									<c:otherwise>
										<input name="${cusine.id}" type="checkbox">
									</c:otherwise>
								</c:choose>
                                <label id="brown">${cusine.name}</label>
                            </div>
                            </div> 
                        </c:forEach>                
                    </div>
                    </div>
                    </br>
                    <input class="ui fluid black button" type="submit" value="Filtra">
                </form>
					</br>
                <div class="column">
					<div class="ui buttons">
                            <div class="ui basic black button">
                                <c:if test="${actualPage-1 < 0}">
                                    <a href="?${requestURLFilters}&page=0"> 
                                        <i class="left arrow icon"></i>
                                    </a>
                                </c:if>
                                <c:if test="${actualPage-1 >= 0}">
                                    <a href="?${requestURLFilters}&page=${actualPage-1}"> 
                                        <i class="left arrow icon"></i>
                                    </a>
                                </c:if>
                                
                            </div>
                        </div>
                        <div class="ui basic black button">
                            <a href="?${requestURLFilters}&page=${requestScope.nextPagination}"> 
                                <i class="right arrow icon"></i>
                            </a>
                        </div>
<!--                        <div class="ui basic label">
                            2,048
                        </div>            -->
            </div>   

                            
            </div>
            
            
								
            <div class="thirteen wide column">

                <div id="results" class="ui three stackable cards">
					<c:if test="${fn:length(results) == 0}">
						<div class="ui yellow icon message">
							<i class="help circle icon"></i>
							  <div class="content">
								<div class="header">
								  Non sono stati trovati ristoranti corrispondenti con la tua ricerca.
								</div>
							  </div>
						  </div>
					</c:if>
                    <c:forEach var="rest" items="${results}" varStatus="status">
                        <c:set var="main_p_index" value="${0}"/>
                      <div class="ui card">
                          <div class="image">
							<a href="restaurants/show?id=${rest.id}" class="ui image"> 
							  <img src="${rest.photos[0].url}" href="restaurants/show?id=${rest.id}">
							</a>
                          </div>
                        <div class="content">
                          <a class="header" id="brown" href="restaurants/show?id=${rest.id}">${rest.name}</a>
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
                          <div class="text"> Posizione in classifica: ${status.index+1}</div>
						  <br />
                          <div class="ui small labels">
							 <c:forEach var="cusine" items="${rest.cusine}">
							  <div class="ui label">
								${cusine.name}
							  </div> 
							 </c:forEach> 							  
						  </div>    
                        </div>            
                    </div>  
                    </c:forEach>               
                </div>
              </div>
            </div>
	</jsp:attribute>
		
</l:main>
