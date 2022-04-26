package com.generalstore.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	@Override
	public void init() throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("GeneralStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();	
		super.init();
	}
	@Override
	public void destroy() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	
}
