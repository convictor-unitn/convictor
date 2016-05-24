<%-- 
    Document   : topNavbar
    Created on : May 15, 2016, 10:46:40 AM
    Author     : umberto
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>


<%-- any content can be specified here e.g.: --%>

<div class="ui stackable borderless menu">

    <!-- Left -->
    <!-- Logo  -->
    <a href="#" class="header item">
      <img class="logo" src="assets/images/logo.png">
      Project Name
    </a>

    <!-- Center -->
    <!-- Search Bar -->
      <div class="ui search small item">
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
