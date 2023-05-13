package myy803.project.service;

import java.util.Comparator;
import java.util.List;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.model.Application;

import myy803.project.model.Student;
import myy803.project.model.Thesis;
import myy803.project.dao.ThesisDAO;
import myy803.project.dto.SelectApplicationDTO.SelectStrategy;


@Service
public class ThesisServiceImpl implements ThesisService{
	
	@Autowired
	private ThesisDAO thesisDAO;

	public List<Student> filterStudentsForThesis(List<Application> list, float th1, int th2) {
		return list.stream().map(Application::getStudent)
				.filter(p -> p.getAverageGrade() >= th1)
				.filter(p -> p.getRemainingCourses() <= th2)
				.collect(Collectors.toList());
	}
	
	public Thesis chooseThesisAssignment(int subjectId, List<Student> list, SelectStrategy strategy) {
		if (list == null) return null;
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
		return thesisDAO.save(new Thesis(subjectId, student));
	}
	
	@Override
	public float showTotalGrade(Thesis thesis) {
		return thesis.getTotalGrade();			
	}
	
	@Override
	public Thesis saveThesis(Thesis thesis) {
		return thesisDAO.save(thesis);
	}
}
