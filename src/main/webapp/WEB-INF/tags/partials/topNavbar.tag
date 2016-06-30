<%-- 
    Document   : topNavbar
    Created on : May 15, 2016, 10:46:40 AM
    Author     : Giovanni M Riva
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="loggedUser" value="${sessionScope.user}" />

<%-- The list of normal or fragment attributes can be specified here: --%>



<%-- topNavbar is a JSTL tag that represents the top level menu which shows specific
     options based on which type of user is requesting the web-page
--%>

<div class="ui grid">
  <div class="computer tablet only row">
    <div class="ui borderless inverted fixed menu navbar page ">

      <!-- Left -->
      <!-- Logo  -->
      <a href="${pageContext.request.contextPath}/" class="brand item">
        <img class="ui avatar image" src="${pageContext.servletContext.contextPath}/images/logo.png">
        <span>Convictor</span>
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <!-- Check whether current url is on home page to hide mini search bar in menu -->
      <c:set value="landingPage.html.jsp" var="root" />
      <c:if test="${not fn:endsWith(pageContext.request.requestURI, root)}">
        <div class="ui search mini item">
          <form class="ui icon input" method="POST" action="${pageContext.request.contextPath}/restaurants/index" >               
            <input class = "prompt" type="text" placeholder="Search..." name="name">
            <i class="search icon"></i>
          </form>
        </div>
      </c:if>

      <!-- Right -->
      <!-- Notificazion Dropdown -->
      <div class="ui simple dropdown right item">
        Notifiche <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Leggi</a> 
        </div>
      </div>
      
      <!-- Registered User -->
      <div class="ui simple dropdown item">
        ${loggedUser.fullName} <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Pagina Profilo</a> 
          <a class="item" href="${pageContext.request.contextPath}/sign_out">Log Out</a>
        </div>
      </div>
      
      <!-- Owner User -->
      <c:if test="${user.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner'}" >
        <a class="item" href="${pageContext.request.contextPath}/userProfile/show">I miei Ristoranti</a>
      </c:if>
      <c:if test="${user == null}" >
          
      <!-- Anonymous User -->
      <div class="item">
        <a class="ui basic button inverted right" href="${pageContext.request.contextPath}/sign_up">Registrati</a>
      </div>
      <div class="item">
        <a class="ui basic button inverted right" href="${pageContext.request.contextPath}/sign_in">Accedi</a>
      </div>
      </c:if>
    </div>
  </div>


  <!-- Mobile View Menu -->

  <div class="mobile only row">
    <div class="ui borderless fixed inverted navbar menu">

      <!-- Left -->
      <!-- Logo  -->
      <a href="${pageContext.request.contextPath}/" class="brand item">
        <!--<img class="logo" src="assets/images/logo.png">-->
        Convictor
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <div class="ui search mini item">
        <form class="ui icon input"method="POST" action="${pageContext.request.contextPath}/restaurants/index">
          <input class = "prompt" type="text" placeholder="Search..." name="name" >
          <i class="search icon"></i>
        </div>
      </form>
      <div class="right menu open">
        <a href="" class="menu item">
          <i class="content icon"></i>
        </a>
      </div>
    </div>


    <div class="ui vertical navbar menu">

      <!-- Right -->
      <!-- Notification Dropdown -->
      <div class="ui item">
        <div class="text">Notifiche</div>
        <div class="menu">
           <a class="item" href="#">notifica 1</a>
          <a class="item" href="#">notifica 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Leggi</a>
        </div>
      </div>

      <!-- Registered User -->
      <div class="item">
        <a href="${pageContext.request.contextPath}/userProfile/show">${loggedUser.fullName}</a> 
        <div class="menu">
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Pagina Profilo</a> 
          <a class="item" href="${pageContext.request.contextPath}/sign_out">Log Out</a>
        </div>
        </div>

      <!-- Owner User -->
      <div class="item">
        <a href="${pageContext.request.contextPath}/userProfile/show">I Miei Ristoranti</a>
      </div>
      
      <!-- Anonymous User -->
      <c:if test="${loggedUser == null}" >                     
      <div class="item">
        <a class="ui basic button right" href="${pageContext.request.contextPath}/sign_up">Registrati</a>
      </div>
      <div class="item">
          <a class="ui basic button right" href="${pageContext.request.contextPath}/sign_in">Accedi</a>
      </div>
      </c:if>
    </div>
    

  </div>
</div>
