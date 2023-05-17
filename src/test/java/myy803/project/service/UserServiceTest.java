package myy803.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import myy803.project.model.Role;
import myy803.project.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Test
	@Order(1)
	public void UserService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(userService);
	}
	
	@Test
	@Order(2)
	public void UserService_saveUser() {
		System.out.println("TEST 2");

		User user1 = userService.saveUser(new User("antkar", "goodPassword", Role.STUDENT));
		User user2 = userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		
		Assertions.assertNotNull(user1);
		Assertions.assertNotNull(user2);
		
		Assertions.assertEquals(user1.getId(), 1);
		Assertions.assertEquals(user2.getId(), 2);
		Assertions.assertEquals(user1.getUsername(), "antkar");
		Assertions.assertEquals(user2.getUsername(), "patkon");
		Assertions.assertTrue(user1.getPassword().startsWith("$2a$10$"));
		Assertions.assertTrue(user2.getPassword().startsWith("$2a$10$"));
		Assertions.assertNotEquals(user1.getPassword(), user2.getPassword());
		Assertions.assertEquals(user1.getRole(), Role.STUDENT);
		Assertions.assertEquals(user2.getRole(), Role.PROFESSOR);
	}
	
	@Test
	@Order(3)
	public void UserService_isUserPresent() {
		System.out.println("TEST 3");
		
		User user = userService.saveUser(new User("antkar", "goodPassword", Role.STUDENT));
		userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		
		Assertions.assertTrue(userService.isUserPresent(user));
		Assertions.assertTrue(userService.isUserPresent(new User("patkon", "doesn't matter", Role.STUDENT)));
		user.setUsername("other");
		Assertions.assertFalse(userService.isUserPresent(user));
		Assertions.assertFalse(userService.isUserPresent(new User("different", "betterPassword", Role.PROFESSOR)));
	}
	
	@Test
	@Order(4)
	public void UserService_loadUserByUsername() {
		System.out.println("TEST 4");
		
		userService.saveUser(new User("antkar", "goodPassword", Role.STUDENT));
		userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		
		UserDetails user1 = userServiceImpl.loadUserByUsername("antkar");
		UserDetails user2 = userServiceImpl.loadUserByUsername("patkon");
		
		Assertions.assertNotNull(user1);
		Assertions.assertNotNull(user2);
		
		Assertions.assertThrows( UsernameNotFoundException.class,
				() -> userServiceImpl.loadUserByUsername("other") );
	}
	
	@Test
	@Order(5)
	public void UserService_getUserById() {
		System.out.println("TEST 5");
		
		userService.saveUser(new User("antkar", "goodPassword", Role.STUDENT));
		userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		
		User user1 = userService.getUserById(1);
		User user2 = userService.getUserById(2);
		
		Assertions.assertNotNull(user1);
		Assertions.assertNotNull(user2);
		
		Assertions.assertEquals(user1.getUsername(), "antkar");
		Assertions.assertEquals(user2.getUsername(), "patkon");
	}
	
	@Test
	@Order(6)
	public void UserService_changePassword() {
		System.out.println("TEST 6");
		
		userService.saveUser(new User("antkar", "goodPassword", Role.STUDENT));
		userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		
		User user1 = userService.getUserById(1);
		String password1Before = user1.getPassword();
		User user2 = userService.getUserById(2);
		String password2Before = user2.getPassword();
		
		userService.changePassword(user2, "evenBetterPassword");
		
		User user1after = userService.getUserById(1);
		String password1After = user1after.getPassword();
		User user2after = userService.getUserById(2);
		String password2After = user2after.getPassword();
		
		Assertions.assertEquals(password1Before, password1After);
		Assertions.assertNotEquals(password2Before, password2After);
	}
	
}
