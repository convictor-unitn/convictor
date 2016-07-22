<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>

<c:set var="bean" scope="request" value="${requestScope.user}" />

<l:main>
	
	<jsp:attribute name="title">Recupero Password</jsp:attribute>
	<jsp:attribute name="bodyBackground">#eaeaea</jsp:attribute>
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
              <div class="column myform">
                <h2 class="ui header">
                  <div id="s_text" class="content">
                    Reimpostazione password
                  </div>
                </h2>
                <form class="ui large form error">
                  <div class="ui stacked segment">
                    <div>Per reimpostare la password del tuo account ti sarà inviato all'indirizzo email che inserirai qui un link per effettuare l'operazione.</div>
                    </br>
                    <div class="field <c:if test="${bean.valid == false and !(bean.errors['email'] == null)}" >error</c:if>">
                      <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="email" placeholder="Email" value="${bean.email}">
                      </div>
						<partials:formerrors field="email" />
                    </div>
                    </br>
						<input id="p_button" class="ui fluid large submit button" type="submit" value="Reimposta la password" />
                    </br>
						<a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
                  </div>
                </form>
              </div>
            </div>


	</jsp:attribute>
		
</l:main>