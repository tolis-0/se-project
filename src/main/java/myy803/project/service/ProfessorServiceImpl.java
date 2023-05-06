package myy803.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.ProfessorDAO;
import myy803.project.model.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO professorDAO;
	
	@Override
	public void saveProfessor(Professor professor) {
		professorDAO.save(professor);
	}

	@Override
	public Optional<Professor> getProfessorById(int id) {
		return professorDAO.findById(id);
	}

}
