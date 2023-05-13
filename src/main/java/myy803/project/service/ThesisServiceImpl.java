package myy803.project.service;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.model.Application;
import myy803.project.model.Thesis;
import myy803.project.dao.ThesisDAO;

@Service
public class ThesisServiceImpl implements ThesisService{
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ThesisDAO thesisDAO;

	public void chooseThesisAssignment(int subjectId, int strategy) {
		List<Application> list = applicationService.getApplicationsBySubjectId(subjectId);
		if (strategy == 1) {
			Random rand = new Random();
			int rand_int1 = rand.nextInt(list.size()-1);
			list.get(rand_int1).getStudentId();
			//create thesis
		}
		if (strategy == 2) {
			int student_id = list.get(0).getStudentId();
			float best_avg_grades = list.get(0).getStudent().getAvg_grades();
			for (int i = 1; i<list.size(); i++) {
				if (best_avg_grades<list.get(i).getStudent().getAvg_grades()) {
					best_avg_grades = list.get(i).getStudent().getAvg_grades();
					student_id = list.get(i).getStudentId();
				}
			}
		}
		if (strategy == 3) {
			int student_id = list.get(0).getStudentId();
			int rem_courses = list.get(0).getStudent().getRem_courses();
			for (int i = 1; i<list.size(); i++) {
				if (rem_courses>list.get(i).getStudent().getRem_courses()) {
					rem_courses = list.get(i).getStudent().getRem_courses();
					student_id = list.get(i).getStudentId();
				}
			}
		}
	}
	
	@Override
	public Thesis saveThesis(Thesis thesis) {
		return thesisDAO.save(thesis);
	}
}
