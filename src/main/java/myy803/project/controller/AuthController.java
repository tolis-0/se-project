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
        
		boolean hasLetter = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;
		
		if(userService.isUserPresent(user)){
            return "redirect:/login?AlreadyRegistered=true";
        }
	
		if(user.getPassword().length()<8 || user.getPassword().length()>25) {
			return "redirect:/login?InvalidPassword=true";
		}
		
		for (int i = 0; i < user.getPassword().length(); i++) {
			if (Character.isLetter(user.getPassword().charAt(i)) ) {
				hasLetter = true;
			}
			if (Character.isDigit(user.getPassword().charAt(i)) ) {
				hasDigit = true;
			}
			if (!user.getPassword().substring(i, i+1).matches("[A-Za-z0-9]")) {
				hasSymbol = true;
			}			
		}
		if (hasLetter == false || hasDigit == false || hasSymbol == false) {
			return "redirect:/login?InvalidPassword=true";
		}
		
		
		if (Character.isDigit(user.getUsername().charAt(0))) {
			return "redirect:/login?InvalidUsername=true";
		}
		
		for (int i = 0; i < user.getUsername().length(); i++) {
			if (!Character.isLetterOrDigit(user.getUsername().charAt(i))) {
				if(user.getUsername().charAt(i) != '_') {
					return "redirect:/login?InvalidUsername=true";
				}
			}
		}

        userService.saveUser(user);
        String roleValue = user.getRole().getValue();
        if (roleValue == "Student") {
        	studentService.saveStudent(new Student(user.getId()));
        } else if (roleValue == "Professor") {
        	professorService.saveProfessor(new Professor(user.getId()));
        }

        return "redirect:/login?RegisterSuccess=true";
	}
}
