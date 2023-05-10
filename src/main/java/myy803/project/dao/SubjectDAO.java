package myy803.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import myy803.project.model.Subject;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	
	@Query(value = "select * from subjects where assigned = 0 ", nativeQuery = true)
	List<Subject> getAllAvailableSubjects();

}
