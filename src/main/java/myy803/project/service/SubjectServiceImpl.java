package myy803.project.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.SubjectDAO;
import myy803.project.model.Professor;
import myy803.project.model.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDAO;
	
	@Autowired
	ProfessorService professorService;
	
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
		List<Subject> subjectlist = subjectDAO.getAllAvailableSubjects();
		for (Subject subject : subjectlist) {
			Professor professor = professorService.getProfessorById(subject.getProfessorId());
			subject.setProfessor(professor);
		}
		return subjectlist;
	}

	@Override
	public void divideListIntoAssignedAndNot(List<Subject> input, List<Subject> assigned, List<Subject> notAssigned) {
		Map<Boolean, List<Subject>> partitioned = 
				input.stream().collect(Collectors.partitioningBy(Subject::isAssigned));
		
		assigned.addAll(partitioned.get(true));
		notAssigned.addAll(partitioned.get(false));
	};
	
	
}
