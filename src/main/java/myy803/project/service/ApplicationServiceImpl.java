package myy803.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.ApplicationDAO;
import myy803.project.model.Application;
import myy803.project.model.Student;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDAO applicationDAO;
	
	@Autowired
	private StudentService studentService;
	
	@Override
	public Application saveApplication(Application application) {
		return applicationDAO.save(application);
	}

	@Override
	public List<Application> getApplicationsBySubjectId(int subjectId) {
		List<Application> list = applicationDAO.getApplicationsForSubject(subjectId);
		for (Application application : list) {
			Student student = studentService.getStudentById(application.getStudentId());
			application.setStudent(student);
		}
		return list;
	}

	@Override
	public void deleteApplications(int studentId, int subjectId) {
		applicationDAO.deleteApplicationsOfStudent(studentId);
		applicationDAO.deleteApplicationsForSubject(subjectId);
	}
	
}
