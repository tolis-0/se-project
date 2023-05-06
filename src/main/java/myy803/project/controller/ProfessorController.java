package myy803.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.project.dto.ProfessorDetails;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@GetMapping("/dashboard")
	public String professorDashboardPage(Model model) {
		model.addAttribute("professorDetails", new ProfessorDetails()); 
		return "professor";
	}
}
