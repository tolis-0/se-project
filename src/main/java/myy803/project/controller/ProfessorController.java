package myy803.project.controller;

import java.util.ArrayList;
import java.util.List;

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
import myy803.project.dto.SelectApplicationDTO;
import myy803.project.dto.SubjectDTO;
import myy803.project.model.Application;
import myy803.project.model.Professor;
import myy803.project.model.Student;
import myy803.project.model.Subject;
import myy803.project.model.Thesis;
import myy803.project.model.User;
import myy803.project.service.ApplicationService;
import myy803.project.service.ProfessorService;
import myy803.project.service.SubjectService;
import myy803.project.service.ThesisService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
    @Autowired
    ProfessorService professorService;
    
    @Autowired
    SubjectService subjectService;
	
    @Autowired
    ApplicationService applicationService;
    
    @Autowired
    ThesisService thesisService;
    
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
		model.addAttribute("applications", applicationService.getApplicationsBySubjectId(subjectId));
		model.addAttribute("strategySelection", new SelectApplicationDTO());
		
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
	
	@PostMapping("/post/assign")
	public String assignSubject(@RequestParam(name="id") int subjectId,
			@ModelAttribute("strategySelection") SelectApplicationDTO strategy) {

		List<Application> applicationList = applicationService.getApplicationsBySubjectId(subjectId);
		List<Student> filteredStudents = thesisService.filterStudentsForThesis(applicationList, 
				strategy.getTh1(), strategy.getTh2());
		Thesis thesis = thesisService.chooseThesisAssignment(subjectId, filteredStudents, strategy.getStrategy());
		if (thesis == null) {
			return "redirect:/professor/subject?id=" + subjectId + "&studentNotFound=true";
		}
		
		return "redirect:/professor/thesis";
	}
	
	@GetMapping("/thesis")
	public String thesisPage () {
		return "professor/thesis";
	}
	
	@PostMapping("post/back")
	public String returnToDashboard() {
		return "redirect:/professor/dashboard";
	}
	
}
