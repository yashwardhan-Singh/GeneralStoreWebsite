<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align="center">
	<img alt="" src="../images/BookstoreAdminLogo.png">
	<div>
		Welcome , <c:out value="${sessionScope.useremail }" />| 
		<a href="logout">Logout</a> <br />
	</div>
	<div id="headermenu">

		<div >
			<a href="list_users">
			<img src="../images/users.png">Users</a>
		</div>
		<div >
			<a href="list_category">
			<img src="../images/category.png">Categories</a>
		</div>
		<div >
			<a href="list_books">
			<img src="../images/bookstack.png ">Books</a>
		</div>
		<div >
			<a href="customer">
			<img src="../images/customer.png">Customer</a>
		</div>
		<div style="display:table-cell;">
			<a href="orders">
			<img src="../images/order.png">Orders</a>
		</div>
	</div>
</div>