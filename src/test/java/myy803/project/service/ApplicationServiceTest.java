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

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ApplicationServiceTest {

	@Autowired
	ApplicationService applicationService;
	
	@Test
	@Order(1)
	public void ApplicationService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(applicationService);
	}
	
	@Test
	@Order(2)
	public void ApplicationService_saveApplication() {
		System.out.println("TEST 2");

		Application appl1 = applicationService.saveApplication(new Application(4, 7, "Hello, I would like to take on this thesis diploma ..." ));
		Application appl2 = applicationService.saveApplication(new Application(3, 11, "Greetings, I am interested in the thesis ..." ));
		
		Assertions.assertNotNull(appl1);
		Assertions.assertNotNull(appl2);
		
		Assertions.assertEquals(appl1.getStudentId(), 7);
		Assertions.assertEquals(appl2.getStudentId(), 11);
		Assertions.assertEquals(appl1.getSubjectId(), 4);
		Assertions.assertEquals(appl2.getSubjectId(), 3);
		Assertions.assertEquals(appl1.getMessage(), "Hello, I would like to take on this thesis diploma ...");
		Assertions.assertEquals(appl2.getMessage(), "Greetings, I am interested in the thesis ...");

	}
	
	@Test
	@Order(3)
	public void ApplicationService_getApplicationsBySubjectId() {
		System.out.println("TEST 3");
		
		applicationService.saveApplication(new Application(4, 7, "Hello, I would like to take on this thesis diploma ..." ));
		applicationService.saveApplication(new Application(3, 11, "Greetings, I am interested in the thesis ..." ));
		
		List<Application> appList1 = applicationService.getApplicationsBySubjectId(4);
		List<Application> appList2 = applicationService.getApplicationsBySubjectId(3);
		List<Application> appList3 = applicationService.getApplicationsBySubjectId(2);
		
		Assertions.assertNotNull(appList1.get(0));
		Assertions.assertNotNull(appList2.get(0));
		assertTrue(appList3.isEmpty());
		
		Assertions.assertEquals(appList1.get(0).getStudentId(), 7);
		Assertions.assertEquals(appList1.get(0).getSubjectId(), 4);
	}
	
	@Test
	@Order(4)
	public void ApplicationService_deleteApplications() {
		System.out.println("TEST 4");
		
		applicationService.saveApplication(new Application(4, 7, "Hello, I would like to take on this thesis diploma ..." ));
		applicationService.saveApplication(new Application(3, 11, "Greetings, I am interested in the thesis ..." ));
		
		applicationService.deleteApplications();
		applicationService.deleteApplications();
		
	}
}
