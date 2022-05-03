package com.generalstorese.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generalstore.dao.BookDAO;
import com.generalstore.dao.CategoryDAO;
import com.generalstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();

	}
	public void listCategory() throws IOException, ServletException {
	listCategory(null);
	}

	public void listCategory(String message) throws IOException, ServletException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if (message != null) {
			request.setAttribute("message", listCategory);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void createCategory() throws IOException, ServletException {

		String name = request.getParameter("name");

		Category existCat = categoryDAO.findByName(name);

		if (existCat != null) {
			String message = "Could not create Category. The Category. "+name+" already exits.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Category newcat = new Category(name);
			categoryDAO.create(newcat);
			String message ="A new Category is added"; 
			listCategory(message);

		}
	}
	public void editCategory() throws IOException, ServletException{
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		request.setAttribute("category", category);
		
		String editPage = "category_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		}
	
	public void updateCategory() throws IOException, ServletException{
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update category." + 
		"A category name" + categoryName + "already exists.";
			request.setAttribute(message, message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}
	}
	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		String message;
		
//		Category category = categoryDAO.get(categoryId);
		
//		if (category == null) {
//			message = "Could not find category with ID " + categoryId
//					+ ", or it might have been deleted";
//			showMessageBackend(message, request, response);
//			return;
//		}
		
		BookDAO bookDAO = new BookDAO();
		long numberOfBooks = bookDAO.countByCategory(categoryId);
				
		if (numberOfBooks > 0) {
			message = "Could not delete the category (ID: %d) because it currently contains some books.";
			message = String.format(message, numberOfBooks);
		} else {
			categoryDAO.delete(categoryId);		
			message = "The category with ID " + categoryId + " has been removed successfully.";
		}
		
		listCategory(message);
		
	}
}
