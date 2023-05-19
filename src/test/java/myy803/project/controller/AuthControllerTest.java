package myy803.project.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import myy803.project.config.LoginSuccessHandler;
import myy803.project.config.TestConfig;
import myy803.project.dto.PasswordDTO;
import myy803.project.dto.RegisterDTO;
import myy803.project.model.Role;
import myy803.project.model.User;
import myy803.project.service.UserService;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
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
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
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
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?AlreadyRegistered=true"));
		
		when(userService.isUserPresent(Mockito.any(User.class))).thenReturn(false);
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?InvalidUsername=true"));
		
		registerDTO.setUsername("6invalidusername");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?InvalidUsername=true"));
		
		registerDTO.setUsername("valid_username");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?SmallPassword=true"));
		
		registerDTO.setPassword1("this_is_a_huge_password_1234567890987645312");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?HugePassword=true"));
		
		registerDTO.setPassword1("password123");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?WeakPassword=true"));
		
		registerDTO.setPassword1("Good3947$Password.");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?DifferentPasswords=true"));
		
		registerDTO.setPassword2("Good3947$Password.");
		mockMvc
			.perform(MockMvcRequestBuilders.post("/post/register")
			.flashAttr("registerDTO", registerDTO))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/login?RegisterSuccess=true"));
	}
	
	@WithMockUser(authorities = {"STUDENT"})
	@Test
	public void AuthController_PasswordPage() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/password"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("password"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("passwordData"));
	}
	
	@WithMockUser(username = "tolis", password = "password!100", authorities = {"STUDENT"})
	@Test
	public void AuthController_ChangePassword() throws Exception {
		PasswordDTO passwordData = new PasswordDTO();
		passwordData.setOldPassword("password!200");
		passwordData.setNewPassword1("password.123");
		passwordData.setNewPassword2("password.321");
		
		when(authManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
		
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/post/password")	// TODO DEN VRISKEI USER ME TIPOTA, LEEI EINAI NULL
					/* DEN DOYLEPSE:
					 *  flashAttr("user", new User(...))
					 *  with.(user(...))
					 *  @WithMockUser
					 *  when(User.getUsername(...)).return(...) E META OTAN DEN DOYLEYEI ME TPT ARXIZEIS KAI DOKIMAZEIS TETOIES MALAKIES
					 *  param("user", ...)
					 *  ME springSecurity() KAI XWRIS
					 */
			.flashAttr("passwordData", passwordData))
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/password?InvalidPassword=true"));
		
	}
	
}
