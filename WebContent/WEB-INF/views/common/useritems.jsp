<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user items</title>
<link rel="stylesheet" type="text/css" href="css/mainPage.css">
</head>
<body>
<header>
	<div id="userPlace">
		<a href="${base}/useredit?login=${userItemsDto.userName}">${userItemsDto.userName} </a>
	</div>
	
</header>

<div id="mainBlock">

<c:choose>
		<c:when test="${!empty userItemsDto.itemsList}">
			<table id="itemsTable">
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th colspan="2"><a href="${base}/itemcreate?login=${userItemsDto.userName}">Add Item</a></th>
				</tr>

				<c:forEach items="${userItemsDto.itemsList}" var="item">
					<tr>
						<td>${item.title}</td>
						<td>${item.description}</td>

						<td><a href="${base}/itemedit?login=${userItemsDto.userName}&item=${item.title}"> edit</a></td>
						<td><a href="${base}/itemdelete?idItem=${item.idItem}"> delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
		<h1>Item's list is empty</h1>
		</c:otherwise>
		</c:choose>
		<br>
		
	</div>
	<c:if test="${not empty errorMessageToUserItems}">
		<font color="red">${errorMessageToUserItems}</font>
	</c:if>
<footer >
    <<div id="outerBlock">
		<a href="${base}/logout">logout</a>
	</div>
</footer>
</body>
</html>