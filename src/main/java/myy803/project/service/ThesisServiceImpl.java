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
		return saveThesis(new Thesis(subjectId, student));
	}
	
	@Override
	public Thesis saveThesis(Thesis thesis) {
		Subject subject = subjectService.getSubjectById(thesis.getId());
		subject.assign();
		subjectService.saveSubject(subject);
		return thesisDAO.save(thesis);
	}
	
	@Override
	public Thesis getStudentThesis(int studentid){
		Thesis assignedThesis = thesisDAO.getStudentThesis(studentid);
		assignedThesis.setSubject(subjectService.getSubjectById(assignedThesis.getId()));
		return assignedThesis;
	}
	
	@Override
	public Thesis getSubjectThesis(int subjectid){
		Thesis assignedThesis = thesisDAO.getSubjectThesis(subjectid);
		assignedThesis.setSubject(subjectService.getSubjectById(assignedThesis.getId()));
		return assignedThesis;
	}
	
}
