package myy803.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.project.model.Subject;

@Service
public interface SubjectService {

	public void saveSubject(Subject subject);
	public Optional<Subject> getSubjectById(int id);

}
