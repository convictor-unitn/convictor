<%-- 
    Document   : restaurant_notices
    Created on : 22-giu-2016, 14.15.20
    Author     : Giovanni M Riva/Federica Balliana
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="restaurant owner notifications list partial" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui container">

    <div class="ui stackable two column equal width center aligned grid">
      <!-- My Restaurants list-->
      <div class="stretched column">
        <div class="ui segment">
          <div class="text" style="float: left"> <h2> I miei ristoranti: </h2> </div>

          </br>
		  <c:forEach var="restaurant" items="${user.restaurants}">
		  <div>		
		    <a style="float: left" class="ui text" href="${pageContext.request.contextPath}/restaurants/show?id=${restaurant.id}" > <h3> ${restaurant.name} <h3> </a>
			<a style="float: right" href="${pageContext.request.contextPath}/restaurants/edit?id=${restaurant.id}" class="ui basic black right floated tiny button">Modifica</a> </br>
		 </div>
		  </c:forEach>

        </div>
      </div>
      <!-- END My Restaurants list-->
      
      <!-- Notifications list-->      
      <div class="stretched column">
          <div class="center text"> <h2> Notifiche </h2> </div>
        </br>

        <div class="ui one column center aligned grid">
          <div class="row">
              <div class="column">
                  <div class="ui buttons">

                      <div class="ui basic black button">

                        <c:if test="${actualPage-1 < 0}">
                            <a href="?${requestURLFilters}&noticePage=0"> 
                                <i class="left arrow icon"></i>
                            </a>
           
                        </c:if>
                            <c:if test="${actualPage-1 >= 0}">
                                <a href="?${requestURLFilters}&noticePage=${actualPage-1}"> 
                                   <i class="left arrow icon"></i>
                                </a>
                            </c:if>   
                            </div>
                        </div>
                        <div class="ui basic black button">
                            <a href="?${requestURLFilters}&noticePage=${requestScope.nextPagination}"> 
                                <i class="right arrow icon"></i>
                            </a>
                        </div><!--
                      <div class="ui basic label">

                      </div>-->
                  </div>
              </div>  
          </div>        
            <div class="column">
				<c:forEach var="notice" items="${user.notices}">
					<div class="ui segment feed">
					<div class="event">
					  <div class="content">
						<div class="date">
							<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${notice.createdAt}"/>
						</div>
						<div class="summary">
							<c:choose>
								<c:when test="${notice.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.ReviewNotice'}">
								   <a class="item" href="${pageContext.request.contextPath}/${notice.description}">
									   ${notice.registeredUser.name} ${notice.registeredUser.surname} ha aggiunto una nuova recensione.
								   </a>  
								</c:when>
								<c:when test="${notice.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.PhotoNotice'}">
								   <a class="item" href="${pageContext.request.contextPath}/${notice.description}">   
									${notice.registeredUser.name} ${notice.registeredUser.surname} ha aggiunto una nuova foto.
								   </a>  
								</c:when>
							</c:choose>
						</div>
						<!--<div class="extra">
						  <div class="ui right floated buttons">
							<button class="ui basic black button">Visualizza</button>
						  </div>
						</div>-->
					  </div>
					</div>
				  </div>
				</c:forEach>
            </div>
        </div>
      </div>
      <!-- END Notifications list-->

    </div>
</div>