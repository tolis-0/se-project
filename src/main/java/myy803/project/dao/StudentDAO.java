package myy803.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.project.model.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {

}
