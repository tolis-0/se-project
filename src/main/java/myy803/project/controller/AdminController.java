package myy803.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.project.dto.RegisterDTO;
import myy803.project.model.Role;
import myy803.project.model.User;
import myy803.project.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    UserService userService;
	
	@GetMapping("/dashboard")
	public String adminDashboardPage(Model model) {
		
		List<User> userList = userService.getAllUsers();
		model.addAttribute(userList);
		
		RegisterDTO newAdmin = new RegisterDTO();
		newAdmin.setRole(Role.ADMIN);
		model.addAttribute("AdminDTO", newAdmin);
		
		return "admin/dashboard";
	}
	
	@PostMapping("/delete/user")
	public String adminDeleteUser(@RequestParam(name="id") int userId) {
		userService.deleteUserById(userId);
		
		return "redirect:/admin/dashboard?UserDeleted=true";
	}
	
	@PostMapping("/post/password")
	public String changePasswordPage() {
		return "redirect:/password";
	}
	
}
