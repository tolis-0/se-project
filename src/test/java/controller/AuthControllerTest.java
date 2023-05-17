package controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import myy803.project.controller.AuthController;
import myy803.project.dto.RegisterDTO;
import myy803.project.model.Role;
import myy803.project.model.User;
import myy803.project.service.UserService;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void AuthController_Register() throws Exception {
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setUsername("username");
		registerDTO.setFullName("Some Name");
		registerDTO.setPassword1("password");
		registerDTO.setPassword2("password");
		registerDTO.setRole(Role.STUDENT);
		
		when(userService.isUserPresent(Mockito.any(User.class))).thenReturn(false);
		when(userService.saveUser(Mockito.any(User.class), "name")).thenReturn(null);
	}
	
}
