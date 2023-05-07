package myy803.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.SubjectDAO;
import myy803.project.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDAO;
	
	@Override
	public void saveSubject(Subject subject) {
		subjectDAO.save(subject);
	}

	@Override
	public Optional<Subject> getSubjectById(int id) {
		return subjectDAO.findById(id);
	}
	
}
