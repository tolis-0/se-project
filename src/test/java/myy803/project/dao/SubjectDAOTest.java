package myy803.project.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import myy803.project.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SubjectDAOTest {

	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	Professor prof1;
	Professor prof2;
	Professor prof3; 
	
	@BeforeEach
	public void initializeTest() {
		User user1 = userDAO.save(new User("apostolos", "password123", Role.PROFESSOR));
		prof1 = professorDAO.save(new Professor(user1, "Apostolos Pappas"));
		User user2 = userDAO.save(new User("patroklos", "321password", Role.PROFESSOR));
		prof2 = professorDAO.save(new Professor(user2, "Patroklos Pappas"));
		User user3 = userDAO.save(new User("hlias", "12password23", Role.PROFESSOR));
		prof3 = professorDAO.save(new Professor(user3, "Hlias Konstantoulas"));
	}
	
	@Test
	@Order(1)
	public void SubjectDAO_notNull() {
		System.out.println("TEST 1");

		Assertions.assertNotNull(professorDAO);
		Assertions.assertNotNull(userDAO);
		Assertions.assertNotNull(subjectDAO);
	}
	
	@Test
	@Order(2)
	public void SubjectDAO_save() {
		System.out.println("TEST 2");
		
		Subject subject1 = new Subject(prof1, "Compilers", "Designing the Cimple programming language.");
		Subject subject2 = new Subject(prof1, "Computer Architecture", "Simulating multi-level cache hierarchy and branch detection.");
		
		System.out.println("Subject 1 (before save): " + subject1);
		System.out.println("Subject 2 (before save): " + subject2);
		
		subject1 = subjectDAO.save(subject1);
		System.out.println("Subject 1 (after save): " + subject1);
		subject2 = subjectDAO.save(subject2);
		System.out.println("Subject 2 (after save): " + subject2);
		
		Assertions.assertNotNull(subject1);
		Assertions.assertNotNull(subject2);
		
		Assertions.assertEquals(subject1.getId(), 1);
		Assertions.assertEquals(subject2.getId(), 2);
	}
	
	@Test
	@Order(3)
	public void SubjectDAO_findById() {
		System.out.println("TEST 3");
		
		subjectDAO.save(new Subject(prof1, "Compilers", "Designing the Cimple programming language."));
		subjectDAO.save( new Subject(prof1, "Computer Architecture", "Simulating multi-level cache hierarchy and branch detection."));
		
		Subject subject1 = subjectDAO.findById(2).orElse(null);
		Subject subject2 = subjectDAO.findById(3).orElse(null);
		
		Assertions.assertNotNull(subject1);
		Assertions.assertNull(subject2);
		
		Assertions.assertEquals(subject1.getId(), 2);
		Assertions.assertEquals(subject1.getName(), "Computer Architecture");
		Assertions.assertEquals(subject1.getObjectives(), "Simulating multi-level cache hierarchy and branch detection.");
	}
	
	@Test
	@Order(4)
	public void SubjectDAO_deleteById() {
		System.out.println("TEST 4");
		
		subjectDAO.save(new Subject(prof1, "Networking I", "An Introduction to Basic Networking Concepts and Principles."));
		subjectDAO.save(new Subject(prof1, "Networking II", "Inventing the Internet."));
		
		subjectDAO.deleteById(1);
		
		Subject subject1 = subjectDAO.findById(1).orElse(null);
		Subject subject2 = subjectDAO.findById(2).orElse(null);
		
		Assertions.assertNull(subject1);
		Assertions.assertNotNull(subject2);
	}
	
	@Test
	@Order(5)
	public void SubjectDAO_getAllAvailableSubjects() {
		Subject s1 = subjectDAO.save(new Subject(prof1, "Networking I", "An Introduction to Basic Networking Concepts and Principles."));
		Subject subject = new Subject(prof1, "Networking II", "Inventing the Internet.");
		subject.assign();
		Subject s2 = subjectDAO.save(subject);
		Subject s3 = subjectDAO.save(new Subject(prof2, "Compilers", "Designing the Cimple programming language."));
		Subject s4 = subjectDAO.save(new Subject(prof2, "Computer Architecture", "Simulating multi-level cache hierarchy and branch detection."));
		
		subjectDAO.deleteById(4);
		
		List<Subject> subjectList = subjectDAO.getAllAvailableSubjects();
		
		Assertions.assertTrue(subjectList.contains(s1));
		Assertions.assertFalse(subjectList.contains(s2));
		Assertions.assertTrue(subjectList.contains(s3));
		Assertions.assertFalse(subjectList.contains(s4));
	}
	
}
