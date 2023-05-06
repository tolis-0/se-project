package myy803.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.project.model.Professor;

@Service
public interface ProfessorService {

	public void saveProfessor(Professor professor);
	public Optional<Professor> getProfessorById(int id);

}
