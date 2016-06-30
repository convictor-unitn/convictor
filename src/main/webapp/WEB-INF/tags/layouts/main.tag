<%-- 
    Document   : main
    Created on : May 15, 2016, 10:32:40 AM
    Author     : Giovanni M Riva
--%>

<%@tag description="This tag contains the main layout for all pages except for error pages" pageEncoding="UTF-8"%>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" %>
<%@attribute name="body" required="true" fragment="true" %>
<%@attribute name="bodyBackground" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${sessionScope.user}" />

<!DOCTYPE html>
<html>
	<head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
            <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
            <link rel="stylesheet" type="text/css" href="${context}/css/semantic.css" media="all" />
            <link rel="stylesheet" type="text/css" href="${context}/css/topNavbar.css" media="all" />
            <link rel="stylesheet" type="text/css" href="${context}/css/landingPage.css" media="all" />
            <link rel="stylesheet" type="text/css" href="${context}/css/forms.css" media="all" />
            <link rel="stylesheet" type="text/css" href="${context}/css/main.css" media="all" />            




            <title>${title}</title>
	</head>
	<body style="background: <c:out value="${bodyBackground}" />">
            <partials:topNavbar />
            <jsp:invoke fragment="body" />
            <script type="text/javascript" src="${context}/js/jquery-2.2.4.js"></script>
            <script type="text/javascript" src="${context}/js/semantic.js"></script>
            <script type="text/javascript" src="${context}/js/topNavbar.js"></script>
            <script type="text/javascript" src="${context}/js/restaurant_profile.js"></script>
	</body>
</html>