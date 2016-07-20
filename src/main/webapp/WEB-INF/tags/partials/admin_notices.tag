<%-- 
    Document   : admin_notices
    Created on : 22-giu-2016, 14.14.14
    Author     : Giovanni M Riva/Federica Balliana
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="admin notifications list partial" pageEncoding="UTF-8"%>

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
                                <i class="left arrow icon" id="brown"></i>
                            </a>
                            </c:if>
                            <c:if test="${actualPage-1 >= 0}">
                                <a href="?${requestURLFilters}&noticePage=${actualPage-1}"> 
                                   <i class="left arrow icon" id="brown"></i>
                                </a>
                            </c:if>   
                        </div>
                        <div class="ui basic black button">
                            <a href="?${requestURLFilters}&noticePage=${requestScope.nextPagination}"> 
                                <i class="right arrow icon" id="brown"></i>
                            </a>
                        </div>
                      <div class="ui basic label" id="brown">
                        2,048
                      </div>
                  </div>
              </div>  
          </div>        
            <div class="column">
              <div class="ui segment feed" id="brown">
                <div class="event">
                  <div class="content">
                    <div class="date">
                      Ieri
                    </div>
                    <div class="summary" id="brown">
                      <a>Utente</a> ha reclamato <a>Ristorante</a>
                    </div>
                    <div class="extra">
                      <div class="ui right floated buttons">
                        <button class="ui basic black button">Conferma</button>
                        <button class="ui basic black button">Declina</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>