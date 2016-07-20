<%-- 
    Document   : registrationForm
    Created on : 27-giu-2016, 18.40.27
    Author     : Giovanni
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<form class="ui large form" method="POST" action="${pageContext.servletContext.contextPath}/registrations/create">
	<div class="ui stacked segment">   
		<div class="field">
			<div class="ui left icon input">
				<i class="user icon"></i>
				<input type="text" name="name" placeholder="Nome" value="${bean.name}">
			</div>
		</div>
		<div class="field">
			<div class="ui left icon input">
				<i class="user icon"></i>
				<input type="text" name="surname" placeholder="Cognome" value="${bean.surname}">
			</div>
		</div>
		<div class="field">
			<div class="ui left icon input">
				<i class="user icon"></i>
				<input type="text" name="email" placeholder="Email" value="${bean.email}">
			</div>
		</div>
		<div class="field">
			<div class="ui left icon input">
				<i class="lock icon"></i>
				<input type="password" name="passwordNew" placeholder="Password">
			</div>
		</div>
		<div class="field">
			<div class="ui left icon input">
				<i class="lock icon"></i>
				<input type="password" name="passwordConfirmation" placeholder="Conferma password">
			</div>
		</div>

		<div  align="left">
			<div class="ui slider checkbox">
				<input type="checkbox" name="privacy" value="checked" ${bean.privacy} />
				<label id="brown">Accetto le condizioni d'uso di servizio</label>
			</div>
		</div>
			
		</br>
		
		<input id="p_button" class="ui fluid large submit button" type="submit" value="Registrati" />
		
		</br>
		
		<a href="javascript:history.back()" class="ui fluid submit button">Annulla</a>
	</div>
</form>