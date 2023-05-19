package myy803.project.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import myy803.project.model.Application;
import myy803.project.model.Professor;
import myy803.project.model.Role;
import myy803.project.model.Student;
import myy803.project.model.Subject;
import myy803.project.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ApplicationDAOTest {

	@Autowired
	ApplicationDAO applicationDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
	private Subject subject;
	private Student stud1;
	private Student stud2;
	private Student stud3;
	
	@BeforeEach
	public void initializeTest() {
		User user1 = userDAO.save(new User("apostolos", "password123", Role.STUDENT));
		stud1 = studentDAO.save(new Student(user1, "Apostolos Pappas"));
		stud1.setAverageGrade(8.0f);
		stud1.setRemainingCourses(20);
		User user2 = userDAO.save(new User("patroklos", "321password", Role.STUDENT));
		stud2 = studentDAO.save(new Student(user2, "Patroklos Pappas"));
		stud2.setAverageGrade(7.5f);
		stud2.setRemainingCourses(5);
		User user3 = userDAO.save(new User("hlias", "12password23", Role.STUDENT));
		stud3 = studentDAO.save(new Student(user3, "Hlias Konstantoulas"));
		stud3.setAverageGrade(7.0f);
		stud3.setRemainingCourses(10);
		
		User user4 = userDAO.save(new User("thanos", "34password12", Role.PROFESSOR));
		Professor prof = professorDAO.save(new Professor(user4, "Thanasis Liatifis"));
		prof.setSpecialty("Computer Networks");
		
		subject = subjectDAO.save(new Subject(prof, "Cloud Computing Project", "..."));
	}	
	
	@Test
	public void ApplicationDAO_notNull() {
		Assertions.assertNotNull(applicationDAO);
	}
	
	@Test
	public void ApplicationDAO_save() {
		Application app1 = applicationDAO.save(new Application(subject.getId(), stud1.getId(), "..."));
		Application app2 = applicationDAO.save(new Application(subject.getId(), stud2.getId(), "test2"));
		
		Assertions.assertNotNull(app1);
		Assertions.assertNotNull(app2);
		Assertions.assertEquals(app2.getMessage(), "test2");
	}
	
	@Test
	public void ApplicationDAO_update() {
		applicationDAO.save(new Application(subject.getId(), stud1.getId(), "initial message"));
		Application app = applicationDAO.save(new Application(subject.getId(), stud1.getId(), "after message"));
		
		Assertions.assertEquals(app.getMessage(), "after message");
	}
	
	@Test
	public void ApplicationDAO_getApplicationsForSubject() {
		Application app1 = applicationDAO.save(new Application(subject.getId(), stud1.getId(), "..."));
		Application app2 = applicationDAO.save(new Application(subject.getId(), stud2.getId(), "..."));
		Application app3 = applicationDAO.save(new Application(subject.getId(), stud3.getId(), "..."));
		
		List<Application> applications = applicationDAO.getApplicationsForSubject(subject.getId());
		
		Assertions.assertTrue(applications.contains(app1));
		Assertions.assertTrue(applications.contains(app2));
		Assertions.assertTrue(applications.contains(app3));
	}
	
	@Test
	public void ApplicationDAO_deleteApplicationsOfStudent() {
		Application app1 = applicationDAO.save(new Application(subject.getId(), stud1.getId(), "..."));
		Application app2 = applicationDAO.save(new Application(subject.getId(), stud2.getId(), "..."));
		Application app3 = applicationDAO.save(new Application(subject.getId(), stud3.getId(), "..."));
		
		applicationDAO.deleteApplicationsOfStudent(stud2.getId());
		
		List<Application> applications = applicationDAO.getApplicationsForSubject(subject.getId());
		
		Assertions.assertTrue(applications.contains(app1));
		Assertions.assertFalse(applications.contains(app2));
		Assertions.assertTrue(applications.contains(app3));
	}
	
	@Test
	public void ApplicationDAO_deleteApplicationsForSubject() {
		Application app1 = applicationDAO.save(new Application(subject.getId(), stud1.getId(), "..."));
		Application app2 = applicationDAO.save(new Application(subject.getId(), stud2.getId(), "..."));
		Application app3 = applicationDAO.save(new Application(subject.getId(), stud3.getId(), "..."));
		
		applicationDAO.deleteApplicationsForSubject(subject.getId());
		
		List<Application> applications = applicationDAO.getApplicationsForSubject(subject.getId());
		
		Assertions.assertFalse(applications.contains(app1));
		Assertions.assertFalse(applications.contains(app2));
		Assertions.assertFalse(applications.contains(app3));
	}
}
