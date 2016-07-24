<%-- 
    Document   : error
    Created on : 25-mag-2016, 17.28.48
    Author     : Giovanni M Riva
--%>

<%@tag description="This tag contains error pages layout" pageEncoding="UTF-8"%>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="errorCode" required="true" %>
<%@attribute name="message" required="true" %>
<%@attribute name="title"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${context}/css/semantic.css" media="all" />
        <link rel="stylesheet" type="text/css" href="${context}/css/main.css" media="all" />            
        <link rel="stylesheet" type="text/css" href="${context}/css/topNavbar.css" media="all" />
        <link rel="stylesheet" type="text/css" href="${context}/css/mainStyle.css" media="all" />
        <link rel="icon" href="${context}/images/favicon.ico" />
        
        <title>${title}</title>

    </head>
    
    <body>
        <partials:topNavbar />
        <!-- Page Contents -->
        <div class="ui hidden divider"></div>
        <div class="ui container">
          <div class="row">
            <div class="column">
              <div class="ui segment raised ">
                <h1 class="ui header">Ops! Qualcosa e' andato storto.</h1>    
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
