<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rural Work Supplies - Online Books/Hand-made Item Store</title>
</head>
<body>
	<jsp:directive.include file = "header.jsp"/>
	<div align="center" >
	<br/><br/>
		
		<h2>New Books:</h2>
		<div style="display: inline-table ; margin: 20px">
		<c:forEach items="${listNewBooks}" var="book">
			<div class="book">
				<div>
					<a href="view_book?id=${book.bookId}">
						<img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
					</a>
				</div>
				<div>
					<a href="view_book?id=${book.bookId}">
						<b>${book.title}</b>
					</a>
				</div>
				<div>
					<jsp:directive.include file="book_rating.jsp" />				
				</div>
				<div><i>by ${book.author}</i></div>
				<div><b>$${book.price}</b></div>
			</div>
			
		</c:forEach>
		</div>
	<div align="center">
		<h2>Best_selling Books:</h2>
		</div>
		<div align="center">
		<h2>Most-favored Books:</h2>
		</div>
	<br/><br/>
	</div>
	
	<jsp:directive.include file = "footer.jsp"/>
</body>
</html>