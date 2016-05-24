<%-- 
    Document   : main
    Created on : May 15, 2016, 10:32:40 AM
    Author     : umberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
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
	</head>
	<body>
		<partials:topNavbar />
		<jsp:invoke fragment="body" />
		<script type="text/javascript" src="${context}/js/jquery-2.2.4.js"></script>
		<script type="text/javascript" src="${context}/js/semantic.js"></script>
	</body>
</html>