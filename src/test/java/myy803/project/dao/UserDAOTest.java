package myy803.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import myy803.project.model.User;
import myy803.project.model.Role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserDAOTest {

	@Autowired
	UserDAO userDAO;
	
	@Test
	@Order(1)
	public void UserDAO_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(userDAO);
	}
	
	@Test
	@Order(2)
	public void UserDAO_save() {
		System.out.println("TEST 2");
		
		User user1 = new User("tolis0", "strongPassword1", Role.ADMIN);
		User user2 = new User("paaaat", "strongPassword2", Role.PROFESSOR);
		User user3 = new User("liakos", "strongPassword3", Role.STUDENT);
		
		User savedUser1 = userDAO.save(user1);
		User savedUser2 = userDAO.save(user2);
		User savedUser3 = userDAO.save(user3);
		
		Assertions.assertNotNull(savedUser1);
		Assertions.assertNotNull(savedUser2);
		Assertions.assertNotNull(savedUser3);

		Assertions.assertEquals(savedUser1.getId(), 1);
		Assertions.assertEquals(savedUser2.getId(), 2);
		Assertions.assertEquals(savedUser3.getId(), 3);
	}
	
	@Test
	@Order(3)
	public void UserDAO_findByUsername() {
		System.out.println("TEST 3");
		
		userDAO.save(new User("tolis0", "strongPassword1", Role.ADMIN));
		userDAO.save(new User("paaaat", "strongPassword2", Role.PROFESSOR));
		userDAO.save(new User("liakos", "strongPassword3", Role.STUDENT));
		
		User user1 = userDAO.findByUsername("tolis0").orElse(null);
		User user2 = userDAO.findByUsername("giorgos").orElse(null);
		
		Assertions.assertNotNull(user1);
		Assertions.assertNull(user2);
		Assertions.assertEquals(user1.getId(), 1);
		Assertions.assertEquals(user1.getRole().getValue(), "Admin");
		Assertions.assertEquals(user1.getPassword(), "strongPassword1");
	}
	
	@Test
	@Order(4)
	public void UserDAO_changePassword() {
		System.out.println("TEST 4");
		
		userDAO.save(new User("giorgos", "somePassword", Role.PROFESSOR));
		userDAO.save(new User("tolis0", "veryWeakPassword", Role.ADMIN));
		
		User user = userDAO.findByUsername("tolis0").orElse(null);
		Assertions.assertNotNull(user);
		Assertions.assertEquals(user.getId(), 2);
		
		user.setPassword("veryStrongPassword");
		User changedUser = userDAO.save(user);
		
		Assertions.assertEquals(changedUser.getPassword(), "veryStrongPassword");
	}
	
	@Test
	@Order(5)
	public void UserDAO_emptyFields() {
		System.out.println("TEST 5");
		
		Assertions.assertThrows( DataIntegrityViolationException.class,
			() -> userDAO.save(new User("marios", "password", null)) );
		Assertions.assertThrows( DataIntegrityViolationException.class,
				() -> userDAO.save(new User("marios", null, Role.STUDENT)) );
		Assertions.assertThrows( DataIntegrityViolationException.class,
				() -> userDAO.save(new User(null, "password", Role.STUDENT)) );
	}
	
}
