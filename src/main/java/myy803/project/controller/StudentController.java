package myy803.project.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.project.dto.StudentDTO;
import myy803.project.dto.SubjectDTO;
import myy803.project.dto.ApplicationDTO;
import myy803.project.model.Student;
import myy803.project.model.User;
import myy803.project.model.Application;
import myy803.project.service.StudentService;
import myy803.project.service.SubjectService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
    StudentService studentService;
	
	@Autowired
    SubjectService subjectService;
	
	@GetMapping("/dashboard")
	public String studentDashboardPage(Model model, @AuthenticationPrincipal User user) {
		Student student = studentService.getStudentById(user.getId());
		if (student == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		model.addAttribute("studentDetails", 
				new StudentDTO(student.getFull_name(), student.getRem_courses(), student.getYear(), student.getAvg_grades())); 
		model.addAttribute("subjects", subjectService.getAllAvailableSubjects());
		
		return "student";
	}
	
		
	@PostMapping("/post/application/new")
	public String newApplication() {
		return "newapplication";
	}
	
	@GetMapping("/subject/new")
	public String createApplicationPage(Model model) {
//		model.addAttribute("applicationDetails", new ApplicationDTO());
		return "newapplication";
	}
}
