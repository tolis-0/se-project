package myy803.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.StudentDAO;
import myy803.project.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void saveStudent(Student student) {
		studentDAO.save(student);
	}
	
	@Override
	public Student getStudentById(int id) {
		Student student = studentDAO.findById(id).orElse(null);
		if (student == null) return null;
		student.setUser(userService.getUserById(student.getId()));
		return student;
	}

}