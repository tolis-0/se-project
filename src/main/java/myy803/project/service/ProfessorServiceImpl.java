package myy803.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.ProfessorDAO;
import myy803.project.model.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void saveProfessor(Professor professor) {
		professorDAO.save(professor);
	}

	@Override
	public Professor getProfessorById(int id) {
		Professor professor = professorDAO.findById(id).orElse(null);
		if (professor == null) return null;
		professor.setUser(userService.getUserById(professor.getId()));
		return professor;
	}

}
