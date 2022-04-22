<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GeneralStore Administration</title>
<link rel="stylesheet"
href="../css/style.css"></head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>

	<div align="center">
		<hr width="50%" />
		<h2 class="pageheading">Quick Actions:</h2>
		<b> <a href="create_book">New Book</a> <a href="create_user">New
				User</a> <a href="create_category">New Category</a> <a
			href="create_customer">New Customer</a>
		</b>
	</div>
	<div align="center">
		<hr width="50%" />

		<h2 class="pageheading">Recent Sales</h2>
	</div>

	<div align="center">
		<hr width="50%" />
		<h2 class="pageheading">Recent Reviews</h2>
	</div>

	<div align="center">
		<hr width="50%" />
		<h2 class="pageheading">Statistics</h2>
	</div>
	<hr width="50%"/>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>