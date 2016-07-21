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
        <img class="ui avatar image" src="${pageContext.servletContext.contextPath}/images/logo_menu.png">
        <span>Convictor</span>
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <!-- Check whether current url is on home page to hide mini search bar in menu -->
      <c:set value="landingPage.html.jsp" var="root" />
      <c:if test="${not fn:endsWith(pageContext.request.requestURI, root)}">
        <div class="ui search mini item">
          <form class="ui icon input" method="GET" action="${pageContext.request.contextPath}/restaurants" >               
            <input class = "prompt" type="text" placeholder="Search..." name="query">
            <i class="search icon"></i>
          </form>
        </div>
      </c:if>

      <!-- Right -->
      <!-- Owner User -->
      <c:if test="${loggedUser.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner'}" >
        <!-- Notificazion Dropdown -->
      <div class="ui simple dropdown right item">
        Notifications <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
        </div>
      </div>
      <!-- Restaurants Dropdown -->
      <div class="ui simple dropdown item">
        I miei ristoranti <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Lista completa</a>
        </div>
      </div>
      </c:if>
      
      <!-- Administrator User -->
      <c:if test="${loggedUser.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.Administrator'}" >
        <div class="ui simple dropdown right item">
            Notifications <i class="dropdown icon"></i>
            <div class="menu">
              <a class="item" href="#">notify 1</a>
              <a class="item" href="#">notify 2</a>
              <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
            </div>
        </div>
      </c:if>
      
	  <!-- 
		Questo è un tweak per settare il right di semantic: se sono un utente registrato setto una variabile
		che stampo nelle classi CSS
	  -->
	  <c:if test="${loggedUser.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.User'}">
		  <c:set var="setRight" value="right" scope="request" />
	  </c:if>
	
	  <c:out value="" />
	  
      <!-- Registered User -->
      <c:if test="${loggedUser != null}" >
      <div class="ui simple dropdown ${setRight} item">
        ${loggedUser.fullName} <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Pagina Profilo</a> 
          <a class="item" href="${pageContext.request.contextPath}/sign_out">Log Out</a>
        </div>
      </div>
      </c:if>
      
    <c:if test="${loggedUser == null}" >
          
      <!-- Anonymous User -->
      <div class="right item">
         
        <a class="ui basic button inverted" style="margin-right: 3%" href="${pageContext.request.contextPath}/sign_in">Accedi</a>
      <!--</div>
      <div class="item">-->
        <a class="ui basic button inverted " href="${pageContext.request.contextPath}/sign_up">Registrati</a>
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
        <img class="logo" src="${pageContext.request.contextPath}/images/logo_menu.png">
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <c:set value="landingPage.html.jsp" var="root" />
      <c:if test="${not fn:endsWith(pageContext.request.requestURI, root)}">
        <div class="ui search mini item">
          <form class="ui icon input"method="POST" action="${pageContext.request.contextPath}/restaurants/index">
            <input class = "prompt" type="text" placeholder="Search..." name="name" >
            <i class="search icon"></i>
          
      </form>
            </div>
      </c:if>
      <div class="right menu open">
        <a href="" class="menu item">
          <i class="content icon"></i>
        </a>
      </div>
    </div>


    <div class="ui vertical navbar menu">
        
      <!-- Owner User -->
      <c:if test="${loggedUser.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner'}" >
        <!-- Notificazion Dropdown -->
      <div class="item">
        <div class="text">Notifications</div>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
        </div>
      </div>
      <!-- Restaurants Dropdown -->
      <div class="item">
        <div class="text">I miei ristoranti</div>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Lista completa</a>
        </div>
      </div>
      </c:if>
      
      <!-- Administrator User -->
      <c:if test="${loggedUser.getClass().name == 'it.unitn.disi.webprog2016.convictor.app.beans.Administrator'}" >
        <div class="ui item">
            <div class="text">Notifications</div> 
            <div class="menu">
              <a class="item" href="#">notify 1</a>
              <a class="item" href="#">notify 2</a>
              <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
            </div>
        </div>
      </c:if>
      
      <!-- Registered User -->
      <c:if test="${loggedUser != null}" >
      <div class="ui item">
        <div class="text">${loggedUser.fullName}</div>
        <div class="menu">
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Pagina Profilo</a> 
          <a class="item" href="${pageContext.request.contextPath}/sign_out">Log Out</a>
        </div>
      </div>  
      </c:if> 
        
      <c:if test="${loggedUser == null}" >          
        <!-- Anonymous User -->
        <div class="item">
          <a class="ui basic fluid black button" href="${pageContext.request.contextPath}/sign_in">Accedi</a>
        </div>
        <div class="item">
            <a class="ui basic fluid black button" href="${pageContext.request.contextPath}/sign_up">Registrati</a>
        </div>
      </c:if>
      
    </div>
    

  </div>
</div>
