<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Reimposta Password</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
              <div class="column">
                <h2 class="ui header">
                  <div id="s_text" class="content">
                    Reimpostazione password
                  </div>
                </h2>
                <form class="ui large form">
                  <div class="ui stacked segment">
                    <div class="field">
                      <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="Nuova password">
                      </div>
                    </div>
                    <div class="field">
                      <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="Conferma password">
                      </div>
                    </div>
                      <div id="p_button" class="ui fluid large submit button">Invia</div>
                    </br>
                  <div class="ui fluid submit button">Annulla</div>
                  </div>
                </form>
              </div>
            </div>

	</jsp:attribute>
		
</l:main>