package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.Thesis;

@Service
public interface ThesisService {
	public void chooseSubjectApplication(int subjectId, int strategy);
}
