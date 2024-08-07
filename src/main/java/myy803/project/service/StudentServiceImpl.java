package myy803.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.StudentDAO;
import myy803.project.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
	
	@Override
	public Student saveStudent(Student student) {
		return studentDAO.save(student);
	}
	
	@Override
	public Student getStudentById(int id) {
		Student student = studentDAO.findById(id).orElse(null);
		if (student == null) return null;
		student.setUser(userService.getUserById(student.getId()));
		return student;
	}

}