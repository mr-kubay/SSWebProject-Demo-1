<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
 
<style type="text/css">
  <%@include file="/css/login.css" %>
</style>
</head>
<body>
<div id="mainBlock">

	<form id="loginForm" action = "${base}/login" method = "post">

		<div id="loginBlock">
			Login : 
			<input type="text" name="login" class="inputLoginForm" placeholder="Enter your login">
		</div>
		<div id="passBlock">
			Password :
			<input type="password" name="password" class="inputLoginForm" placeholder="Enter your password">
		</div>
		 <br>
	<c:if test="${not empty errorMessage}">
		<font color="red">${errorMessage}</font>
	</c:if>
		<div id="buttonBlock">
			<input type="submit" name = "login" value="Sign in" id="submitButton">
			<a href="${base}/usercreate">Register</a>
		</div>
	</form>

</div>

</body>
</html>