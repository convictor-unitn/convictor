<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Recupero Password</jsp:attribute>
	<jsp:attribute name="bodyBackground">#eaeaea</jsp:attribute>
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
              <div class="column myform">
                <h2 class="ui header">
                  <div id="s_text" class="content" id="brown">
                    Reimpostazione password
                  </div>
                </h2>
                <form class="ui large form" method="POST" action="${url}">
                  <div class="ui stacked segment" id="brown">
                    <div>Per reimpostare la password del tuo account ti sarà inviato all'indirizzo email che inserirai qui un link per effettuare l'operazione.</div>
                    </br>
                    <div class="field">
                      <div class="ui left icon input" >
                        <i class="user icon"></i>
                        <input type="text" name="email" placeholder="Email">
                      </div>
                    </div>
                    </br>
                      <input id="p_button" class="ui fluid large submit button" type="submit"></input>
                    </br>
                    <a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
                  </div>
                </form>
              </div>
            </div>
	</jsp:attribute>
		
</l:main>