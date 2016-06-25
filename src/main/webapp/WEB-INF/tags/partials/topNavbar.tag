<%-- 
    Document   : topNavbar
    Created on : May 15, 2016, 10:46:40 AM
    Author     : Giovanni M Riva
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!--<img class="logo" src="assets/images/logo.png">-->
        Convictor
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <div class="ui search mini item">
        <div class="ui icon input">
          <input class = "prompt" type="text" placeholder="Search...">
          <i class="search icon"></i>
        </div>
      </div>

      <!-- Right -->
      <!-- Notificazion Dropdown -->
      <div class="ui simple dropdown right item">
        Notifications <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
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
        <a class="item" href="${pageContext.request.contextPath}/userProfile/show">My Restaurant(s)</a>
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
      <a href="#" class="brand item">
        <img class="logo" src="#">
        Logo
      </a>

      <!-- Center -->
      <!-- Search Bar -->
      <div class="ui search mini item">
        <div class="ui icon input">
          <input class = "prompt" type="text" placeholder="Search...">
          <i class="search icon"></i>
        </div>
      </div>
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
        <div class="text">Notifications </div>
        <div class="menu">
          <a class="item" href="#">notify 1</a>
          <a class="item" href="#">notify 2</a>
          <a class="item" href="${pageContext.request.contextPath}/userProfile/show">Open</a> 
        </div>
      </div>

      <!-- Registered User -->
      <div class="item">
        <a href="${pageContext.request.contextPath}/user_profile/show">Name Surname </a> 
        <div class="menu">
          <a class="item" href="#">Profile</a>
          <a class="item" href="#">Log Out</a>
        </div>
      </div>

      <!-- Owner User -->
      <div class="item">
        <a href="${pageContext.request.contextPath}/user_profile/show">My Restaurant(s)</a>
      </div>
      <c:if test="${loggedUser == null}" >
                
      <!-- Anonymous User -->
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
