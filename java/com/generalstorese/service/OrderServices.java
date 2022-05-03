package com.generalstorese.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.generalstore.controller.frontend.shoppingcart.ShoppingCart;
import com.generalstore.dao.OrderDAO;
import com.generalstore.entity.Book;
import com.generalstore.entity.BookOrder;
import com.generalstore.entity.Customer;
import com.generalstore.entity.OrderDetail;

public class OrderServices {
	private OrderDAO orderDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDao = new OrderDAO();
	}

	public void listAllOrder() throws ServletException, IOException {
		listAllOrder(null);
	}

	public void listAllOrder(String message) throws ServletException, IOException {
		List<BookOrder> listOrder = orderDao.listAll();
		request.setAttribute("listOrder", listOrder);

		String listPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));

		BookOrder order = orderDao.get(orderId);
		request.setAttribute("order", order);
		String listPage = "order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void showCheckoutForm() throws ServletException, IOException {
		String listPage = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);		
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		String shippingAddress = address + ", " + city + ", " + zipcode + ", " + country;
		
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress );
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();
		
		Iterator<Book> iterator = items.keySet().iterator();
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal(shoppingCart.getTotalAmount());
		
		orderDao.create(order);
		
		shoppingCart.clear();
		
		String message = "Thank you. Your order has been received."
				+ "We will deliver your books within a few days.";
		request.setAttribute("message", message);
		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);		}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		List<BookOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId());
		
		request.setAttribute("listOrders", listOrders);
		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);	
	}

	public void showOrderDetailForCustoemer() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		BookOrder order = orderDao.get(orderId, customer.getCustomerId());
		request.setAttribute("order", order);
		String listPage = "frontend/order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);		
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));		
		BookOrder order = orderDao.get(orderId);
		request.setAttribute("order", order);
//		if (order == null) {
//			String message = "Could not find order with ID " + orderId;
//			RequestDispatcher dispatcher = request.getRequestDispatcher(message);
//			dispatcher.forward(request, response);				
//			return;
//		}
//		
//		HttpSession session = request.getSession();
//		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
//		
//		if (isPendingBook == null) {			
//			session.setAttribute("order", order);
//		} else {
//			session.removeAttribute("NewBookPendingToAddToOrder");
//		}
		String listPage = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

}
