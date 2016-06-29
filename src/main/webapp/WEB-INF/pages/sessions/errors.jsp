<%-- 
    Document   : errors
    Created on : Jun 25, 2016, 11:40:53 PM
    Author     : umberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:out value="Incluso" />

<c:if test="${param.bean.valid == false }">
    <div class="row">
        <div class="sixteen wide column">
            <div class="ui error message">
                <div class="header">Ci sono degli errori</div>
                <div class="ui list">
                   
                    <c:forEach var="error" items="${bean.errors}" > 
                    <div class="item">
                        ${error}
                    </div>
                    </c:forEach>
                </div>    
            </div>
        </div>
    </div>    
</c:if>  
