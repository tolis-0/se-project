package myy803.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.project.dto.PasswordDTO;
import myy803.project.dto.ProfessorDTO;
import myy803.project.model.Professor;
import myy803.project.model.User;
import myy803.project.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
    @Autowired
    ProfessorService professorService;
	
	@GetMapping("/dashboard")
	public String professorDashboardPage(Model model, @AuthenticationPrincipal User user) {
		
		Optional<Professor> opt = professorService.getProfessorById(user.getId());
		if (!opt.isPresent()) {/*TODO*/}
		
		Professor professor = opt.get();
		model.addAttribute("professorDetails", 
				new ProfessorDTO(professor.getFullName(), professor.getSpecialty())); 
		
		return "professor";
	}
	
	@PostMapping("/post/details")
	public String changeProfileInformation(@ModelAttribute("professorDetails") ProfessorDTO professorDetails,
			@AuthenticationPrincipal User user) {
		
		Optional<Professor> opt = professorService.getProfessorById(user.getId());
		if (!opt.isPresent()) {/*TODO*/}
		
		Professor professor = opt.get();
		professor.setFullName(professorDetails.getFullName());
		professor.setSpecialty(professorDetails.getSpecialty());
		professorService.saveProfessor(professor);
		
		return "redirect:/professor/dashboard?ChangedInfo=true";
	}
	
	@GetMapping("/password")
	public String changePasswordPage(Model model) {
		
		model.addAttribute("passwordData", new PasswordDTO());
		return "password";
	}
	
	@PostMapping("/post/password")
	public String changePassword(@ModelAttribute("passwordData") PasswordDTO passwordData,
			@AuthenticationPrincipal User user) {
		
		return "redirect:/professor/dashboard?ChangedPassword=true";
	}
	
}
