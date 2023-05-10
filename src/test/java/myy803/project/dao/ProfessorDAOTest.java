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
import myy803.project.model.Subject;
import myy803.project.model.User;;

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
	
	/*@Test
	@Order(4)
	public void ProfessorModel_getUser() {
		System.out.println("TEST 4");
		
		User user = userDAO.save(new User("antkar", "goodPassword", Role.PROFESSOR));
		professorDAO.save(new Professor(user, "Antreas Karatzas"));
		Professor prof = professorDAO.getReferenceById(1);
		
		Assertions.assertNotNull(prof.getUser());
		
		Assertions.assertEquals(prof.getUser().getId(), 1);
		Assertions.assertEquals(prof.getUser().getUsername(), "antkar");
		Assertions.assertEquals(prof.getUser().getPassword(), "goodPassword");
		Assertions.assertEquals(prof.getUser().getRole(), Role.PROFESSOR);
	}
	
	@Test
	@Order(5)
	public void ProfessorModel_getSubjects() {
		System.out.println("TEST 5");
		
		User user1 = userDAO.save(new User("makan", "veryGoodPassword", Role.PROFESSOR));
		Professor prof1 = professorDAO.save(new Professor(user1, "Marios Kanatas"));
		User user2 = userDAO.save(new User("valiat", "greatPassword", Role.PROFESSOR));
		Professor prof2 = professorDAO.save(new Professor(user2, "Vasilis Liatifis"));
		
		subjectDAO.save(new Subject(prof1, "Compilers", "Designing the Cimple programming language."));
		subjectDAO.save(new Subject(prof2, "Networking I", "An Introduction to Basic Networking Concepts and Principles."));
		subjectDAO.save(new Subject(prof1, "Computer Architecture", "Simulating multi-level cache hierarchy and branch detection."));
		subjectDAO.save(new Subject(prof2, "Networking II", "Inventing the Internet."));
		
		prof1 = professorDAO.getReferenceById(1);
		prof2 = professorDAO.getReferenceById(2);
		
		Assertions.assertNotNull(prof1.getSubjectList());
		Assertions.assertNotNull(prof2.getSubjectList());
		
		Assertions.assertEquals(prof1.getSubjectList().get(1).getName(), "Compilers");
		Assertions.assertEquals(prof1.getSubjectList().get(2).getName(), "Computer Architecture");
		Assertions.assertEquals(prof2.getSubjectList().get(1).getName(), "Networking I");
		Assertions.assertEquals(prof2.getSubjectList().get(2).getName(), "Networking II");
	}*/
}
