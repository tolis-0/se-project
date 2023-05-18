package myy803.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.project.dto.SelectApplicationDTO.SelectStrategy;
import myy803.project.model.Application;
import myy803.project.model.Student;
import myy803.project.model.Thesis;

@Service
public interface ThesisService {
	
	public List<Student> filterStudentsForThesis(List<Application> list, float th1, int th2);
	public Thesis chooseThesisAssignment(int subjectId, List<Student> list, SelectStrategy strategy);
	public Thesis saveNewThesis(Thesis thesis);
	public Thesis saveThesis(Thesis thesis);
	public Thesis getStudentThesis(int studentId);
	public Thesis getThesisById(int thesisId);
	public Thesis setGrades(int id, float impGrade, float repGrade, float presGrade) throws Exception;
	
}
