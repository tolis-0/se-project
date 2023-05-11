package myy803.project.controller;

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
import myy803.project.dto.SubjectDTO;
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
		
		Professor professor = professorService.getProfessorById(user.getId());
		if (professor == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		model.addAttribute("professorDetails", 
				new ProfessorDTO(professor.getFullName(), professor.getSpecialty()));
		model.addAttribute("subjects", professor.getSubjectList());
		
		return "professor/dashboard";
	}
	
	@PostMapping("/post/details")
	public String changeProfileInformation(@ModelAttribute("professorDetails") ProfessorDTO professorDetails,
			@AuthenticationPrincipal User user) {
		
		Professor professor = professorService.getProfessorById(user.getId());
		if (professor == null) {
			return "redirect:/login?UserIdError=true";
		}
		
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
		
		Subject subject = subjectService.getSubjectById(subjectId);
		if (subject == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Resource");
		}
		
		Professor professor = professorService.getProfessorById(subject.getProfessorId());
		if (professor == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		model.addAttribute("subject", subject);
		model.addAttribute("subjectDetails", new SubjectDTO(subject.getName(), subject.getObjectives()));
		
		return "professor/subject";
	}
	
	@PostMapping("/post/subject/edit")
	public String editSubject(@RequestParam(name="id") int subjectId, 
			@ModelAttribute("subjectDetails") SubjectDTO subjectDetails) {
		
		Subject subject = subjectService.getSubjectById(subjectId);
		if (subject == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Resource");
		}
		
		subject.setName(subjectDetails.getName());
		subject.setObjectives(subjectDetails.getObjectives());
		subjectService.saveSubject(subject);

		return "redirect:/professor/dashboard";
	}
	
	@PostMapping("/post/subject/new")
	public String newSubject() {
		return "redirect:/professor/subject/new";
	}
	
	@GetMapping("/subject/new")
	public String createSubjectPage(Model model) {
		model.addAttribute("subjectDetails", new SubjectDTO());
		return "professor/newsubject";
	}
	
	@PostMapping("/post/subject/create")
	public String createSubject(@ModelAttribute("subjectDetails") SubjectDTO subjectDetails,
			@AuthenticationPrincipal User user) {
		
		Professor professor = professorService.getProfessorById(user.getId());
		if (professor == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		Subject subject = new Subject(professor, subjectDetails.getName(), subjectDetails.getObjectives());
		subjectService.saveSubject(subject);
		
		return "redirect:/professor/dashboard";
	}
	
	@PostMapping("/post/subject/delete")
	public String deleteSubject(@RequestParam(name="id") int subjectId, 
			@ModelAttribute("subjectDetails") SubjectDTO subjectDetails) {
		

		subjectService.deleteById(subjectId);
		
		return "redirect:/professor/dashboard";
		
	}
	
}
