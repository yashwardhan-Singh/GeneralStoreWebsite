package com.generalstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.generalstore.entity.Users;

public class UserDAOTest{
	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO = new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("Bakery@gamil.com");
		user1.setFullName("Red Velvet");
		user1.setPassword("700091");

		user1 = userDAO.create(user1);
		

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {

		Users user1 = new Users();
		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("youtube.net");
		user.setFullName("Nishit Kumar S C Bhagwagar");
		user.setPassword("newPassworD");

		user = userDAO.update(user);
		String expected = "newPassworD";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	@Test
	public void testCheckLoginSuccess() {
		String email = "jha7549@gmail.com";
		String password = "miss you";
		boolean loginResult =userDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}

	@Test
	public void testCheckLoginFailed() {
		String email = "nam@gmail.com";
		String password = "II miss you";
		boolean loginResult =userDAO.checkLogin(email, password);
		assertFalse(loginResult);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		assertNull(user);

	}
	@Test
	public void testDeleteUsers() {
		Integer userId = 5;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 55;
		userDAO.delete(userId);
		
	}
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size() > 0);
	}
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertEquals(6, totalUsers);
	}
	@Test
	public void testFindByEmail() {
		String email = "Monaisha.jha7549@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
}
