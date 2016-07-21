<%-- 
    Document   : restaurantForm
    Created on : Jun 26, 2016, 12:10:22 AM
    Author     : umberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<c:if test="${empty bean.id || bean.id == 0}">
    <c:set var="url" value="${pageContext.servletContext.contextPath}/restaurants/create" />
</c:if>
<c:if test="${!empty bean.id && bean.id != 0}">
    <c:set var="url" value="${pageContext.servletContext.contextPath}/restaurants/update?id=${bean.id}" />
</c:if>
<form class="ui large form error" method="POST" action="${url}">
    <div class="ui tertiary segment">		
		<!-- Personal Infos -->

      <div class="ui segment">
        <!-- Name Field -->
        <label>Informazioni Personali</label>
        <div class="ui divider"></div>
        <div class="field <c:if test="${bean.valid == false and !( bean.errors['name'] == null)}" >error</c:if>">
            <input type="text" name="name" placeholder="Nome" value="${bean.name}" >
			<partials:formerrors field="name"/>
        </div>

        <!-- Location Fields -->
        <div class="four fields">
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['street'] == null)}" >error</c:if>">
              <input type="text" name="street" placeholder="Via" value="${bean.street}">
			<partials:formerrors field="street"/>
          </div>
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['city'] == null)}" >error</c:if>">
              <input type="text" name="city" placeholder="Città" value="${bean.city}">
			<partials:formerrors field="city"/>			  
		  </div>
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['zip'] == null)}" >error</c:if>">
              <input type="text" name="zipcode" placeholder="CAP" value="${bean.zipCode}">
			  <partials:formerrors field="zip"/>          
		  </div>
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['province'] == null)}" >error</c:if>">
              <input type="text" name="province" placeholder="Provincia" value="${bean.province}">
			  <partials:formerrors field="province"/>
		  </div>
        </div>
        <div class="three fields">
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['email'] == null)}" >error</c:if>">
              <input type="text" name="email" placeholder="Email" value="${bean.email}">
              <partials:formerrors field="email"/>
		  </div>
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['phone'] == null)}" >error</c:if>">
              <input type="text" name="phone" placeholder="Telefono" value="${bean.phone}">
			  <partials:formerrors field="phone"/>
		  </div>
          <div class="field <c:if test="${bean.valid == false and !( bean.errors['website'] == null)}" >error</c:if>">
              <input type="text" name="website" placeholder="Pagina Web" value="${bean.website}">
			  <partials:formerrors field="website"/>
		  </div>
        </div>
      </div>
          
          <div class="ui segment">
              <label>Fascia di Prezzo</label>
              <div class="ui divider"></div>
			  <partials:formerrors field="slotPrice"/>
              <div class="five fields">                  
                      <c:forEach var="slotprice" items="${allPriceSlot}">
                         <div class="field <c:if test="${bean.valid == false and !( bean.errors['slotPrice'] == null)}" >error</c:if>">
                             <div class="ui radio checkbox">
                      <!-- If on edit page -->                  
                      <c:choose>    
                          <c:when test="${!empty bean.id && bean.id != 0}">
                              <!--finds which slot price belongs to restaurant already-->                            
                              <c:choose>
                                  <c:when test="${slotprice.slot == bean.slotPrice}">
                                      <input type="radio" name="priceslotselected" checked="checked" value="${slotprice.slot}">
                                      <label>${slotprice.name}</label>
                                  </c:when>
                                  <c:otherwise>
                                      <input type="radio" name="priceslotselected" value="${slotprice.slot}">
                                      <label>${slotprice.name}</label>
                                  </c:otherwise>
                              </c:choose>                                                                                
                          </c:when>
                        <c:otherwise>
                            <input type="radio" name="priceslotselected" value="${slotprice.slot}">
                            <label>${slotprice.name}</label>
                        </c:otherwise>
                      </c:choose> 
                         </div>
                      </div>
                  </c:forEach>                                                     
              </div>
          </div>

      <!-- Cuisines Field -->
      <div class="ui segment">
        <label>Tipologia Piatti</label>
        <div class="ui divider"></div>
		<partials:formerrors field="cusines"/>
        <div class="field <c:if test="${bean.valid == false and !( bean.errors['cusines'] == null)}" >error</c:if>">
          <select multiple="" class="ui dropdown" name="cusines">
              <option value="">Seleziona Cucine</option>
		  <c:out value="${bean.id}"/>
              <c:forEach var="cusine" items="${allCusines}">
                  <!-- If on edit page -->                  
                  <c:choose>    
                      <c:when test="${!empty bean.id && bean.id != 0}">
                          <!--finds which cusines belongs to restaurant already-->
                          <c:set var="alreadyChosen" value="false" />
                            <c:forEach var="restCusine" items="${bean.cusine}">
                                <c:if test="${cusine.id == restCusine.id}">
                                    <option  value="${cusine.id}" selected>${cusine.name}</option>
                                    <c:set var="alreadyChosen" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${alreadyChosen != 'true' }">
                                <option  value="${cusine.id}" >${cusine.name}</option>
                            </c:if>
                      </c:when>
                    <c:otherwise>
                        <option  value="${cusine.id}" >${cusine.name}</option>
                    </c:otherwise>
                  </c:choose>   
              </c:forEach>
          </select>
        </div>
      </div>
      
      <!-- Description Field -->

      <div class="ui segment">
        <label>Descrizione</label>
        <div class="ui divider"></div>
        <div class="field <c:if test="${bean.valid == false and !( bean.errors['description'] == null)}" >error</c:if>">
            <input class="ui textbox" type="text" name="description" rows="2" placeholder="Aggiungi breve descrizione" value="${restaurant.description}"/>
			<partials:formerrors field="description"/>		
		</div>
      </div>     
      
      <!-- Timing Infos -->

      <div class="ui segment">

        <!-- Opening Hours -->
        <label>Informazioni Orari</label>
        <div class="ui divider"></div>

        <div class="ui segment">
            <div class="field">
                <div class="ui small label">
                    Lunedi'
                </div>
            </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
                <input type="text" name="open_at_monday" placeholder="Ora">
                <input type="text" name="openH" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_monday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_monday" placeholder="Ora">
              <input type="text" name="openH" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_monday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
            <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_monday">
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>

        <div class="ui segment">
            <div class="field">
                <div class="ui small label">
                    Martedi'
                </div>
            </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_tuesday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_tuesday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_tuesday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_tuesday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
            <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_monday">
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
        <div class="ui segment">
            <div class="ui small label">
                    Mercoledi'
                </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_wednesday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_wednesday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_wednesday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_wednesday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_wednesday" >
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
        <div class="ui segment">
            <div class="ui small label">
                    Giovedi'
                </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_thursday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_thursday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_thursday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_thursday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_thursday" >
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
        <div class="ui segment">
            <div class="ui small label">
                    Venerdi'
                </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_friday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_friday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_friday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_friday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_friday"  >
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
        <div class="ui segment">
            <div class="ui small label">
                    Sabato
                </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_saturday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_saturday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_saturday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_saturday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_saturday"  >
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
        <div class="ui segment">
            <div class="ui small label">
                    Domenica
                </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_sunday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_sunday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <div class="field">Pomeriggio</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
              <input type="text" name="open_at_afternoon_sunday" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_sunday" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox mycheckbox">
                <input type="checkbox" name="dayoff_sunday" >
                <label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>

      </div>      

    </div>
      <input id="p_button" class="ui fluid large submit button" type="submit"></input>
    </br>
    <div class="ui fluid submit button">Annull)a</div>
  </form>