package myy803.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.project.dto.ApplicationDTO;
import myy803.project.dto.StudentDTO;
import myy803.project.model.Application;
import myy803.project.model.Student;
import myy803.project.model.User;
import myy803.project.service.ApplicationService;
import myy803.project.service.StudentService;
import myy803.project.service.SubjectService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
    StudentService studentService;
	
	@Autowired
    SubjectService subjectService;
	
	@Autowired
	ApplicationService applicationService;
	
	@GetMapping("/dashboard")
	public String studentDashboardPage(Model model, @AuthenticationPrincipal User user) {
		Student student = studentService.getStudentById(user.getId());
		if (student == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		model.addAttribute("studentDetails", 
				new StudentDTO(student.getFullName(), student.getRem_courses(), student.getYear(), student.getAvg_grades())); 
		model.addAttribute("subjects", subjectService.getAllAvailableSubjects());
		
		return "student";
	}
		
	@PostMapping("/post/application/new")
	public String newApplication(@RequestParam(name="id") int subjectId) {
		return "redirect:/student/subject/new?id=" + subjectId;
	}
	
	@GetMapping("/subject/new")
	public String createApplicationPage(@RequestParam(name="id") int subjectId, Model model) {
		model.addAttribute("applicationDetails", new ApplicationDTO());
		return "newapplication";
	}
	
	@PostMapping("/post/application/create")
	public String createApplication(@RequestParam(name="id") int subjectId,
			@ModelAttribute("applicationDetails") ApplicationDTO applicationDetails,
			@AuthenticationPrincipal User user) {
		
		Student student = studentService.getStudentById(user.getId());
		if (student == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		Application application = new Application(subjectId, student.getId(), applicationDetails.getMessage());
		applicationService.saveApplication(application);
		
		return "redirect:/student/dashboard";
	}
}
