package myy803.project.dao;

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
import myy803.project.model.Thesis;
import myy803.project.model.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ThesisDAOTest {

	@Autowired
	ThesisDAO thesisDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	private Thesis thesis;
	
	@BeforeEach
	public void initializeTest() {
		User user1 = userDAO.save(new User("apostolos", "password123", Role.STUDENT));
		Student stud1 = studentDAO.save(new Student(user1, "Apostolos Pappas"));
		stud1.setAverageGrade(8.0f);
		stud1.setRemainingCourses(20);
		User user2 = userDAO.save(new User("patroklos", "321password", Role.STUDENT));
		Student stud2 = studentDAO.save(new Student(user2, "Patroklos Pappas"));
		stud2.setAverageGrade(7.5f);
		stud2.setRemainingCourses(5);
		User user3 = userDAO.save(new User("hlias", "12password23", Role.STUDENT));
		Student stud3 = studentDAO.save(new Student(user3, "Hlias Konstantoulas"));
		stud3.setAverageGrade(7.0f);
		stud3.setRemainingCourses(10);
		
		User user4 = userDAO.save(new User("thanos", "34password12", Role.PROFESSOR));
		Professor prof = professorDAO.save(new Professor(user4, "Thanasis Liatifis"));
		prof.setSpecialty("Computer Networks");
		
		Subject subject = subjectDAO.save(new Subject(prof, "Cloud Computing Project", "..."));
		
		applicationDAO.save(new Application(stud1.getId(), subject.getId(), "..."));
		applicationDAO.save(new Application(stud2.getId(), subject.getId(), "..."));
		applicationDAO.save(new Application(stud3.getId(), subject.getId(), "..."));
		
		thesis = thesisDAO.save(new Thesis(subject.getId(), stud2));
	}
	
	@Test
	public void ThesisDAO_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(thesisDAO);
	}
	
	@Test
	public void ThesisDAO_save() {
		Assertions.assertNotNull(thesis);
		Assertions.assertEquals(thesis.getId(), 1);
		Assertions.assertEquals(thesis.getStudent().getId(), 2);
	}
	
	@Test
	public void ThesisDAO_updateGrades() {
		thesis.setImplementationGrade(5);
		thesis.setPresentationGrade(6);
		thesis.setReportGrade(7);
		
		Thesis updatedThesis = thesisDAO.save(thesis);
		
		Assertions.assertNotNull(updatedThesis);
		Assertions.assertEquals(updatedThesis.getImplementationGrade(), 5);
		Assertions.assertEquals(updatedThesis.getPresentationGrade(), 6);
		Assertions.assertEquals(updatedThesis.getReportGrade(), 7);
	}
	
	@Test
	public void ThesisDAO_getStudentThesis() {
		thesis.setImplementationGrade(10);
		thesisDAO.save(thesis);
		
		Thesis gotThesis = thesisDAO.getStudentThesis(2);
		Thesis gotThesisAnother = thesisDAO.getStudentThesis(1);
		
		Assertions.assertNotNull(gotThesis);
		Assertions.assertNull(gotThesisAnother);
		
		Assertions.assertTrue(gotThesis.equals(thesis));
	}
	
	@Test
	public void ThesisDAO_getSubjectThesis() {
		thesis.setImplementationGrade(10);
		thesisDAO.save(thesis);
		
		Thesis gotThesis = thesisDAO.getSubjectThesis(1);
		Thesis gotThesisAnother = thesisDAO.getSubjectThesis(2);
		
		Assertions.assertNotNull(gotThesis);
		Assertions.assertNull(gotThesisAnother);
		Assertions.assertTrue(gotThesis.equals(thesis));
	}
	
}
