<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana

    Variabili che arrivano dal controller
    - user (già settata con c:set da requestScope)
    - user.errors contiene gli errori dei campi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="it.unitn.disi.webprog2016.convictor.app.beans.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<c:set var="user" scope="request" value="${requestScope.user}" />
<l:main>
	
	<jsp:attribute name="title">Reimposta Profilo Utente</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
                <div class="column myform">
                    <h2 class="ui header">

                        <div id="s_text" class="content">
                            Reimpostazione profilo utente
                        </div>
                    </h2>
                    <form action="${pageContext.request.contextPath}/userProfile/update" method="POST" class="ui large form">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="name" placeholder="Nome" value="${user.name}" />
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="surname" placeholder="Cognome" value="${user.surname}" />
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="email" placeholder="Email" value="${user.email}">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="oldPassword" placeholder="Vecchia password">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="password" placeholder="Nuova password">
                                </div>
                            </div>
                             <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="passwordConfirmation" placeholder="Conferma password">
                                </div>
                            </div>
                            <div class="ui text" align="right">
                            <a href="invio_req_pass.html">Password dimenticata?</a>
                        </div>

                        </br>
                        <input id="p_button" class="ui fluid large submit button" type="submit" value="Salva" />

                            </br>

                            <a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
                        </div>


                    </form>

                  </div>
            </div>

            
	</jsp:attribute>
		
</l:main>

