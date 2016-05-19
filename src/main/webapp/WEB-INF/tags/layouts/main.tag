<%-- 
    Document   : main
    Created on : May 15, 2016, 10:32:40 AM
    Author     : umberto
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" %>
<%@attribute name="body" required="true" fragment="true" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${title}</title>
	</head>
	<body>
		<partials:topNavbar />
		<jsp:invoke fragment="body" />
	</body>
</html>