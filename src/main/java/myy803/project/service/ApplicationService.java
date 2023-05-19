package myy803.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.project.model.Application;

@Service
public interface ApplicationService {

	public Application saveApplication(Application application);
	public List<Application> getApplicationsBySubjectId(int subjectId);
	public void deleteApplications(int studentId, int subjectId);
	
}
