package myy803.project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import myy803.project.model.Role;
import myy803.project.model.Student;
import myy803.project.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class StudentDAOTest {

	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Test
	@Order(1)
	public void StudentDAO_notNull() {
		System.out.println("TEST 1");
		
		Assertions.assertNotNull(studentDAO);
		Assertions.assertNotNull(userDAO);
	}
	
	@Test
	@Order(2)
	public void StudentDAO_save() {
		System.out.println("TEST 2");
		
		User user1 = userDAO.save(new User("apostolos", "password123", Role.STUDENT));
		Student stud1 = studentDAO.save(new Student(user1, "Apostolos Pappas"));
		User user2 = userDAO.save(new User("patroklos", "321password", Role.STUDENT));
		Student stud2 = studentDAO.save(new Student(user2, "Patroklos Pappas"));
		User user3 = userDAO.save(new User("hlias", "12password23", Role.STUDENT));
		Student stud3 = studentDAO.save(new Student(user3, "Hlias Konstantoulas"));
		
		Assertions.assertNotNull(stud1);
		Assertions.assertNotNull(stud2);
		Assertions.assertNotNull(stud3);
		
		Assertions.assertEquals(stud1.getId(), 1);
		Assertions.assertEquals(stud2.getId(), 2);
		Assertions.assertEquals(stud3.getId(), 3);
	}
	
	@Test
	@Order(3)
	public void StudentDAO_findById() {
		System.out.println("TEST 3");
		
		User user1 = userDAO.save(new User("stefa", "password", Role.PROFESSOR));
		studentDAO.save(new Student(user1, "Stefanos Anagnostou"));
		User user2 = userDAO.save(new User("antonis", "wordpass", Role.PROFESSOR));
		studentDAO.save(new Student(user2, "Antonis Papadopoulos"));
		
		Student stud1 = studentDAO.findById(2).orElse(null);
		Student stud2 = studentDAO.findById(3).orElse(null);
		
		Assertions.assertNotNull(stud1);
		Assertions.assertNull(stud2);
		
		Assertions.assertEquals(stud1.getId(), 2);
		Assertions.assertEquals(stud1.getFullName(), "Antonis Papadopoulos");
	}
	
}
