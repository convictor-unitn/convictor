<%-- 
    Document   : blank
    Created on : 25-mag-2016, 17.48.03
    Author     : Giovanni
--%>

<%@tag description="This tag contains the common elements to each page " pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" %>
<%@attribute name="body" required="true" fragment="true" %>

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
        
        <script type="text/javascript" src="${context}/js/jquery-2.2.4.js"></script>
        <script type="text/javascript" src="${context}/js/semantic.js"></script>
        <script type="text/javascript" src="${context}/js/topNavbarc.js"></script>
    </body>
</html>