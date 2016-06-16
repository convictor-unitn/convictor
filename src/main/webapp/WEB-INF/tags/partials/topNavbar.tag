<%-- 
    Document   : topNavbar
    Created on : May 15, 2016, 10:46:40 AM
    Author     : umberto
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

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
        <img class="logo" src="assets/images/logo.png">
        Project Name
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
          <a class="item" href="#">Open</a>
        </div>
      </div>
      
      <!-- Registered User -->
      <div class="ui simple dropdown item">
        Name Surname <i class="dropdown icon"></i>
        <div class="menu">
          <a class="item" href="#">Profile</a>
          <a class="item" href="#">Log Out</a>
        </div>
      </div>
      
      <!-- Owner User -->
      <a href="#" class="item">
        My Restaurant(s)
      </a>
      
      <!-- Anonymous User -->
      <div class="item">
        <div class="ui primary button right">Sign up</div>
      </div>
      <div class="item">
        <div class="ui button right">Log-in</div>
      </div>
    </div>
  </div>


  <!-- Mobile View Menu -->

  <div class="mobile only row">
    <div class="ui borderless fixed inverted navbar menu">

      <!-- Left -->
      <!-- Logo  -->
      <a href="#" class="brand item">
        <img class="logo" src="assets/images/logo.png">
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
          <a class="item" href="#">Open</a>
        </div>
      </div>

      <!-- Registered User -->
      <div class="item">
        <div class="ui text item">Name Surname </div>
        <div class="menu">
          <a class="item" href="#">Profile</a>
          <a class="item" href="#">Log Out</a>
        </div>
      </div>

      <!-- Owner User -->
      <a href="#" class="item">
        My Restaurant(s)
      </a>

      <!-- Anonymous User -->
      <div class="item">
        <div class="ui primary button right">Sign up</div>
      </div>
      <div class="item">
        <div class="ui button right">Log-in</div>
      </div>
    </div>

  </div>
</div>
