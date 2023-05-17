package myy803.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.project.dto.AdminDTO;
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
		model.addAttribute("user", new User());
		model.addAttribute("AdminDTO", new AdminDTO());
		
		return "admin/dashboard";
	}
	
	@PostMapping("/delete/user")
	public String adminDeleteUser(@RequestParam(name="id") int userId) {
		userService.deleteUserById(userId);
		
		return "redirect:/admin/dashboard?UserDeleted=true";
	}
	
	@PostMapping("/post/register")
	public String registerAttempt(@ModelAttribute("AdminDTO") AdminDTO adminDTO) {
		
		User user = new User(adminDTO.getPassword1(), adminDTO.getRole());
		
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

        user = userService.saveUser(user);
        
        return "redirect:/login?RegisterSuccess=true";
	}
	
}
