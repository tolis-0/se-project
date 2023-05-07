package myy803.project.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import myy803.project.dto.ProfessorDTO;
import myy803.project.model.Professor;
import myy803.project.model.Subject;
import myy803.project.model.User;
import myy803.project.service.ProfessorService;
import myy803.project.service.SubjectService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
    @Autowired
    ProfessorService professorService;
    
    @Autowired
    SubjectService subjectService;
	
    
	@GetMapping("/dashboard")
	public String professorDashboardPage(Model model, @AuthenticationPrincipal User user) {
		
		Optional<Professor> opt = professorService.getProfessorById(user.getId());
		if (!opt.isPresent()) {/*TODO*/}
		
		Professor professor = opt.get();
		model.addAttribute("professorDetails", 
				new ProfessorDTO(professor.getFullName(), professor.getSpecialty()));
		model.addAttribute("subjects", professor.getSubjectList());
		
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
	
	@PostMapping("/post/password")
	public String changePasswordPage() {
		return "redirect:/password";
	}
	
	@GetMapping("/subject")
	public String subjectPage(@RequestParam(name="id") int subjectId, Model model, @AuthenticationPrincipal User user,
			HttpServletResponse response) {
		
		Subject subject = subjectService.getSubjectById(subjectId).orElse(null);
		if (subject == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Resource");
		}
		
		Professor professor = professorService.getProfessorById(subject.getId()).orElse(null);
		if (professor == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		model.addAttribute("subjectDetails");
		
		return "subject";
	}
	
}
