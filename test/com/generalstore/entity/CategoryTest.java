package com.generalstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class CategoryTest {

	public static void main(String[] args) {
		Category cat1 = new Category("Code Java");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GeneralStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(cat1);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("A category is persisted");
	}

}
