<%-- 
    Document   : formerrors
    Created on : 25-giu-2016, 17.55.30
    Author     : Giovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>


<jsp:useBean id="restaurant" scope="request" class="it.unitn.disi.webprog2016.convictor.app.beans.Restaurant" />
<c:set var="restaurant" value="${requestScope.Restaurant}" />

    <div class="row">
        <div class="sixteen wide column">
            <div class="ui error message">
                <div class="header">Ci sono degli errori</div>
                <div class="ui list">
                    <c:forEach var="error" items="${restaurant.errors}" > 
                    <div class="item">
                        ${error}
                    </div>
                    </c:forEach>
                </div>    
            </div>
        </div>
    </div>    
    

