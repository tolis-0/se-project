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

import myy803.project.model.Thesis;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations="classpath:test.properties")
public class ThesisServiceTest {

	@Autowired
	ThesisService thesisService;
	
	@Test
	@Order(1)
	public void ThesisService_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(thesisService);
		
	}
	
	@Test
	@Order(2)
	public void ThesisService_saveThesis() {
		System.out.println("TEST 2");
	}
	
	@Test
	@Order(2)
	public void ThesisService_saveNewThesis() {
		System.out.println("TEST 3");
	}
}