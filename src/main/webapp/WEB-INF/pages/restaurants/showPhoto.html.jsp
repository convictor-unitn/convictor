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
		<div class="row">
              <div class="column">
                  <div class="ui comments">
					<!-- Reviews List -->
                      <div class="comment">
						<div class="content">
							<img class="ui centered image" src="${photo.url}">
						</div>
					  </div>
                    </div>
						<div class="button black">
							<a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${photo.restaurantId}">
								Segnala fotografia
							</a>
						</div>
						<div class="button black">
							<a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${photo.restaurantId}">
								Vai alla pagina del ristorante
							</a>
						</div>
						<div class="button black">
							<a href="${pageContext.servletContext.contextPath}/userProfile/show">
								Torna al profilo
							</a>
						</div>
                </div>
                  
              </div>
      
    </jsp:attribute>
</l:main>