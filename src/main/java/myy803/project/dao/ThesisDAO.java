package myy803.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myy803.project.model.Thesis;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {
	
	@Query(value = "SELECT * FROM thesis WHERE student = :studentid", nativeQuery = true)
	Thesis getStudentThesis(@Param("studentid") int studentid);
}
