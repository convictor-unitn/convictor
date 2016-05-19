<%-- 
    Document   : topNavbar
    Created on : May 15, 2016, 10:46:40 AM
    Author     : umberto
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>


<%-- any content can be specified here e.g.: --%>
<nav>
	<ul>
		<li><a href="${pageContext.request.contextPath}/">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/protected">Protected</a></li>
		<li><a href="#">Sign In</a></li>
		<li><a href="#">Sign Out</a></li>
	</ul>
</nav>