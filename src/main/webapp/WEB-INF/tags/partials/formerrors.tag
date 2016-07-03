<%-- 
    Document   : formerrors
    Created on : 25-giu-2016, 17.55.30
    Author     : Giovanni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>


            <div class="ui error message">
                <div class="header">Ci sono degli errori</div>
                <ul>
                   
                    <c:forEach var="error" items="${bean.errors}" >
                        <li>
                            ${error['value']}
                        </li>
                    </c:forEach>
                </ul>    
            </div>

