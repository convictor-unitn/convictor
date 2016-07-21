<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/partials/" %>


<c:set var="bean" scope="request" value="${requestScope.user}" />


<l:main>
	
	<jsp:attribute name="title">Reimposta Password</jsp:attribute>
	<jsp:attribute name="bodyBackground">url("${pageContext.servletContext.contextPath}/images/background.png")</jsp:attribute>
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
              <div class="column myform">
                <h2 class="ui header">
                  <div id="s_text" class="content">
                    Reimpostazione password
                  </div>
                </h2>
                <form class="ui large form">
                  <div class="ui stacked segment">
                    <p:formerrors />
                    <div class="field <c:if test="${bean.valid == false and not bean.errors['password'] == null}" >error</c:if>">
                      <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="Nuova password">
                      </div>
                    </div>
                    <div class="field <c:if test="${bean.valid == false and not bean.errors['password'] == null}" >error</c:if>">
                      <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="Conferma password">
                      </div>
                    </div>
                      <input id="p_button" class="ui fluid large submit button" type="submit"></input>
                    </br>
                  <div class="ui fluid submit button">Annulla</div>
                  </div>
                </form>
              </div>
            </div>
	</jsp:attribute>
		
</l:main>