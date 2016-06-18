<%-- 
    Document   : restaurant_profile.html
    Created on : 14-giu-2016, 15.29.02
    Author     : Giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layouts/" %>


<l:main>
	
	<jsp:attribute name="title">{Restaurant's Name} Profile Page</jsp:attribute>
	
	<jsp:attribute name="body">
            <div class="ui container">

                <!-- Restaurant's Name -->
                <div class="ui center aligned grid">
                  <div class="column">
                    <div class="ui header large">
                      Trattoria Milanese
                    </div>
                  </div>
                </div>

                <!-- Restaurant Image -->
                <div class="ui  grid">
                  <div class="column">
                    <img class="ui rounded centered bordered image" src="images/restaurant.jpg"/>
                  </div>
                </div>

                <div class="ui center aligned five column stackable grid">
                  <div class="column">
                    <div class="meta">
                      <span>Via Roma, 100</span>
                      <span>1010</span>
                      <span>Milano</span>
                    </div>
                  </div>
                  <div class="column">
                    <div class="meta">trattoria@milanese.me</div>
                  </div>
                  <div class="column">
                    <div class="meta">0123 456789</div>
                  </div>
                </div>
                <!-- Rating Infos & Reviews Infos & QR Code -->
                <div class="ui center aligned middle aligned five column stackable grid">
                  <div class="column">
                    <div class="row">
                      <div class="ui small statistic">
                        <div class="value">
                          5
                        </div>
                        <div class="label">
                          position in Lombardia
                        </div>
                      </div>
                    </div>
                    <div class="ui statistic">
                      <div class="ui huge center rating" data-rating="1" data-max-rating="5"></div>
                    </div>
                  </div>
                  <div class="column">
                    <img class="ui small centered image" src="images/default_qrcode.png"/>
                  </div>
                </div>


                <!-- Restaurant Infos -->
                <div class="ui center aligned six column stackable grid">
                  <div class="row">
                    <div class="column">
                      <div clas="ui grid">
                        <div class="column">
                          <div class="ui sub header">Cucina</div>
                        </div>
                        <div class="ui divider"></div>
                        <div class="column">
                          <div class="ui list">
                            <div class="item">
                              Cinese
                            </div>
                            <div class="item">
                              Giapponese
                            </div>
                            <div class="item">
                              Indiano
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="column">
                      <div clas="ui center aligned grid">
                        <div class="column">
                          <div class="ui sub header">Orari di apertura</div>
                        </div>
                        <div class="ui divider"></div>
                        <div class="column">
                          <div class="ui list">
                            <div class="item">
                              7.30 - 12.30
                            </div>
                            <div class="item">
                              18.30 - 1.30
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="column">
                      <div clas="ui center aligned grid">
                        <div class="column">
                          <div class="ui sub header">Fascia di prezzo</div>
                        </div>
                        <div class="ui divider"></div>
                        <div class="column">
                          <div class="ui list">
                            <div class="item">
                              7.50 - 15.00
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="column">
                      <div clas="ui center aligned grid">
                        <div class="column">
                          <div class="ui sub header">Giorno di chiusura</div>
                        </div>
                        <div class="ui divider"></div>
                        <div class="column">
                          <div class="ui list">
                            <div class="item">
                              LUN
                            </div>
                            <div class="item">
                              MER
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="ui divider"></div>
                </div>

                <!-- Show Reviews/Map Buttons -->
                <div class="ui stackable center aligned three columns grid ">

                  <div class="row">
                    <div class="column">
                      <div class="ui three item tabular menu">
                        <a class="item active" data-tab="recensioni">Recensioni</a>
                        <a class="item " data-tab="mappa">Mappa</a>
                        <a class="item" data-tab="reclama">Reclama</a>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="ui stackable grid">
                  <div class="column">
                    <!-- Recensioni Tab -->
                    <div class="ui tab active" data-tab="recensioni">
                      <div class="ui grid">
                        <div class="row">
                          <div class="column">

                            <!-- Reviews List -->
                            <div class="ui comments">
                              <div class="comment">
                                <a class="avatar">
                                  <img src="/images/avatar/small/stevie.jpg">
                                </a>
                                <div class="content">
                                  <a class="author">Stevie Feliciano</a>
                                  <div class="metadata">
                                    <div class="date">2 days ago</div>
                                    <div class="rating">
                                      <i class="star icon"></i>
                                      5 Faves
                                    </div>
                                  </div>
                                  <div class="text">
                                    Hey guys, I hope this example comment is helping you read this documentation.
                                  </div>
                                  <div class="actions">
                                    <a class="reply">Reply</a>
                                  </div>
                                  <form class="ui reply form">
                                    <div class="field">
                                      <textarea></textarea>
                                    </div>
                                    <div class="ui basic submit labeled icon button">
                                      <i class="icon edit"></i> Add Reply
                                    </div>
                                  </form>
                                </div>
                              </div>
                              <div class="comment">
                                <a class="avatar">
                                  <img src="/images/avatar/small/stevie.jpg">
                                </a>
                                <div class="content">
                                  <a class="author">Mario Rossi</a>
                                  <div class="metadata">
                                    <div class="date">2 days ago</div>
                                    <div class="rating">
                                      <i class="star icon"></i>
                                      1 Faves
                                    </div>
                                  </div>
                                  <div class="text">
                                    Hey guys, this place sucks
                                  </div>
                                  <div class="actions">
                                    <a class="reply">Reply</a>
                                  </div>
                                  <form class="ui reply form">
                                    <div class="field">
                                      <textarea></textarea>
                                    </div>
                                    <div class="ui basic submit labeled icon button">
                                      <i class="icon edit"></i> Add Reply
                                    </div>
                                  </form>
                                </div>
                              </div>
                              <div class="comment">
                                <a class="avatar">
                                  <img src="/images/avatar/small/stevie.jpg">
                                </a>
                                <div class="content">
                                  <a class="author">Luca Bianchi</a>
                                  <div class="metadata">
                                    <div class="date">2 days ago</div>
                                    <div class="rating">
                                      <i class="star icon"></i>
                                      2 Faves
                                    </div>
                                  </div>
                                  <div class="text">
                                    Brilliant Rest
                                  </div>
                                  <div class="actions">
                                    <a class="reply">Reply</a>
                                  </div>
                                  <form class="ui reply form">
                                    <div class="field">
                                      <textarea></textarea>
                                    </div>
                                    <div class="ui basic submit labeled icon button">
                                      <i class="icon edit"></i> Add Reply
                                    </div>
                                  </form>
                                </div>
                              </div>
                            </div>
                          </div>
                          <!-- End Reviews List -->
                        </div>

                        <!-- Add a review textbox -->
                        <div class="sixteen wide column">
                          <div class="ui center aligned grid">
                            <div class="column">
                              <div class="ui segment">
                                <div class="ui header">Write a review</div>
                                <div class="ui large center rating" data-rating="1" data-max-rating="5"></div>
                                <div class="ui center comment">
                                  <form class="ui small reply form">
                                    <div class="field">
                                      <textarea></textarea>
                                    </div>
                                    <div class="ui basic submit labeled icon button">
                                      <i class="icon edit"></i> Add new review
                                    </div>
                                  </form>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- End Recensioni Tab -->
                    <!-- Mappa Tab -->
                    <div class="ui tab" data-tab="mappa">
                      <div class="ui center aligned grid">
                        <div class="column">
                          Mappa
                        </div>
                      </div>
                    </div>
                    <!-- End Mappa Tab -->
                    <!-- Reclama Tab -->
                    <div class="ui tab" data-tab="reclama">
                      <div class="ui center aligned grid">
                        <div class="column">
                          <button class="ui button">Conferma Reclamo</button>
                        </div>
                      </div>
                    </div>
                    <!-- End Reclama Tab -->
                  </div>
                </div>
              </div>


	</jsp:attribute>
                
                
		
</l:main>

