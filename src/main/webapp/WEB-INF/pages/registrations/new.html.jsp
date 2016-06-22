<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Nuovo Utente</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
                <div class="column">
                    <h2 class="ui header">

                        <div id="s_text" class="content">
                            Crea un nuovo account
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
                                    <input type="password" name="password" placeholder="Password">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="conf_password" placeholder="Conferma password">
                                </div>
                            </div>

                            <div  align="left">
                                <div class="ui slider checkbox">
                                    <input type="checkbox" name="privacy">
                                    <label>Accettazione privacy</label>
                                </div>
                            </div>

                            </br>

                            <div id="p_button" class="ui fluid large submit button">Registrati</div>

                            </br>

                            <div class="ui fluid submit button">Annulla</div>
                        </div>
                    </form>
                </div>
            </div>	
        </jsp:attribute>
		
</l:main>