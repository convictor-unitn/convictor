<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Landing Page</jsp:attribute>
	
	<jsp:attribute name="body">

           <div class="ui vertical masthead center aligned segment">

                <div class="ui container">
                  <div class="ui menu" style="background-color:rgba(256,256,256,0.4)">   
                    <div class="right item">
                      <a class="ui basic black button"><b>Accedi</b></a>
                      <a class="ui basic black button"><b>Registrati</b></a>
                    </div> 
                  </div>
                </div>


                <div class="ui text container">
                  <h1 class="ui header">
                    <img class="ui small middle aligned circular image" src="logo.png">
                    <span class="content">Convictor</span>
                  </h1>
                </div>

                <div class="ui container">
                  <div class="ui very padded black basic segment" >   
                    <div class="ui mysegment">
                      <div class="ui medium fluid action input">
                        <input placeholder="Cerca ristorante..." type="text">
                        <button class="ui black button">Cerca</button>
                      </div>
                    </div>
                  </div>
                </div>

            </div>
            
            <div class="ui list">
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
                <div class="item">
                    
                </div>
            </div>


	</jsp:attribute>
                
                
		
</l:main>