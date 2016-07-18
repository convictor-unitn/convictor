<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/partials" %>

<c:set var="bean" value="${requestScope.user}" scope="request" />

<l:main>
	
	<jsp:attribute name="title">Nuovo Utente</jsp:attribute>
	<jsp:attribute name="bodyBackground">url("${pageContext.servletContext.contextPath}/images/background.png")</jsp:attribute>
	<jsp:attribute name="body">
            <div class="ui middle aligned center aligned grid">
                <div class="column myform">
                    <h2 class="ui header">

                        <div id="s_text" class="content">
                            Crea un nuovo account
                        </div>
                    </h2>			
					<f:registrationForm />
                </div>
            </div>	
        </jsp:attribute>
		
</l:main>