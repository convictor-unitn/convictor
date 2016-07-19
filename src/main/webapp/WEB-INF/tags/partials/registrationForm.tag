<%-- 
    Document   : registrationForm
    Created on : 27-giu-2016, 18.40.27
    Author     : Giovanni
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

    <div class="field">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="name" placeholder="Nome" value="${user.name}">
        </div>
    </div>
    <div class="field">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="surname" placeholder="Cognome" value="${user.surname}">
        </div>
    </div>
    <div class="field">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="email" placeholder="Email" value="${user.email}">
        </div>
    </div>