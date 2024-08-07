package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.Professor;

@Service
public interface ProfessorService {

	public void setUserService(UserService userService);
	
	public Professor saveProfessor(Professor professor);
	public Professor getProfessorById(int id);

}
