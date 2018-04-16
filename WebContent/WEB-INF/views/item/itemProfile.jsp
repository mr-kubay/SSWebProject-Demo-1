<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!--  <link rel="stylesheet" type="text/css" href="css/mainPage.css"> -->
<style type="text/css">
  <%@include file="/css/item.css" %>
</style>
</head>
<body>
	<div>
		<h1>Item</h1>
	</div>
	<div id="mainBlock">
		<c:choose>
			<c:when test="${itemDto != null}">
				<form id="itemForm" action="${base}/itemedit" method="post">
			</c:when>
			<c:otherwise>
				<form id="itemForm" action="${base}/itemcreate" method="post">
			</c:otherwise>
		</c:choose>


		<div id="titleBlock">
			Title : <input type="text" name="title" class="inputItemForm"
				placeholder="Title" value="${itemDto.title}">
		</div>
		<div id="descriptionBlock">
			Description : <input type="text" name="description"
				class="inputItemForm" placeholder="description"
				value="${itemDto.description}">
		</div>
		<c:if test="${not empty errorMessage}">
			<font color="red">${errorMessage}</font>
		</c:if>
		<div id="buttonBlock">
			<input type="submit" value="OK" id="submitButton"> 
			<a href="${base}/useritems" id="createButton">Cancel</a>
		</div>
		</form>

	</div>
	</body>
</html>