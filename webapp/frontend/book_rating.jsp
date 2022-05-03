<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forTokens items="${book.ratingStars}" delims="," var="star">
	<c:if test="${star eq 'on'}">
		<img src="images/rating_on.png" />
	</c:if>
	<c:if test="${star eq 'off'}">
		<img src="images/rating_off.png" />
	</c:if>
	<c:if test="${star eq 'half'}">
		<img src="images/rating_half.png" />
	</c:if>
</c:forTokens>

</body>
</html>