package com.generalstore.controller.admin.category;

import com.generalstore.controller.BaseServlet;
import com.generalstorese.service.CategoryServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/delete_category")
public class DeleteCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   
    public DeleteCategoryServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	CategoryServices categoryServices = new CategoryServices(entityManager, request, response);
	categoryServices.deleteCategory();
	}

	

}
