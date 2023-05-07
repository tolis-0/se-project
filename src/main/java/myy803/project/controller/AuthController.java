package myy803.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import myy803.project.service.ProfessorService;
import myy803.project.service.StudentService;
import myy803.project.service.UserService;
import myy803.project.config.LoginSuccessHandler;
import myy803.project.dto.PasswordDTO;
import myy803.project.dto.RegisterDTO;
import myy803.project.model.Professor;
import myy803.project.model.Student;
import myy803.project.model.User;


@Controller
public class AuthController {

    @Autowired
    UserService userService;
    
    @Autowired
    StudentService studentService;
	
    @Autowired
    ProfessorService professorService;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    
    @GetMapping("/")
    public String mainPage(Authentication authentication) {
    	if (authentication == null) {
    		return "redirect:/login";
    	}
    	
    	String url = loginSuccessHandler.determineTargetUrl(authentication);
    	
    	return "redirect:" + url;
    }
    
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("registerDTO", new RegisterDTO());
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("registerDTO", new RegisterDTO());
		return "login";
	}
	
	@PostMapping("/post/register")
	public String registerAttempt(@ModelAttribute("registerDTO") RegisterDTO registerDTO) {
		
		User user = new User(registerDTO.getUsername(), registerDTO.getPassword1(), registerDTO.getRole());
		
		if(userService.isUserPresent(user)) {
            return "redirect:/login?AlreadyRegistered=true";
        }
		
		/*String errors = checkForErrors(user.getUsername(), user.getPassword());
		if (!errors.isEmpty()) {
			return "redirect:/login?" + errors;
		}
		
		if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) {
			return "redirect:/login?DifferentPasswords=true";
		}*/

        userService.saveUser(user);
        saveRoleSpecificData(user, registerDTO.getFullName());
        
        return "redirect:/login?RegisterSuccess=true";
	}
	
	@GetMapping("/password")
	public String changePasswordPage(Model model) {
		
		model.addAttribute("passwordData", new PasswordDTO());
		return "password";
	}
	
	@PostMapping("/post/password")
	public String changePassword(@ModelAttribute("passwordData") PasswordDTO passwordData,
			@AuthenticationPrincipal User user, Authentication authentication) {
		
		if (!validUsernamePassword(user.getUsername(), passwordData.getOldPassword())) {
			return "redirect:/password?InvalidPassword=true";
		}
		
		if (!passwordData.getNewPassword1().equals(passwordData.getNewPassword2())) {
			return "redirect:/password?DifferentPasswords=true";
		}
		
		userService.changePassword(user, passwordData.getNewPassword1());
		return "redirect:" + loginSuccessHandler.determineTargetUrl(authentication) + "?ChangedPassword=true";
	}
	
	
	public boolean validUsernamePassword(String username, String password) { 
	    UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(username, password);
	    try {
	    	Authentication auth = authManager.authenticate(authReq);
	    	if (auth == null) return false;
	    } catch (AuthenticationException e) {
	    	return false;
	    }
	    
	    return true;
	}
	
	
	private String checkForErrors(String username, String password) {
		
		if (!usernameMeetsRequirements(username)) {
			return "InvalidUsername=true";
		}
		
		if (Character.isDigit(username.charAt(0))) {
			return "InvalidUsername=true";
		}
	
		if (password.length() < 8) {
			return "SmallPassword=true";
		}
		
		if (password.length() > 25) {
			return "HugePassword=true";
		}
		
		if (!passwordMeetsRequirements(password)) {
			return "WeakPassword=true";
		}
		
		return "";
	}
	
	private boolean passwordMeetsRequirements(String password) {
		boolean hasLetter = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;
		
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLetter(password.charAt(i)) ) {
				hasLetter = true;
			}
			if (Character.isDigit(password.charAt(i)) ) {
				hasDigit = true;
			}
			if (!password.substring(i, i+1).matches("[A-Za-z0-9]")) {
				hasSymbol = true;
			}			
		}
		
		return hasLetter && hasDigit && hasSymbol;
	}
	
	private boolean usernameMeetsRequirements(String username) {
		for (int i = 0; i < username.length(); i++) {
			if (!Character.isLetterOrDigit(username.charAt(i))) {
				if(username.charAt(i) != '_') {
					return false;
				}
			}
		}
		return true;
	}
	
	private void saveRoleSpecificData(User user, String fullName) {
        String roleValue = user.getRole().getValue();
        if (roleValue == "Student") {
        	studentService.saveStudent(new Student(user.getId(), fullName));
        } else if (roleValue == "Professor") {
        	professorService.saveProfessor(new Professor(user.getId(), fullName));
        }
	}
}
