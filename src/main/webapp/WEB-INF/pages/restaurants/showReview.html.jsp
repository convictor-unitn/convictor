<%-- 
    Document   : review.html
    Created on : Jul 20, 2016, 9:08:16 AM
    Author     : uriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>
<c:set var="review" value="${requestScope.review}" scope="request" />

<l:main>
  <jsp:attribute name="title">Recensione</jsp:attribute>
    <jsp:attribute name="body">
		<div class="row">
              <div class="column">
                  <div class="ui comments">
					<!-- Reviews List -->
                      <div class="comment">
						<div class="content">
							<a class="author" id="brown">${review.registeredUserName}</a>
							<div class="metadata">
								<div class="date" id="brown">
									  <fmt:formatDate
									  pattern="dd-MM-yyyy HH:mm"
									  value="${review.createdAt}"/>
								</div>
								<div class="rating">
									<div class="ui horizontal list">
										<c:forEach var="i" begin="0" end="${review.rating}" step="1">
										<c:if test="${i!=0}">
										   <div class="item">
												<i class="star icon"> </i>
											</div>
										</c:if>
									</c:forEach>
									<c:forEach begin="${review.rating}" end="4" step="1">
										<div class="item">
											<i class="empty star icon"> </i>
										</div>
									</c:forEach>
									</div>                                    
								</div>
							</div>
						<div class="text" id="brown">
						  ${review.description}
						</div>
					  </div>
                    </div>
						<div class="button black">
							<a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${review.restaurantId}">
								Indietro
							</a>
						</div>
                </div>
                  
              </div>
      
    </jsp:attribute>
</l:main>