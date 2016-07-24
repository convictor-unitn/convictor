<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>

<c:set var="bean" value="${requestScope.user}" scope="request" />

<l:main>
	
	<jsp:attribute name="title">Accedi | Convictor</jsp:attribute>
	<jsp:attribute name="bodyBackground">#eaeaea</jsp:attribute>
	<jsp:attribute name="body">
        <div class="ui middle aligned center aligned grid">
          <div class="column myform">
            <h2 class="ui header">
              <div id="s_text" class="content">
                Accedi
              </div>
            </h2>
                            
              <form method="POST" class="ui large form error" action="${pageContext.servletContext.contextPath}/sessions/create">
              <div class="ui stacked segment">
				  <partials:formerrors field="loginError"/>
                
                <div class="field <c:if test="${bean.valid == false and not (bean.errors['loginError'] == null)}" >error</c:if>">
                  <div class="ui left icon input">
                    <i class="user icon"></i>
                    <input type="text" name="email" placeholder="Email" value="${bean.email}" />
                  </div>
                </div>
                <div class="field <c:if test="${bean.valid == false and not (bean.errors['loginError'] == null)}" >error</c:if>">
                  <div class="ui left icon input">
                    <i class="lock icon"></i>
                    <input type="password" name="password" placeholder="Password" />
                  </div>
                </div>
                <div class="ui text" align="right">
                  <a href="${pageContext.servletContext.contextPath}/passwords/request_new">Password dimenticata?</a>
                </div>
                </br>
                    <input type="submit" id="p_button" class="ui fluid large submit button"  value="Accedi"/>
                </br>
                <a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
              </div>
            </form>
            </br>
            <div class="text">
              Non hai ancora un account? <a href="${pageContext.servletContext.contextPath}/sign_up">Registrati</a>
            </div>
          </div>
        </div>

	</jsp:attribute>
		
</l:main>