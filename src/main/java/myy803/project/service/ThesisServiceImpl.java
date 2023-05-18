package myy803.project.service;

import java.util.Comparator;
import java.util.List;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.model.Application;

import myy803.project.model.Student;
import myy803.project.model.Subject;
import myy803.project.model.Thesis;
import myy803.project.dao.ThesisDAO;
import myy803.project.dto.SelectApplicationDTO.SelectStrategy;


@Service
public class ThesisServiceImpl implements ThesisService{
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ThesisDAO thesisDAO;

	public List<Student> filterStudentsForThesis(List<Application> list, float th1, int th2) {
		return list.stream().map(Application::getStudent)
				.filter(p -> p.getAverageGrade() >= th1)
				.filter(p -> p.getRemainingCourses() <= th2)
				.collect(Collectors.toList());
	}
	
	public Thesis chooseThesisAssignment(int subjectId, List<Student> list, SelectStrategy strategy) {
		if (list.isEmpty()) return null;
		Student student;
		switch (strategy) {
			case RANDOM:
				Random rand = new Random();
				student = list.get(rand.nextInt(list.size()));
				break;
			case AVERAGE_GRADE:
				student = list.stream().max(Comparator.comparing(Student::getAverageGrade)).get();
				break;
			case REMAINING_COURSES:
				student = list.stream().min(Comparator.comparing(Student::getRemainingCourses)).get();
				break;
			default:
				return null;
		}
		return saveNewThesis(new Thesis(subjectId, student));
	}
	
	@Override
	public Thesis saveNewThesis(Thesis thesis) {
		Subject subject = subjectService.getSubjectById(thesis.getId());
		subject.assign();
		subjectService.saveSubject(subject);
		return thesisDAO.save(thesis);
	}
	
	@Override
	public Thesis saveThesis(Thesis thesis) {
		return thesisDAO.save(thesis);
	}
	
	@Override
	public Thesis getStudentThesis(int studentId){
		Thesis assignedThesis = thesisDAO.getStudentThesis(studentId);
		if (assignedThesis != null) {
			Subject subject = subjectService.getSubjectById(assignedThesis.getId());
			assignedThesis.setSubject(subject);
			assignedThesis.setProfessor(subject.getProfessor());
		}
		return assignedThesis;
	}
	
	@Override
	public Thesis getThesisById(int thesisId){
		Thesis assignedThesis = thesisDAO.getSubjectThesis(thesisId);
		if (assignedThesis != null) {
			Subject subject = subjectService.getSubjectById(thesisId);
			assignedThesis.setSubject(subject);
			assignedThesis.setProfessor(subject.getProfessor());
		}
		return assignedThesis;
	}

	@Override
	public Thesis setGrades(int id, float impGrade, float repGrade, float presGrade) throws Exception {
		Thesis thesis = getThesisById(id);
		
		if (impGrade < 0 || impGrade > 10 || repGrade < 0 || repGrade > 10 || presGrade < 0 || presGrade > 10) {
			throw new Exception("Invalid Grade");
		}
		
		thesis.setImplementationGrade(impGrade);
		thesis.setPresentationGrade(presGrade);
		thesis.setReportGrade(repGrade);
		
		return saveThesis(thesis);
	}
	
}
