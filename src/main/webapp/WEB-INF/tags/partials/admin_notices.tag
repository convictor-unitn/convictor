<%-- 
    Document   : admin_notices
    Created on : 22-giu-2016, 14.14.14
    Author     : Giovanni M Riva/Federica Balliana
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="admin notifications list partial" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <div class="ui container">
        <div class="text"> <h2> Notifiche </h2> </div>
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
								<c:when test="${notice.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice'}">
									<a class="item" href="${pageContext.request.contextPath}/${notice.description}">
									  ${notice.registeredUser.name} ${notice.registeredUser.surname} ha reclamato un ristorante.
									</a>  
								</c:when>
								<c:when test="${notice.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.PhotoRemovalNotice'}">
								   <a class="item" href="${pageContext.request.contextPath}/${notice.description}">
									   ${notice.registeredUser.name} ${notice.registeredUser.surname} ha richiesto la rimozione di una foto.
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