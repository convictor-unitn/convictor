<%-- 
    Document   : main
    Created on : May 15, 2016, 10:32:40 AM
    Author     : umberto
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="partials" tagdir="/WEB-INF/tags/partials/" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" %>
<%@attribute name="body" required="true" fragment="true" %>

<!DOCTYPE html>
<html>
    <head>
      <!-- Standard Meta -->
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

      <!-- Site Properties -->
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="dist/semantic.min.css">
    </head>
    <body>
        <partials:topNavbar />
        <jsp:invoke fragment="body" />

        <script src="dist/semantic.min.js"></script>
    </body>
</html>