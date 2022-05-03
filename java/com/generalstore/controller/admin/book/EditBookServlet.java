package com.generalstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generalstorese.service.BookServices;

@WebServlet("/admin/edit_book")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public EditBookServlet() {
    }

	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

    			BookServices bookServices = new BookServices(request, response);
		bookServices.editBook();
	}

}
