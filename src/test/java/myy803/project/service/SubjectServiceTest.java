package myy803.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
public class SubjectServiceTest {
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	UserService userService;
	
	Professor prof1;
	Professor prof2;
	
	@BeforeEach
	public void initializeTest() {
		User user1 = userService.saveUser(new User("liakpa", "PASSWORD789", Role.PROFESSOR));
		prof1 = professorService.saveProfessor(new Professor(user1, "Hlias Pappas"));
		User user2 = userService.saveUser(new User("daarg", "987PASSWORD", Role.PROFESSOR));
		prof2 = professorService.saveProfessor(new Professor(user2, "Argiris Daoulas"));
	}
	
	@Test
	@Order(1)
	public void ProfessorService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(subjectService);
	}
	
	@Test
	@Order(2)
	public void ProfessorService_saveSubject() {
		System.out.println("TEST 2");
		
		System.out.println("prof1: " + prof1);
		System.out.println("prof2: " + prof2);
		
		Subject subject1 = new Subject(prof1, "Name1", "Objectives ... 1");
		Subject subject2 = new Subject(prof1, "Name1", "Objectives ... 1");
		System.out.println("subject1: " + subject1);
		System.out.println("subject2: " + subject2);
		
		subject1 = subjectService.saveSubject(subject1);
		subject2 = subjectService.saveSubject(subject2);
		
		Assertions.assertNotNull(subject1);
		Assertions.assertNotNull(subject2);
		
		Assertions.assertEquals(subject1.getId(), 1);
		Assertions.assertEquals(subject2.getId(), 2);
	}
}
