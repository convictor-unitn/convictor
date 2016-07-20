<%-- 
    Document   : review.html
    Created on : Jul 20, 2016, 9:08:16 AM
    Author     : uriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials//" %>
<c:set var="bean" value="${requestScope.review}" scope="request" />

<l:main>
  <jsp:attribute name="title">Status Recensione</jsp:attribute>
    <jsp:attribute name="body">
      <c:choose>
        <c:when test="${bean.valid}">
          <div class="ui blue icon message">
            <i class="check circle icon"></i>
              <div class="content">
                <div class="header">
                  La recensione è stata inserita correttamente!
				</div>
              </div>
              <a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${bean.restaurantId}" class="ui blue submit button">Indietro</a>
          </div>
        </c:when>
        <c:otherwise>
          <p:formerrors/>
          <div class="sixteen wide column">
                <div class="ui center aligned grid">
                  <div class="column">
                    <div class="ui segment">
                      <div class="ui header">Inserisci una recensione</div>
                      <div id="rating-selector" class="ui large center rating" data-rating="1" data-max-rating="5"></div>
                      <div class="ui center comment">
                          <form class="ui small reply form" method="POST" action="${pageContext.servletContext.contextPath}/restaurants/addReview">
                            <input type="hidden" name="idRestaurant" value="${bean.restaurantId}"/>
                            <input type="hidden" id="ratingFormHidden" name="rating" value="" />
                            <div class="field">
                              <textarea name="reviewText"></textarea>
                            </div>
                            <div class="ui basic submit button">
                              <input class="ui button" type="submit" onclick="setInputValue()" value="Recensisci" class="icon edit">
                            </div>
                          </form>
                        </div>
                    </div>
                    <a href="${pageContext.servletContext.contextPath}/restaurants/show?id=${bean.restaurantId}" class="ui blue submit button">Indietro</a>
                  </div>
                </div>
              </div>
        </c:otherwise>
      </c:choose>
      <script type="text/javascript">
          // Use to load the rating inside the form
          function setInputValue() {
            var val = document.getElementById("rating-selector").getElementsByClassName("icon active").length;
            document.getElementById("ratingFormHidden").setAttribute('value', val);
          }
      </script>
      
    </jsp:attribute>
</l:main>