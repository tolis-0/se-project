package myy803.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.project.model.Application;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {

}

