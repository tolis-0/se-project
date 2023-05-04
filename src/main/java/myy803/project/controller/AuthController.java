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
        
		if(userService.isUserPresent(user)){
            return "redirect:/login?AlreadyRegistered=true";
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
