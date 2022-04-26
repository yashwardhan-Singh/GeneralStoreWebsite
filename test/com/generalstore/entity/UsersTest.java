package com.generalstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {

	public static void main(String[] args) {
		Users user1 = new Users();
		user1.setEmail("nisit@gmail.com");
		user1.setFullName("kumar.nishit");
		user1.setPassword("PASScode");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GeneralStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("A user is persisted");
	}

}
