package myy803.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.project.model.Professor;

public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

}
