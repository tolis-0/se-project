package myy803.project.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import myy803.project.config.LoginSuccessHandler;
import myy803.project.dto.RegisterDTO;
import myy803.project.model.Role;
import myy803.project.model.User;
import myy803.project.service.UserService;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
    private AuthenticationManager authManager;
    
    @MockBean
    private LoginSuccessHandler loginSuccessHandler;
	
    @BeforeEach
    public void setUp(WebApplicationContext context) {
       mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
	@Test
	void AuthController_LoginPage() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/login"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("login"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("user"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("registerDTO"));
	}
	
	@Test
	public void AuthController_Register() throws Exception {
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setUsername("invalid#username");
		registerDTO.setFullName("Some Name");
		registerDTO.setPassword1("passwrd");
		registerDTO.setPassword2("passwrd");
		registerDTO.setRole(Role.STUDENT);
		
		when(userService.isUserPresent(Mockito.any(User.class))).thenReturn(true);
		when(userService.saveUser(Mockito.any(User.class), eq("name"))).thenReturn(null);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?AlreadyRegistered=true"));
		
		when(userService.isUserPresent(Mockito.any(User.class))).thenReturn(false);
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?InvalidUsername=true"));
		
		registerDTO.setUsername("6invalidusername");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?InvalidUsername=true"));
		
		registerDTO.setUsername("valid_username");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?SmallPassword=true"));
		
		registerDTO.setPassword1("this_is_a_huge_password_1234567890987645312");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?HugePassword=true"));
		
		registerDTO.setPassword1("password123");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?WeakPassword=true"));
		
		registerDTO.setPassword1("Good3947$Password.");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?DifferentPasswords=true"));
		
		registerDTO.setPassword2("Good3947$Password.");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register").flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?RegisterSuccess=true"));
	}
	
	@Test
	public void AuthController_Login() throws Exception {
		
	}
	
}
