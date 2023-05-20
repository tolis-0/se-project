package myy803.project.service;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import myy803.project.dto.SelectApplicationDTO.SelectStrategy;
import myy803.project.model.Application;
import myy803.project.model.Professor;
import myy803.project.model.Role;
import myy803.project.model.Student;
import myy803.project.model.Subject;
import myy803.project.model.Thesis;
import myy803.project.model.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ThesisServiceTest {

	@Autowired
	ThesisService thesisService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	ApplicationService applicationService;
	
	private Subject subject;
	private Student stud1;
	private Student stud2;
	private Student stud3;
	
	@BeforeEach
	public void initializeTest() {
		User user1 = userService.saveUser(new User("apostolos", "password123", Role.STUDENT));
		stud1 = new Student(user1, "Apostolos Pappas");
		stud1.setAverageGrade(8.0f);
		stud1.setRemainingCourses(20);
		stud1 = studentService.saveStudent(stud1);
		
		User user2 = userService.saveUser(new User("patroklos", "321password", Role.STUDENT));
		stud2 = new Student(user2, "Patroklos Pappas");
		stud2.setAverageGrade(7.5f);
		stud2.setRemainingCourses(5);
		stud2 = studentService.saveStudent(stud2);
		
		User user3 = userService.saveUser(new User("hlias", "12password23", Role.STUDENT));
		stud3 = new Student(user3, "Hlias Konstantoulas");
		stud3.setAverageGrade(7.0f);
		stud3.setRemainingCourses(10);
		stud3 = studentService.saveStudent(stud3);
		
		User user4 = userService.saveUser(new User("thanos", "34password12", Role.PROFESSOR));
		Professor prof = new Professor(user4, "Thanasis Liatifis");
		prof.setSpecialty("Computer Networks");
		professorService.saveProfessor(prof);
		
		subject = subjectService.saveSubject(new Subject(prof, "Cloud Computing Project", "..."));
		
		applicationService.saveApplication(new Application(subject.getId(), stud1.getId(), "..."));
		applicationService.saveApplication(new Application(subject.getId(), stud2.getId(), "..."));
		applicationService.saveApplication(new Application(subject.getId(), stud3.getId(), "..."));
	}
	
	
	@Test
	public void ThesisService_notNull() {
		Assertions.assertNotNull(thesisService);
		Assertions.assertNotNull(studentService);
		Assertions.assertNotNull(userService);
		Assertions.assertNotNull(applicationService);
		Assertions.assertNotNull(professorService);
		Assertions.assertNotNull(subjectService);
	}
	
	@Test
	public void ThesisService_saveThesis() {
		Thesis thesis = thesisService.saveThesis(new Thesis(subject.getId(), stud2));
		
		Assertions.assertNotNull(thesis);
		Assertions.assertEquals(thesis.getId(), subject.getId());
		Assertions.assertEquals(thesis.getStudent().getId(), stud2.getId());
	}
	
	@Test
	public void ThesisService_saveNewThesis() {
		Assertions.assertFalse(subject.isAssigned());
		
		thesisService.saveNewThesis(new Thesis(subject.getId(), stud3));
		Subject savedSubject = subjectService.getSubjectById(subject.getId());
		
		Assertions.assertTrue(savedSubject.isAssigned());
	}
	
	@Test
	public void ThesisService_getThesisById() {
		thesisService.saveThesis(new Thesis(subject.getId(), stud1));
		
		Thesis thesis = thesisService.getThesisById(subject.getId());
		
		Assertions.assertNotNull(thesis);
		Assertions.assertEquals(thesis.getId(), subject.getId());
		Assertions.assertEquals(thesis.getStudent().getId(), stud1.getId());
	}
	
	@Test
	public void ThesisService_getStudentThesis() {
		thesisService.saveThesis(new Thesis(subject.getId(), stud3));
		
		Thesis thesis = thesisService.getStudentThesis(stud3.getId());
		
		Assertions.assertNotNull(thesis);
		Assertions.assertEquals(thesis.getId(), subject.getId());
		Assertions.assertEquals(thesis.getStudent().getId(), stud3.getId());
	}
	
	@Test
	public void ThesisService_setGrades() throws Exception {
		Thesis thesis = thesisService.saveThesis(new Thesis(subject.getId(), stud2));
		
		Thesis updatedThesis = thesisService.setGrades(thesis.getId(), 8.0f, 6.5f, 7.0f);
		
		Assertions.assertNotNull(updatedThesis);
		Assertions.assertEquals(updatedThesis.getImplementationGrade(), 8.0f);
		Assertions.assertEquals(updatedThesis.getReportGrade(), 6.5f);
		Assertions.assertEquals(updatedThesis.getPresentationGrade(), 7.0f);
	}
	
	@Test
	public void ThesisService_invalidGrades() throws Exception {
		Thesis thesis = thesisService.saveThesis(new Thesis(subject.getId(), stud2));
		
		Assertions.assertThrows( Exception.class,
			() -> thesisService.setGrades(thesis.getId(), -1.0f, 6.5f, 7.0f) );
		Assertions.assertThrows( Exception.class,
				() -> thesisService.setGrades(thesis.getId(), 5.0f, 6.5f, 13.0f) );
	}
	
	@Test
	public void ThesisService_filterStudentsForThesis() {
		List<Application> list = applicationService.getApplicationsBySubjectId(subject.getId());
		List<Student> students = thesisService.filterStudentsForThesis(list, 7.1f, 15);
		
		Assertions.assertEquals(students.get(0).getId(), stud2.getId());
		Assertions.assertEquals(students.size(), 1);
		
		students = thesisService.filterStudentsForThesis(list, 7.1f, 999);
		Assertions.assertEquals(students.size(), 2);
		
		students = thesisService.filterStudentsForThesis(list, 0, 999);
		Assertions.assertEquals(students.size(), 3);
	}
	
	@Test
	public void ThesisService_chooseThesisAssignment_AverageGrade() {
		List<Application> list = applicationService.getApplicationsBySubjectId(subject.getId());
		Assertions.assertNotNull(list);
		
		List<Student> students = thesisService.filterStudentsForThesis(list, 0, 999);
		
		Thesis thesis = thesisService.chooseThesisAssignment(
				subject.getId(), students, SelectStrategy.AVERAGE_GRADE);
		Assertions.assertNotNull(thesis);
		Assertions.assertEquals(thesis.getStudent().getId(), stud1.getId());
		
		list = applicationService.getApplicationsBySubjectId(subject.getId());
		Assertions.assertTrue(list.isEmpty());
	}
	
	@Test
	public void ThesisService_chooseThesisAssignment_RemainingCourses() {
		List<Application> list = applicationService.getApplicationsBySubjectId(subject.getId());
		List<Student> students = thesisService.filterStudentsForThesis(list, 0, 999);
		
		Thesis thesis = thesisService.chooseThesisAssignment(
				subject.getId(), students, SelectStrategy.REMAINING_COURSES);
		Assertions.assertEquals(thesis.getStudent().getId(), stud2.getId());
	}
	
	@Test
	public void ThesisService_chooseThesisAssignment_EmptyList() {
		List<Application> list = applicationService.getApplicationsBySubjectId(subject.getId());
		List<Student> students = thesisService.filterStudentsForThesis(list, 8.0f, 5);
		
		Thesis thesis = thesisService.chooseThesisAssignment(
				subject.getId(), students, SelectStrategy.RANDOM);
		Assertions.assertNull(thesis);
	}
	
}
