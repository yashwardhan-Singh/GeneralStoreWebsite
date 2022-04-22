package com.generalstorese.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.generalstore.dao.BookDAO;
import com.generalstore.dao.CategoryDAO;
import com.generalstore.entity.Book;
import com.generalstore.entity.Category;


public class BookServices {
	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listBooks() throws IOException, ServletException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	public void showBookNewForm() throws IOException, ServletException{
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategoty" , listCategory);
		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
		
	}

	public void createBook() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category")); //isko thik krna h
		String title = request.getParameter("title");
		String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=null;
		
		try {
		 publishDate=dateFormat.parse(request.getParameter("publishDate"));
		}catch(ParseException excetion) {
			//excetion.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}
//		System.out.println("Category Id- " + categoryId); iskko thik krna h
		System.out.println("Title- " + title);
		System.out.println("Author- " + author);
		System.out.println("Description:- " + description);
		System.out.println("ISBN- " + isbn);
		System.out.println("Price- " + price);
		System.out.println("Published Date- " + publishDate);
		
		Book book = new Book();
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);
		book.setAuthor(author);
		book.setTitle(title);
		book.setDescription(description);
		book.setCategory(category);
		book.setPublishDate(publishDate);
		book.setIsbn(isbn);
		book.setPrice(price);
		
		Part part=request.getPart("bookImage");
		if(part!=null &&part.getSize()>0) {
			long size=part.getSize();
			byte[] imageBytes=new byte[(int) size];
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}
		Book createdBook = bookDAO.create(book);
		String message = "A new book has been created successfully";
		request.setAttribute("message", message);
		listBooks();
	}
}
