package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.Student;

@Service
public interface StudentService {

	public void saveStudent(Student student);

}
