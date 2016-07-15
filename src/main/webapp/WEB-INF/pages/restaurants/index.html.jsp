<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Federica Balliana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>

<l:main>
	
	<jsp:attribute name="title">Lista risultati</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="ui two column stackable grid container">

  <div class="three wide column">
     <div class="ui one column grid">
      <div class="row">                
       <div class="column">
              <div id="choices" class="ui form">
      <div class="grouped fields">
        <label>Ordina per:</label>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>classifica</label>
          </div>
        </div>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>nome</label>
          </div>
        </div>              
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>prezzo (dal piu' basso al piu' alto)</label>
          </div>
        </div>
        <div class="field">
          <div class="ui radio checkbox">
            <input name="prezzo_crescente" type="radio">
            <label>prezzo (dal piu' alto al piu' basso)</label>
          </div>
        </div>
      </div>
    </div>

    </br>
             
    <div id="choices" class="ui form">
      <div class="grouped fields">
        <label>Filtra per tipologia di cucina:</label>
        <div class="field">
          <div class="ui checkbox">
            <input name="tipo_cucina" type="checkbox">
            <label></label>
          </div>
        </div>
      </div>
    </div>

	</br>
	<button class="ui fluid basic black button">Cerca</button>
   </div>
  </div>
    <div class="column">
            <div class="ui buttons">
                <div class="ui button">
                    <i class="left arrow icon"></i>
                </div>
            </div>
            <div class="ui button">                      
                  <i class="right arrow icon"></i>                      
            </div>
                <div class="ui basic label">
                  2,048
            </div>
        </div>
  </div>
    

  </div>
 
  <div class="thirteen wide column">
    <div id="results" class="ui three stackable cards">
      <div class="ui card">
        <div class="image">
          <img src="immagine">
        </div>
        <div class="content">
          <a class="header">Ristorante</a>
          <div class="ui heart rating">
            <i class="heart icon"> </i>
            <i class="heart icon"> </i>
            <i class="heart icon"> </i>
            <i class="heart icon"> </i>
            <i class="heart icon"> </i>
          </div>
          <div class="meta">
            <span class="date">Numero di recensioni</span>
          </div>
          <div class="text">Posizione in classifica</div>
          <div class="description">Tipi di cucina</div>
        </div>
      </div>
    </div>
  </div>
</div>
	</jsp:attribute>
		
</l:main>
