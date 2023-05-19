package myy803.project.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import myy803.project.service.ApplicationService;
import myy803.project.service.StudentService;
import myy803.project.service.SubjectService;
import myy803.project.service.ThesisService;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	StudentController studentController;
	
	@MockBean
    StudentService studentService;
	
	@MockBean
    SubjectService subjectService;
	
	@MockBean
	ApplicationService applicationService;
	
	@MockBean
	ThesisService thesisService;
	
    @BeforeEach
    public void setUp(WebApplicationContext context) {
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }
    
	@Test
	void testStudentControllerIsNotNull() {
		Assertions.assertNotNull(studentController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@WithMockUser("spring")
	@Test
	void StudentController_StudentDashboard() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/dashboard"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("dashboard"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("studentDetails"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("subjects"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("thesis"));
	}
	
	@WithMockUser(value = "pat")
	@Test
	void StudentController_PasswordChange() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/post/password"))
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.redirectedUrl("redirect:/password"));
	}
	
	@Test
	void StudentController_DetailsSave() throws Exception {
		//TODO
	}
	
	@Test
	void StudentController_Apply() throws Exception {
		//TODO
	}
	
	@Test
	void StudentController_SubmitApplication() throws Exception {
		//TODO
	}
	
	@Test
	void StudentController_Back() throws Exception {
		//TODO
	}
	
	@Test
	void StudentController_Logout() throws Exception {
		//TODO
	}
}
