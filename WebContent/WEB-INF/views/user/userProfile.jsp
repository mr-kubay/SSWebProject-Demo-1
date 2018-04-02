<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
<div><h1>User</h1></div>
	<div id="mainBlock">
		<c:choose>
			<c:when test="${userDto != null}">
				<form id="userForm" action="${base}/useredit" method="post">
			</c:when>
			<c:otherwise>
				<form id="userForm" action="${base}/usercreate" method="post">
			</c:otherwise>
		</c:choose>



		<div class="fieldBlock">
			Login : <input type="text" name="login" class="inputUserForm"
				placeholder="Login" value="${userDto.login}">
				 <input
				type="hidden" name="id" value="${userDto.idUser}">
		</div>

		<div class="fieldBlock">
			Email: <input type="text" name="email" class="inputUserForm"
				placeholder="email" value="${userDto.email}">
		</div>

		<div class="fieldBlock">
			Password : <input type="password" name="password"
				class="inputUserForm" placeholder="Password">
		</div>

		<div class="fieldBlock">
			Confirm password : <input type="password" name="confirmpass"
				class="inputUserForm" placeholder="Confirm password">
		</div>

		<c:if test="${!empty errorMessage}">
			<font color="red">${errorMessage}</font>
		</c:if>
		
		<div id="buttonBlock">
			<input type="submit" value="OK" id="submitButton">
			 <c:if test="${userDto != null}" ><a href="${base}/useritems" id="createButton">Cancel</a> </c:if>
			  <c:if test="${userDto == null}" ><a href="${base}/login" id="createButton">Cancel</a> </c:if>
		</div>

		</form>

	</div>
</body>
</html>