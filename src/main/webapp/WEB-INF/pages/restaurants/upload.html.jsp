<%-- 
    Document   : upload.html
    Created on : Jul 18, 2016, 3:17:33 PM
    Author     : umberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	<jsp:attribute name="title">Upload Status</jsp:attribute>
		
	<jsp:attribute name="body">
            
            <div class="ui blue icon message">
              <i class="check circle icon"></i>
              <div class="content">
                <div class="header">
                  L'immagine è stata caricata correttamente!
                </div>
              </div>
              <div class="ui blue submit button">Indietro</div>
            </div>

            <div class="ui negative icon message">
              <i class="Frown icon"></i>
              <div class="content">
                <div class="header">
                  Oh Oh, c'è stato un errore!
                </div>
                <p>Non è stato possibile caricare l'immagine.</p>
              </div>
              <div class="ui red submit button">Indietro</div>
            </div>
		
	</jsp:attribute>
</l:main>
