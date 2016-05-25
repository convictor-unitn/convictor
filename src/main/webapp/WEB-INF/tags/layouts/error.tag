<%-- 
    Document   : error
    Created on : 25-mag-2016, 17.28.48
    Author     : Giovanni
--%>

<%@tag description="This tag contains error pages layout" pageEncoding="UTF-8"%>
<%@taglib prefix="blank" tagdir="/WEB-INF/tags/layouts/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="errorCode" required="true" %>
<%@attribute name="message" required="true" %>

<c:set var="context" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${context}/css/semantic.css" media="all" />
        <link rel="stylesheet" type="text/css" href="${context}/css/topNavbar.css" media="all" />
    </head>
    <body>
        <!-- Page Contents -->
        <div class="ui hidden divider"></div>
        <div class="ui container">
          <div class="row">
            <div class="column">
              <div class="ui segment raised ">
                <h1 class="ui header">Something went wrong!</h1>    
                <div class="text">${errorCode} ${message}</div>
              </div>
            </div>
          </div>
        </div>
        <script type="text/javascript" src="${context}/js/jquery-2.2.4.js"></script>
        <script type="text/javascript" src="${context}/js/semantic.js"></script>
        <script type="text/javascript" src="${context}/js/topNavbar.js"></script>
    </body>
</html>
