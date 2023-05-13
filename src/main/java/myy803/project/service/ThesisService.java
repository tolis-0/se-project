package myy803.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.project.dto.SelectApplicationDTO.SelectStrategy;
import myy803.project.model.Application;
import myy803.project.model.Thesis;

@Service
public interface ThesisService {
	public Thesis chooseThesisAssignment(List<Application> list, SelectStrategy strategy);
	public Thesis saveThesis(Thesis thesis);
	public float showTotalGrade(Thesis thesis);
}
