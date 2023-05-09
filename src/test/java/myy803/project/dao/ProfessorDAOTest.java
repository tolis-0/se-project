package myy803.project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import myy803.project.model.Professor;;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProfessorDAOTest {
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Test
	@Order(1)
	public void ProfessorDAO_notNull() {
		System.out.println("TEST 1");
		Assertions.assertNotNull(professorDAO);
	}
	
	@Test
	@Order(2)
	public void ProfessorDAO_save() {
		System.out.println("TEST 2");
		Professor prof1 = new Professor();
		Professor prof2 = new Professor();
		// TODO
	}
}
