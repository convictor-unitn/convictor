<%-- 
    Document   : formerrors
    Created on : 21-lug-2016, 16.55.46
    Author     : Giovanni
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>

<%@attribute name="field" required="true" description="error name to display"%>

<c:if test="${bean.valid == false and !(bean.errors[field] == null)}" >
	<div class="ui error message">
		${bean.errors[field]}
	</div>
</c:if>