<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<c:set var="results" scope="request" value="${requestScope.restaurantResult}" />
<!-- By now each results will display the first of its photos list as a preview -->
<c:set var="photo_index" scope="page" value="1" />

<l:main>
	
	<jsp:attribute name="title">Lista risultati</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="ui two column stackable grid container">

  <div class="three wide column">
    <div id="choices" class="ui form">
      <div class="grouped fields">
        <label>Ordina per:</label>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>classifica</label>
          </div>
        </div>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>nome</label>
          </div>
        </div>              
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>prezzo (dal piu' basso al piu' alto)</label>
          </div>
        </div>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
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
                <input name="${cusine.name}" type="checkbox">
                <label></label>
              </div>
            </div> 
        </c:forEach>                
      </div>
    </div>

	</br>
	<button class="ui fluid basic black button">Cerca</button>

  </div>
 
  <div class="thirteen wide column">

    <div id="results" class="ui three stackable cards">
        <c:forEach var="rest" items="${results}">
          <div class="ui card">
            <div class="image">
              <img src="${rest.photos[photo_index]}">
            </div>
            <div class="content">
              <a class="header">${rest.name}</a>
                <c:forEach var="i" begin="0" end="${rest.rating}" step="1">
                    <c:if test="${i!=0}">
                        <div class="ui heart rating">
                            <i class="heart icon"> </i>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach begin="${restaurant.rating}" end="4" step="1">
                        <div class="ui heart rating">
                            <i class="empty heart icon"> </i>
                        </div>
                </c:forEach>                  
              <div class="meta">
                <span class="date">${rest.reviews.length} recensioni</span>                
              </div>
              <div class="text">${rest} Posizione in classifica</div>
              <div class="description">
                <c:forEach var="cusine" items="${bean.cusine}">${cusine} </c:forEach> 
              </div>  
            </div>            
        </div>  
        </c:forEach>               
    </div>
  </div>
</div>
	</jsp:attribute>
		
</l:main>
