<%-- 
    Document   : registrationForm
    Created on : 27-giu-2016, 18.40.27
    Author     : Giovanni
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials" %>

    
    <div class="field <c:if test="${bean.valid == false and !( bean.errors['name'] == null)}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="name" placeholder="Nome" value="${user.name}">
        </div>
		<partials:formerrors field="name"/>
    </div>
    <div class="field <c:if test="${bean.valid == false and !( bean.errors['surname'] == null)}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="surname" placeholder="Cognome" value="${user.surname}">				
        </div>
		<partials:formerrors field="surname"/>
    </div>
    <div class="field <c:if test="${bean.valid == false and !( bean.errors['email'] == null)}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="email" placeholder="Email" value="${user.email}">			
        </div>
		<partials:formerrors field="email"/>
    </div>

