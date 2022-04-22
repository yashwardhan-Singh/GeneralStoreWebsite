package com.generalstore.controller.admin.book;

import com.generalstore.controller.BaseServlet;
import com.generalstorese.service.BookServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/create_book")
public class CreateBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    
    public CreateBookServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	BookServices bookServices = new BookServices(entityManager, request, response);
	bookServices.createBook();
	}

}