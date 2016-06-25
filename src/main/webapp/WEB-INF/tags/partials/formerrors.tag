<%-- 
    Document   : formerrors
    Created on : 25-giu-2016, 17.55.30
    Author     : Giovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<jsp:useBean id="restaurant" scope="session" class="it.unitn.disi.webprog2016.convictor.app.beans.Restaurant" />

<c:forEach step="1" var="error" items="${restaurant.}"

<div class="ui error message">
    <div class="header">Action Forbidden</div>
    <p>You can only sign up for an account once with a given e-mail address.</p>
</div>