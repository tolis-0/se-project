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

import myy803.project.model.Professor;
import myy803.project.model.Role;
import myy803.project.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProfessorDAOTest {
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Test
	@Order(1)
	public void ProfessorDAO_notNull() {
		System.out.println("TEST 1");
		
		Assertions.assertNotNull(professorDAO);
		Assertions.assertNotNull(userDAO);
		Assertions.assertNotNull(subjectDAO);
	}
	
	@Test
	@Order(2)
	public void ProfessorDAO_save() {
		System.out.println("TEST 2");
		
		User user1 = userDAO.save(new User("apostolos", "password123", Role.PROFESSOR));
		Professor prof1 = professorDAO.save(new Professor(user1, "Apostolos Pappas"));
		User user2 = userDAO.save(new User("patroklos", "321password", Role.PROFESSOR));
		Professor prof2 = professorDAO.save(new Professor(user2, "Patroklos Pappas"));
		User user3 = userDAO.save(new User("hlias", "12password23", Role.PROFESSOR));
		Professor prof3 = professorDAO.save(new Professor(user3, "Hlias Konstantoulas"));
		
		Assertions.assertNotNull(prof1);
		Assertions.assertNotNull(prof2);
		Assertions.assertNotNull(prof3);
		
		Assertions.assertEquals(prof1.getId(), 1);
		Assertions.assertEquals(prof2.getId(), 2);
		Assertions.assertEquals(prof3.getId(), 3);
	}
	
	@Test
	@Order(3)
	public void ProfessorDAO_findById() {
		System.out.println("TEST 3");
		
		User user1 = userDAO.save(new User("stefa", "password", Role.PROFESSOR));
		professorDAO.save(new Professor(user1, "Stefanos Anagnostou"));
		User user2 = userDAO.save(new User("antonis", "wordpass", Role.PROFESSOR));
		professorDAO.save(new Professor(user2, "Antonis Papadopoulos"));
		
		Professor prof1 = professorDAO.findById(2).orElse(null);
		Professor prof2 = professorDAO.findById(3).orElse(null);
		
		Assertions.assertNotNull(prof1);
		Assertions.assertNull(prof2);
		
		Assertions.assertEquals(prof1.getId(), 2);
		Assertions.assertEquals(prof1.getFullName(), "Antonis Papadopoulos");
	}
	
}
