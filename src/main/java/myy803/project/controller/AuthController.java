package myy803.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import myy803.project.service.UserService;
import myy803.project.model.User;


@Controller
public class AuthController {

    @Autowired
    UserService userService;
	
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
	public String registerAttempt(@ModelAttribute("user") User user, RedirectAttributes redirectAttrs) {
        
		if(userService.isUserPresent(user)){
			redirectAttrs.addFlashAttribute("successMessage", "User already registered!");
            return "redirect:/login";
        }

        userService.saveUser(user);
        redirectAttrs.addFlashAttribute("successMessage", "User registered successfully!");

        return "redirect:/login";
	}
}
