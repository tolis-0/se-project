package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.Thesis;

@Service
public interface ThesisService {
	public void chooseThesisAssignment(int subjectId, int strategy);
	public Thesis saveThesis(Thesis thesis);
}
