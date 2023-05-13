package myy803.project.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.ProfessorDAO;
import myy803.project.model.Application;
import myy803.project.model.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Override
	public Professor saveProfessor(Professor professor) {
		return professorDAO.save(professor);
	}

	@Override
	public Professor getProfessorById(int id) {
		Professor professor = professorDAO.findById(id).orElse(null);
		if (professor == null) return null;
		professor.setUser(userService.getUserById(professor.getId()));
		return professor;
	}
	
	public void chooseSubjectApplication(int subjectId, int strategy) {
		List<Application> list = applicationService.getApplicationsBySubjectId(subjectId);
		if (strategy == 1) {
			Random rand = new Random();
			int rand_int1 = rand.nextInt(list.size()-1);
			list.get(rand_int1).getStudentId();
			//create thesis
		}
		if (strategy == 2) {
			int student_id = list.get(0).getStudentId();
//			int best_avg_grades = 
		}
	}
}
