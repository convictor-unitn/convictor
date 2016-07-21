<%-- 
    Document   : registrationForm
    Created on : 27-giu-2016, 18.40.27
    Author     : Giovanni
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<<<<<< HEAD
    <c:if test="${empty bean.id || bean.id == 0}">
        <c:set var="url" value="${pageContext.servletContext.contextPath}/registrations/create" />
    </c:if>
    <c:if test="${!empty bean.id && bean.id != 0}">
        <c:set var="url" value="${pageContext.servletContext.contextPath}/registrations/update" />
    </c:if>

    <div class="field <c:if test="${bean.valid == false and not bean.errors['name'] == null}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="name" placeholder="Nome" value="${user.name}">
        </div>
    </div>
    <div class="field <c:if test="${bean.valid == false and not bean.errors['surname'] == null}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="surname" placeholder="Cognome" value="${user.surname}">
        </div>
    </div>
    <div class="field <c:if test="${bean.valid == false and not bean.errors['email'] == null}" >error</c:if>">
        <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="email" placeholder="Email" value="${user.email}">
        </div>
    </div>
