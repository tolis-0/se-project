package myy803.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import myy803.project.service.ProfessorService;
import myy803.project.service.StudentService;
import myy803.project.service.UserService;
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
    
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/post/register")
	public String registerAttempt(@ModelAttribute("user") User user) {
		
		if(userService.isUserPresent(user)) {
            return "redirect:/login?AlreadyRegistered=true";
        }
		
		String errors = checkForErrors(user.getUsername(), user.getPassword());
		if (!errors.isEmpty()) {
			return "redirect:/login?" + errors;
		}

        userService.saveUser(user);
        saveRoleSpecificData(user);
        
        return "redirect:/login?RegisterSuccess=true";
	}
	
	
	private String checkForErrors(String username, String password) {
		
		if (!usernameMeetsRequirements(username)) {
			return "InvalidUsername=true";
		}
		
		if (Character.isDigit(username.charAt(0))) {
			return "InvalidUsername=true";
		}
	
		if(password.length() < 8 || password.length() > 25) {
			return "InvalidPassword=true";
		}
		
		if (!passwordMeetsRequirements(password)) {
			return "InvalidPassword=true";
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
	
	private void saveRoleSpecificData(User user) {
        String roleValue = user.getRole().getValue();
        if (roleValue == "Student") {
        	studentService.saveStudent(new Student(user.getId()));
        } else if (roleValue == "Professor") {
        	professorService.saveProfessor(new Professor(user.getId()));
        }
	}
}
