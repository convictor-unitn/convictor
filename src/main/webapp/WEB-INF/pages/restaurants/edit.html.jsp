<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Giovanni M Riva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="it.unitn.disi.webprog2016.convictor.app.beans.*" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials" %>


<c:set var="restaurant" scope="request" value="${requestScope.Restaurant}" />
<l:main>
	
	<jsp:attribute name="title">Modifica Ristorante</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">
                <div class="ui middle aligned center aligned grid">
                  <div class="column">
                      <c:if test="${!restaurant.isvalid()}">
                        <partials:formerrors/>
                      </c:if>
                    <h2 class="ui header">
                      <div id="s_text" class="content">
                        Modifica Ristorante
                      </div>
                    </h2>
                    <form class="ui large form" method="POST" action="${pageContext.servletContext.contextPath}/restaurants/create">
                      <div class="ui tertiary segment">

                        <!-- Personal Infos -->

                        <div class="ui segment">
                          <!-- Name Field -->
                          <label>Informazioni Personali</label>
                          <div class="ui divider"></div>
                          <div class="field">
                              <input type="text" name="name" placeholder="Nome" value="${restaurant.name}" >
                          </div>

                          <!-- Location Fields -->
                          <div class="four fields">
                            <div class="field">
                                <input type="text" name="street" placeholder="Via" value="${restaurant.street}">
                            </div>
                            <div class="field">
                                <input type="text" name="city" placeholder="Città" value="${restaurant.city}">
                            </div>
                            <div class="field">
                                <input type="text" name="zipcode" placeholder="CAP" value="${restaurant.zipCode}">
                            </div>
                            <div class="field">
                                <input type="text" name="province" placeholder="Provincia" value="${restaurant.province}">
                            </div>
                          </div>
                          <div class="three fields">
                            <div class="field">
                                <input type="text" name="email" placeholder="Email" value="${restaurant.email}">
                            </div>
                            <div class="field">
                                <input type="text" name="phone" placeholder="Telefono" value="${restaurant.phone}">
                            </div>
                            <div class="field">
                                <input type="text" name="website" placeholder="Pagina Web" value="${restaurant.website}">
                            </div>
                          </div>
                        </div>

                        <!-- Cuisines Field -->

                        <div class="ui segment">
                          <label>Tipologia Piatti</label>
                          <div class="ui divider"></div>
                          <div class="field">
                            <select multiple="" class="ui dropdown">
                              <option value="">Seleziona Cucine</option>
                              <option value="CI">Cinese</option>
                              <option value="JP">Giapponese</option>
                              <option value="IT">Italiana</option>
                              <option value="SWE">Svedese</option>
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
                              <textarea rows="2" placeholder="Aggiungi breve descrizione" value="${restaurant.description}">
                              </textarea>
                          </div>
                        </div>

                      </div>
                        <input id="p_button" class="ui fluid large submit button" type="submit"></input>
                      </br>
                      <div class="ui fluid submit button">Annulla</div>
                    </form>
                  </div>
                </div>
            </div>
	</jsp:attribute>
		
</l:main>