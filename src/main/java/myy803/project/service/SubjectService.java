package myy803.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.project.model.Subject;

@Service
public interface SubjectService {

	public Subject saveSubject(Subject subject);
	public Subject getSubjectById(int id);
	public void deleteById(int theId);
	public List<Subject> getAllAvailableSubjects();
	public void divideListIntoAssignedAndNot(List<Subject> input, List<Subject> assigned, List<Subject> notAssigned);

}
