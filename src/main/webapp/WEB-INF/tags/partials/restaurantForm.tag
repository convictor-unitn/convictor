<%-- 
    Document   : restaurantForm
    Created on : Jun 26, 2016, 12:10:22 AM
    Author     : umberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

      <!-- Cuisines Field -->

      <div class="ui segment">
        <label>Tipologia Piatti</label>
        <div class="ui divider"></div>
        <div class="field">
          <select multiple="" class="ui dropdown" name="cusines">
              <option value="">Seleziona Cucine</option>
              <c:forEach var="cusine" items="${allCusines}">
                  <option  value="${cusine.id}">${cusine.name}</option>
              </c:forEach>
          </select>
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
                <input type="text" name="openH" placeholder="Ora">
                <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
            <div class="ui checkbox">
                <input type="checkbox" tabindex="0" class="hidden">
                <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
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
              <input type="text" name="openH" placeholder="Ora">
              <input type="text" name="openM" placeholder="Minuti">
            </div>
          </div>

          <!-- Closing Hours -->
          <div class="field">
            <div class="ui left labeled input">
              <div class="ui basic label">
                Chiusura
              </div>
              <input type="text" name="closeH" placeholder="Ora">
              <input type="text" name="closeM" placeholder="Minuti">
            </div>
          </div>
        </div>

        <!-- Days Opening -->
        <div class="field">
          <div class="ui checkbox">
            <input type="checkbox" tabindex="0" class="hidden">
            <label>Seleziona come giorno di chiusura</label>
          </div>
        </div>
        </div>

      </div>

      <!-- Description Field -->

      <div class="ui segment">
        <label>Descrizione</label>
        <div class="ui divider"></div>
        <div class="field">
            <input type="text" name="description" rows="2" placeholder="Aggiungi breve descrizione" value="${restaurant.description}"/>
        </div>
      </div>

    </div>
      <input id="p_button" class="ui fluid large submit button" type="submit"></input>
    </br>
    <div class="ui fluid submit button">Annulla</div>
  </form>