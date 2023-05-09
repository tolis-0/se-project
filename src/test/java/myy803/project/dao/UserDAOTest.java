package myy803.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import myy803.project.model.User;
import myy803.project.model.Role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserDAOTest {

	@Autowired
	UserDAO userDAO;
	
	@Test
	public void UserDAOSave() {
		User user = new User("tolis0", "password", Role.ADMIN);
		
		User savedUser = userDAO.save(user);
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals(savedUser.getId(), 4);
	}
	
	@Test
	public void UserDAOMultipleSaves() {
		User user1 = new User("tolis0", "password1", Role.ADMIN);
		User user2 = new User("paaaat", "password2", Role.PROFESSOR);
		User user3 = new User("liakos", "password3", Role.STUDENT);
		
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
	
}
