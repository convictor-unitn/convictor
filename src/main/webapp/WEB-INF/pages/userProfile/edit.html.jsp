<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

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
                    <form class="ui large form">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="nome" placeholder="Nome">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="cognome" placeholder="Cognome">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="email" placeholder="Email">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="password" placeholder="Vecchia password">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="nuova_password" placeholder="Nuova password">
                                </div>
                            </div>
                             <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="conf_password" placeholder="Conferma password">
                                </div>
                            </div>
                            <div class="ui text" align="right">
                            <a href="invio_req_pass.html">Password dimenticata?</a>
                        </div>

                        </br>
                        <input id="p_button" class="ui fluid large submit button" type="submit">Modifica</input>

                            </br>

                            <div class="ui fluid submit button">Annulla</div>
                        </div>


                    </form>

                  </div>
            </div>

            
	</jsp:attribute>
		
</l:main>

