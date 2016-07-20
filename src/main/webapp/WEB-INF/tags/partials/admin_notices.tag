<%-- 
    Document   : admin_notices
    Created on : 22-giu-2016, 14.14.14
    Author     : Giovanni M Riva/Federica Balliana
--%>

<%@tag description="admin notifications list partial" pageEncoding="UTF-8"%>

    <div class="ui container">
        <div class="text"> <h2> Notifiche </h2> </div>
        </br>

        <div class="ui one column center aligned grid">
          <div class="row">
              <div class="column">
                  <div class="ui buttons">
                      <div class="ui basic black button">
                          <i class="left arrow icon"></i>
                      </div>
                  </div>
                  <div class="ui basic black button">                      
                        <i class="right arrow icon"></i>                      
                  </div>
                      <div class="ui basic label"  id="bg_cream">
                        2,048
                  </div>
              </div>  
          </div>        
            <div class="column">
              <div class="ui segment feed"  id="bg_cream">
                <div class="event">
                  <div class="content">
                    <div class="date">
                      Ieri
                    </div>
                    <div class="summary">
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