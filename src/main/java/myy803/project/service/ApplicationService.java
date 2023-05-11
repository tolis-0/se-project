package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.Application;

@Service
public interface ApplicationService {

	public Application saveApplication(Application application);
	
}
