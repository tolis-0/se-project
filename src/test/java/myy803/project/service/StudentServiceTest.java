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
import org.springframework.test.context.TestPropertySource;

import myy803.project.model.Role;
import myy803.project.model.Student;
import myy803.project.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class StudentServiceTest {
	
	@Autowired
	StudentService studentService;

	@Autowired
	UserService userService;
	
	@Test
	@Order(1)
	public void StudentService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(studentService);
		Assertions.assertNotNull(userService);
	}
	
	@Test
	@Order(2)
	public void StudentService_saveStudent() {
		System.out.println("TEST 2");

		User user1 = userService.saveUser(new User("panana", "Password123", Role.STUDENT));
		Student stud1 = studentService.saveStudent(new Student(user1, "Panagiotis Anagnostou"));
		User user2 = userService.saveUser(new User("stefpap", "Password456", Role.STUDENT));
		Student stud2 = studentService.saveStudent(new Student(user2, "Stefanos Pappas"));
		
		Assertions.assertNotNull(stud1);
		Assertions.assertNotNull(stud2);
		
		Assertions.assertEquals(stud1.getId(), 1);
		Assertions.assertEquals(stud2.getId(), 2);
		Assertions.assertEquals(stud1.getFullName(), "Panagiotis Anagnostou");
		Assertions.assertEquals(stud2.getFullName(), "Stefanos Pappas");
	}
	
	@Test
	@Order(3)
	public void StudentService_getStudentById() {
		System.out.println("TEST 3");

		User user1 = userService.saveUser(new User("panana", "Password123", Role.STUDENT));
		studentService.saveStudent(new Student(user1, "Panagiotis Anagnostou"));
		User user2 = userService.saveUser(new User("stefpap", "Password456", Role.STUDENT));
		studentService.saveStudent(new Student(user2, "Stefanos Pappas"));
		
		Student stud2 = studentService.getStudentById(2);
		Student stud3 = studentService.getStudentById(3);
		
		Assertions.assertNotNull(stud2);
		Assertions.assertNull(stud3);
		
		Assertions.assertEquals(stud2.getId(), 2);
		Assertions.assertEquals(stud2.getFullName(), "Stefanos Pappas");
	}
	
	@Test
	@Order(4)
	public void StudentService_getUser() {
		System.out.println("TEST 4");
		
		User user = userService.saveUser(new User("panana", "Password123", Role.STUDENT));
		studentService.saveStudent(new Student(user, "Panagiotis Anagnostou"));
		Student stud = studentService.getStudentById(1);
		
		Assertions.assertNotNull(stud.getUser());
		
		Assertions.assertEquals(stud.getUser().getId(), 1);
		Assertions.assertEquals(stud.getUser().getUsername(), "panana");
		Assertions.assertTrue(stud.getUser().getPassword().startsWith("$2a$10$"));
		Assertions.assertEquals(stud.getUser().getRole(), Role.STUDENT);
	}
	
}
