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
import myy803.project.service.ThesisService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
    StudentService studentService;
	
	@Autowired
    SubjectService subjectService;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ThesisService thesisService;
	
    @RequestMapping("/")
    public String redirectFromRoot() {
    	return "redirect:/student/dashboard";
    }
	
	@GetMapping("/dashboard")
	public String studentDashboardPage(Model model, @AuthenticationPrincipal User user) {
		Student student = studentService.getStudentById(user.getId());
		if (student == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		model.addAttribute("studentDetails", 
				new StudentDTO(student.getFullName(), student.getRemainingCourses(), student.getYear(), student.getAverageGrade()));
		model.addAttribute("subjects", subjectService.getAllAvailableSubjects());
		model.addAttribute("thesis", thesisService.getStudentThesis(student.getId()));
		return "student/dashboard";
	}
	
	@PostMapping("/post/details")
	public String changeProfileInformation(@ModelAttribute("studentDetails") StudentDTO studentDetails,
			@AuthenticationPrincipal User user) {
		
		Student student = studentService.getStudentById(user.getId());
		if (student == null) {
			return "redirect:/login?UserIdError=true";
		}
		
		student.setFullName(studentDetails.getFullName());
		student.setRemainingCourses(studentDetails.getRemCourses());
		student.setYear(studentDetails.getYear());
		student.setAverageGrade(studentDetails.getAvgGrade());
		studentService.saveStudent(student);
		
		return "redirect:/student/dashboard?ChangedInfo=true";
	}
	
	@PostMapping("/post/password")
	public String changePasswordPage() {
		return "redirect:/password";
	}
	
	
	@PostMapping("/post/application/new")
	public String newApplication(@RequestParam(name="id") int subjectId) {
		return "redirect:/student/subject/new?id=" + subjectId;
	}
	
	@GetMapping("/subject/new")
	public String createApplicationPage(@RequestParam(name="id") int subjectId, Model model) {
		model.addAttribute("applicationDetails", new ApplicationDTO());
		return "student/newapplication";
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
	
	@GetMapping("/thesis")
	public String thesisPage (Model model, @AuthenticationPrincipal User user) {
		Student student = studentService.getStudentById(user.getId());
		model.addAttribute("thesis", thesisService.getStudentThesis(student.getId()));
		return "student/thesis";
	}
	
	@PostMapping("post/back")
	public String returnToDashboard() {
		return "redirect:/student/dashboard";
	}
}
