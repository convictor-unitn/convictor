<%-- 
    Document   : restaurantForm
    Created on : Jun 26, 2016, 12:10:22 AM
    Author     : umberto
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<c:if test="${empty bean.id || bean.id == 0}">
    <c:set var="url" value="${pageContext.servletContext.contextPath}/restaurants/create" />
</c:if>
<c:if test="${!empty bean.id && bean.id != 0}">
    <c:set var="url" value="${pageContext.servletContext.contextPath}/restaurants/update?id=${bean.id}" />
</c:if>
<form class="ui large form" method="POST" action="${url}">
    <div class="ui tertiary segment">

      <!-- Personal Infos -->

      <div class="ui segment">
        <!-- Name Field -->
        <label>Informazioni Personali</label>
        <div class="ui divider"></div>
        <div class="field">
            <input type="text" name="name" placeholder="Nome" value="${bean.name}" >
        </div>

        <!-- Location Fields -->
        <div class="four fields">
          <div class="field">
              <input type="text" name="street" placeholder="Via" value="${bean.street}">
          </div>
          <div class="field">
              <input type="text" name="city" placeholder="Città" value="${bean.city}">
          </div>
          <div class="field">
              <input type="text" name="zipcode" placeholder="CAP" value="${bean.zipCode}">
          </div>
          <div class="field">
              <input type="text" name="province" placeholder="Provincia" value="${bean.province}">
          </div>
        </div>
        <div class="three fields">
          <div class="field">
              <input type="text" name="email" placeholder="Email" value="${bean.email}">
          </div>
          <div class="field">
              <input type="text" name="phone" placeholder="Telefono" value="${bean.phone}">
          </div>
          <div class="field">
              <input type="text" name="website" placeholder="Pagina Web" value="${bean.website}">
          </div>
        </div>
      </div>
          
          <div class="ui segment">
              <label>Fascia di Prezzo</label>
              <div class="ui divider"></div>
              <div class="five fields">                  
                      <c:forEach var="slotprice" items="${allPriceSlot}">
                         <div class="field">
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
        <div class="field">
          <select multiple="" class="ui dropdown" name="cusines">
              <option value="">Seleziona Cucine</option>
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
        <div class="field">
            <input class="ui textbox" type="text" name="description" rows="2" placeholder="Aggiungi breve descrizione" value="${restaurant.description}"/>
        </div>
      </div>     
      
      <!-- Timing Infos -->	  	 

      <div class="ui segment">

        <!-- Opening Hours -->
        <label>Informazioni Orari</label>
        <div class="ui divider"></div>
		<c:forEach var="optime" items="${bean.openingTimes}">
		<!-- Set two variables to display only hours or only minutes from opening hours of a restaurant -->
			<fmt:formatDate var="openAtHour" value="${optime.openAt}" pattern="HH"/>
			<fmt:formatDate var="openAtMinute" value="${optime.openAt}" pattern="mm"/>
			<fmt:formatDate var="closeAtHour" value="${optime.closeAt}" pattern="HH"/>
			<fmt:formatDate var="closeAtMinute" value="${optime.closeAt}" pattern="mm"/>
			<fmt:formatDate var="openAtAfternoonHour" value="${optime.openAtAfternoon}" pattern="HH"/>
			<fmt:formatDate var="openAtAfternoonMinute" value="${optime.openAtAfternoon}" pattern="mm"/>
			<fmt:formatDate var="closeAtAfternoonHour" value="${optime.closeAtAfternoon}" pattern="HH"/>
			<fmt:formatDate var="closeAtAfternoonMinute" value="${optime.closeAtAfternoon}" pattern="mm"/>
		<div class="ui segment">
            <div class="field">
                <div class="ui small label">
                    ${optime.dayString}
                </div>
            </div>
            <div class="field">Mattina</div>
        <div class="two fields">
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Apertura
              </div>
                <input type="text" name="open_at_${optime.dayString}_hour" placeholder="Ora" value="${openAtHour}">
                <input type="text" name="open_at_${optime.dayString}_minute" placeholder="Minuti" value="${openAtMinute}">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_${optime.dayString}_hour" placeholder="Ora" value="${closeAtHour}">
              <input type="text" name="close_at_${optime.dayString}_minute" placeholder="Minuti" value="${closeAtMinute}">
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
				<input type="text" name="open_at_afternoon_${optime.dayString}_hour" placeholder="Ora" value="${openAtAfternoonHour}">
				<input type="text" name="open_at_afternoon_${optime.dayString}_minute" placeholder="Minuti" value="${openAtAfternoonMinute}">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="close_at_afternoon_${optime.dayString}_hour" placeholder="Ora" ${closeAtAfternoonHour}>
              <input type="text" name="close_at_afternoon_${optime.dayString}_minute" placeholder="Minuti" value="${closeAtAfternoonMinute}">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
            <div class="ui checkbox mycheckbox">
				<c:choose>
					<c:when test="${optime.dayoff == true}">
						<input type="checkbox" name="dayoff_${optime.dayString}" value="checked">
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="dayoff_${optime.dayString}" value="">
					</c:otherwise>
				</c:choose>

					<label >Seleziona come giorno di chiusura</label>
             </div>
        </div>
        </div>
		</c:forEach>
		
	  </div>

      </div>      

      <input id="p_button" class="ui fluid large submit button" type="submit"></input>
    </br>
    <div class="ui fluid submit button">Annulla</div>
  </form>