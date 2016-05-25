<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Index Page</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui hidden divider"></div>
		<h1>Index Page</h1>
                <nav>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/protected">Protected</a></li>
                        <li><a href="#">Sign In</a></li>
                        <li><a href="#">Sign Out</a></li>
                    </ul>
                </nav>
	</jsp:attribute>
                
                
		
</l:main>