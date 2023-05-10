package myy803.project.service;

import org.junit.jupiter.api.TestMethodOrder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import myy803.project.model.Professor;
import myy803.project.model.Role;
import myy803.project.model.Subject;
import myy803.project.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProfessorServiceTest {
	
	@Autowired
	ProfessorService professorService;

	@Autowired
	UserService userService;
	
	@Autowired
	SubjectService subjectService;
	
	@Test
	@Order(1)
	public void ProfessorService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(professorService);
	}
	
	@Test
	@Order(2)
	public void ProfessorService_saveProfessor() {
		System.out.println("TEST 2");

		User user1 = userService.saveUser(new User("antkar", "goodPassword", Role.PROFESSOR));
		Professor prof1 = professorService.saveProfessor(new Professor(user1, "Antreas Karatzas"));
		User user2 = userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		Professor prof2 = professorService.saveProfessor(new Professor(user2, "Patroklos Konstantoulas"));
		
		Assertions.assertNotNull(prof1);
		Assertions.assertNotNull(prof2);
		
		Assertions.assertEquals(prof1.getId(), 1);
		Assertions.assertEquals(prof2.getId(), 2);
		Assertions.assertEquals(prof1.getFullName(), "Antreas Karatzas");
		Assertions.assertEquals(prof2.getFullName(), "Patroklos Konstantoulas");
	}
	
	@Test
	@Order(3)
	public void ProfessorService_getProfessorById() {
		System.out.println("TEST 3");

		User user1 = userService.saveUser(new User("antkar", "goodPassword", Role.PROFESSOR));
		professorService.saveProfessor(new Professor(user1, "Antreas Karatzas"));
		User user2 = userService.saveUser(new User("patkon", "betterPassword", Role.PROFESSOR));
		professorService.saveProfessor(new Professor(user2, "Patroklos Konstantoulas"));
		
		Professor prof2 = professorService.getProfessorById(2);
		Professor prof3 = professorService.getProfessorById(3);
		
		Assertions.assertNotNull(prof2);
		Assertions.assertNull(prof3);
		
		Assertions.assertEquals(prof2.getId(), 2);
		Assertions.assertEquals(prof2.getFullName(), "Patroklos Konstantoulas");
	}
	
	@Test
	@Order(4)
	public void ProfessorService_getUser() {
		System.out.println("TEST 4");
		
		User user = userService.saveUser(new User("antkar", "goodPassword", Role.PROFESSOR));
		professorService.saveProfessor(new Professor(user, "Antreas Karatzas"));
		Professor prof = professorService.getProfessorById(1);
		
		Assertions.assertNotNull(prof.getUser());
		
		Assertions.assertEquals(prof.getUser().getId(), 1);
		Assertions.assertEquals(prof.getUser().getUsername(), "antkar");
		Assertions.assertTrue(prof.getUser().getPassword().startsWith("$2a$10$"));
		Assertions.assertEquals(prof.getUser().getRole(), Role.PROFESSOR);
	}

	@Test
	@Order(5)
	public void ProfessorService_getSubjects() {
		System.out.println("TEST 5");
		
		User user1 = userService.saveUser(new User("makan", "veryGoodPassword", Role.PROFESSOR));
		Professor prof1 = professorService.saveProfessor(new Professor(user1, "Marios Kanatas"));
		User user2 = userService.saveUser(new User("valiat", "greatPassword", Role.PROFESSOR));
		Professor prof2 = professorService.saveProfessor(new Professor(user2, "Vasilis Liatifis"));
		
		subjectService.saveSubject(new Subject(prof1, "Compilers", "Designing the Cimple programming language."));
		subjectService.saveSubject(new Subject(prof2, "Networking I", "An Introduction to Basic Networking Concepts and Principles."));
		subjectService.saveSubject(new Subject(prof1, "Computer Architecture", "Simulating multi-level cache hierarchy and branch detection."));
		subjectService.saveSubject(new Subject(prof2, "Networking II", "Inventing the Internet."));
		
		prof1 = professorService.getProfessorById(1);
		prof2 = professorService.getProfessorById(2);
		
		Assertions.assertNotNull(prof1.getSubjectList());
		Assertions.assertNotNull(prof2.getSubjectList());
		
		Assertions.assertEquals(prof1.getSubjectList().get(1).getName(), "Compilers");
		Assertions.assertEquals(prof1.getSubjectList().get(2).getName(), "Computer Architecture");
		Assertions.assertEquals(prof2.getSubjectList().get(1).getName(), "Networking I");
		Assertions.assertEquals(prof2.getSubjectList().get(2).getName(), "Networking II");
	}
	
}
