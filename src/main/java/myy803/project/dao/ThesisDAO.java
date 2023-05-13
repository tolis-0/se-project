package myy803.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.project.model.Thesis;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {
	
}
