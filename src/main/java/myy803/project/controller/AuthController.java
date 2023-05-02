package myy803.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import myy803.project.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "login";
	}
	
	@PostMapping("login")
	public String loginAttempt() {
		return null; //TODO
		
	}
	
	@PostMapping("register")
	public String registerAttempt() {
		return null; //TODO
	}
}
