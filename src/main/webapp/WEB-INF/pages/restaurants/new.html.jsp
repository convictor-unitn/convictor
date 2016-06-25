<%-- 
    Document   : index
    Created on : May 15, 2016, 10:32:00 AM
    Author     : Giovanni M Riva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>
<l:main>
	
	<jsp:attribute name="title">Nuovo Ristorante</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">
                <div class="ui middle aligned center aligned grid">
                  <div class="column">
                    <h2 class="ui header">
                      <div id="s_text" class="content">
                        Aggiungi Ristorante
                      </div>
                    </h2>
                    <form class="ui large form">
                      <div class="ui tertiary segment">

                        <!-- Personal Infos -->

                        <div class="ui segment">
                          <!-- Name Field -->
                          <label>Informazioni Personali</label>
                          <div class="ui divider"></div>
                          <div class="field">
                            <input type="text" name="name" placeholder="Nome">
                          </div>

                          <!-- Location Fields -->
                          <div class="four fields">
                            <div class="field">
                              <input type="text" name="street" placeholder="Via">
                            </div>
                            <div class="field">
                              <input type="text" name="city" placeholder="Città">
                            </div>
                            <div class="field">
                              <input type="text" name="zipcode" placeholder="CAP">
                            </div>
                            <div class="field">
                              <input type="text" name="province" placeholder="Provincia">
                            </div>
                          </div>
                          <div class="three fields">
                            <div class="field">
                              <input type="text" name="email" placeholder="Email">
                            </div>
                            <div class="field">
                              <input type="text" name="phone" placeholder="Telefono">
                            </div>
                            <div class="field">
                              <input type="text" name="website" placeholder="Pagina Web">
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
                            <select multiple="" class="ui dropdown">
                              <option value="">Giorni Apertura</option>
                              <option value="CI">Luned</option>
                              <option value="JP">Giapponese</option>
                              <option value="IT">Italiana</option>
                              <option value="SWE">Svedese</option>
                            </select>
                          </div>
                        </div>

                        <!-- Description Field -->

                        <div class="ui segment">
                          <label>Descrizione</label>
                          <div class="ui divider"></div>
                          <div class="field">
                            <textarea rows="2" placeholder="Aggiungi breve descrizione"></textarea>
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