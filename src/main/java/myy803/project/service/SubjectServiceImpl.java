package myy803.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.SubjectDAO;
import myy803.project.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDAO;
	
	@Override
	public Subject saveSubject(Subject subject) {
		return subjectDAO.save(subject);
	}

	@Override
	public Subject getSubjectById(int id) {
		return subjectDAO.findById(id).orElse(null);
	}
	
	@Override
	public void deleteById(int theId) {
		subjectDAO.deleteById(theId);
	}
	
	@Override
	public List<Subject> getAllAvailableSubjects() {
		return subjectDAO.getAllAvailableSubjects();
	};
}
