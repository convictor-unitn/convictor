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
	
	<jsp:attribute name="title">Remipostazione Profilo</jsp:attribute>
	<jsp:attribute name="bodyBackground">url("${pageContext.servletContext.contextPath}/images/background.png")</jsp:attribute>
	<jsp:attribute name="body">
<!--            <div class="ui middle aligned center aligned grid">
                <div class="column myform">
                    <h2 class="ui header">

                        <div id="s_text" class="content">
                            Reimpostazione profilo
                        </div>
                    </h2>
                      
                    <form class="ui large form" method="POST" action="${url}">
                        <div class="ui stacked segment">
                            <partials:registrationForm />
                            <div class="field">
                                <div class="field <c:if test="${bean.valid == false and !(bean.errors['password'] == null)}" >error</c:if>">
                                    <i class="lock icon"></i>
                                    <input type="password" name="passwordOld" placeholder="Vecchia password">
                                </div>
                            </div>
                            <div class="field <c:if test="${bean.valid == false and !(bean.errors['password'] == null)}" >error</c:if>">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="passwordNew" placeholder="Nuova password">
                                </div>
                            </div>
                             <div class="field <c:if test="${bean.valid == false and !(bean.errors['password'] == null)}" >error</c:if>">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="passwordConfirmation" placeholder="Conferma password">
                                </div>
                            </div>
                            <div class="ui text" align="right">
                            <a href="invio_req_pass.html">Password dimenticata?</a>
                        </div>

                        </br>
                            <input id="p_button" class="ui fluid large submit button" type="submit"></input>

                            </br>

                            <a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
                        </div>


                    </form>

                  </div>
            </div>-->
            
        </jsp:attribute>
		
</l:main>
        
