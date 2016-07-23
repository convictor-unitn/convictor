<%-- 
    Document   : restaurant_notices
    Created on : 22-giu-2016, 14.15.20
    Author     : Giovanni M Riva/Federica Balliana
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="restaurant owner notifications list partial" pageEncoding="UTF-8"%>

<div class="ui container">

    <div class="ui stackable two column equal width center aligned grid">
      <!-- My Restaurants list-->
      <div class="stretched column">
        <div class="ui segment">
          <div class="text" style="float: left"> <h2> I miei ristoranti: </h2> </div>

          </br>
		  
		  <div>		
		    <a style="display:inline; float: left;" class="ui text" href="#" > <h3> Ristorante 1 <h3> </a>
			<a style="display:inline;" href="${pageContext.request.contextPath}/restaurants/edit" class="ui basic black right floated button">Modifica</a>
		 </div>

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
                        </div>
                      <div class="ui basic label">

                        2,048
                      </div>
                  </div>
              </div>  
          </div>        
            <div class="column">
              <div class="ui segment feed">
                <div class="event">
                  <div class="content">
                    <div class="date">
                      Ieri
                    </div>
                    <div class="summary">
                      <a>Utente</a> ha recensito <a>Ristorante</a>
                    </div>
                    <div class="extra">
                      <div class="ui right floated buttons">
                        <button class="ui basic black button">Visualizza</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="ui segment feed">
                <div class="event">
                  <div class="content">
                    <div class="date">
                      Ieri
                    </div>
                    <div class="summary">
                      <a>Utente</a> ha aggiunto una foto a <a>Ristorante</a>
                    </div>
                    <div class="extra">
                      <div class="ui right floated buttons">
                        <button class="ui basic black button">Visualizza</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
      <!-- END Notifications list-->

    </div>
</div>