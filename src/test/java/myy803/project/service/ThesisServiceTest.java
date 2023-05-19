package myy803.project.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import myy803.project.model.Application;
import myy803.project.model.Professor;
import myy803.project.model.Role;
import myy803.project.model.Student;
import myy803.project.model.Thesis;
import myy803.project.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ThesisServiceTest {

	@Autowired
	ThesisService thesisService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	@Test
	@Order(1)
	public void ThesisService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(thesisService);
		Assertions.assertNotNull(studentService);
		Assertions.assertNotNull(userService);
		
	}
	
	@Test
	@Order(2)
	public void ThesisService_saveThesis() {
		System.out.println("TEST 2");
		
		User user1 = userService.saveUser(new User("panana", "Password123", Role.STUDENT));
		User user2 = userService.saveUser(new User("stefpap", "Password456", Role.STUDENT));
		
		Student stud1 = studentService.saveStudent(new Student(user1, "Panagiotis Anagnostou"));		
		Student stud2 = studentService.saveStudent(new Student(user2, "Stefanos Pappas"));
		
		Thesis th1 = thesisService.saveThesis(new Thesis(3, stud1));
		Thesis th2 = thesisService.saveThesis(new Thesis(6, stud2));
		
		Assertions.assertNotNull(th1);
		Assertions.assertNotNull(th2);
		
		Assertions.assertEquals(th1.getId(), 3);
		Assertions.assertEquals(th1.getStudent(), stud1);
		Assertions.assertEquals(th2.getId(), 6);
		Assertions.assertEquals(th2.getStudent(), stud2);

	}
	
	@Test
	@Order(3)
	public void ThesisService_getThesisById() {
		System.out.println("TEST 3");
		
		User user1 = userService.saveUser(new User("panana", "Password123", Role.STUDENT));
		User user2 = userService.saveUser(new User("stefpap", "Password456", Role.STUDENT));
		
		Student stud1 = studentService.saveStudent(new Student(user1, "Panagiotis Anagnostou"));		
		Student stud2 = studentService.saveStudent(new Student(user2, "Stefanos Pappas"));
		
		thesisService.saveThesis(new Thesis(3, stud1));
		thesisService.saveThesis(new Thesis(6, stud2));
		
		Thesis th1 = thesisService.getThesisById(3);
		Thesis th2 = thesisService.getThesisById(6);
		
		Assertions.assertNotNull(th1);
		Assertions.assertNotNull(th2);
		
		Assertions.assertEquals(th1.getId(), 3);
		Assertions.assertEquals(th1.getStudent(), stud1);
		Assertions.assertEquals(th2.getId(), 6);
		Assertions.assertEquals(th2.getStudent(), stud2);
		
	}
}