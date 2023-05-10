package myy803.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
//	@Transactional
	public void deleteById(int theId) {
		subjectDAO.deleteById(theId);
	}
}
