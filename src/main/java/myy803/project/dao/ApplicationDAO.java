package myy803.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import myy803.project.model.Application;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {

	@Query(value = "SELECT * FROM applications WHERE subject = :subjectId", nativeQuery = true)
	List<Application> getApplicationsForSubject(@Param("subjectId") int subjectId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM applications WHERE student = :studentId", nativeQuery = true)
	void deleteApplicationsOfStudent(@Param("studentId") int studentId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM applications WHERE subject = :subjectId", nativeQuery = true)
	void deleteApplicationsForSubject(@Param("subjectId") int subjectId);
	
}

