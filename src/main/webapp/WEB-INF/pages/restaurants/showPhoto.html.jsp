<%-- 
    Document   : showPhoto.html
    Created on : Jul 24, 2016, 2:48:06 PM
    Author     : uriel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>
<c:set var="photo" value="${requestScope.photo}" scope="request" />

<l:main>
  <jsp:attribute name="title">Foto ristorante</jsp:attribute>
    <jsp:attribute name="body">
		<div class="ui one stackable centered column grid">
			<div class="row">
              <div class="thirteen wide center aligned column">
                  <div class="ui comments mycomments">
					<!-- Reviews List -->
                      <div class="comment">
						<div class="content">
							<img class="ui centered bordered rounded image" src="${photo.url}">
						</div>
					  </div>
                    </div>
						<c:choose>
							<c:when test="${sessionScope.user.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.Administrator'}" >
								<form method="POST" action="${pageContext.servletContext.contextPath}/restaurants/approveReport">
									<input type="hidden" name="photoId" value="${photo.id}">
									<input type="hidden" name="noticeId" value="${requestScope.noticeId}">
									<input type="submit" class="ui button black" name="approve" value="Approva">
									<input type="submit" class="ui button black" name="reject" value="Rifiuta">
								</form>
							</c:when>
							<c:otherwise>
								<form method="POST" action="${pageContext.servletContext.contextPath}/restaurants/report">
									<input type="hidden" name="photoId" value="${photo.id}">
									<c:if test="${empty requestScope.alreadyReport || requestScope.alreadyReport != true}">
										<input type="submit" class="ui button black" value="Segnala foto">
									</c:if>
									<c:if test="${!empty requestScope.alreadyReport && requestScope.alreadyReport == true}">
										La foto è già stata segnalata all'amministratore.
									</c:if>
								</form>
							</c:otherwise>
						</c:choose>
			  </div>
            </div>
						<div class="thirteen wide column">
							<div class="ui basic black two buttons">
							<div class="ui button " href="${pageContext.servletContext.contextPath}/restaurants/show?id=${photo.restaurantId}">
								
									Vai alla pagina del ristorante
								
								</div>
								<div class="ui button" href="${pageContext.servletContext.contextPath}/userProfile/show">
							
										Torna al profilo
								
								</div>
							</div>
						</div>						
						

                  
		</div>
		
      
    </jsp:attribute>
</l:main>