package myy803.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.project.model.Subject;

@Service
public interface SubjectService {

	public Subject saveSubject(Subject subject);
	public Optional<Subject> getSubjectById(int id);
	public void deleteById(int theId);
	public List<Subject> getAllAvailableSubjects();

}
