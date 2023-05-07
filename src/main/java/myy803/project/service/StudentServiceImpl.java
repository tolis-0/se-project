package myy803.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.project.dao.StudentDAO;
import myy803.project.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public void saveStudent(Student student) {
		studentDAO.save(student);
	}
	
	@Override
	public Optional<Student> getStudentById(int id) {
		return studentDAO.findById(id);
	}

}
