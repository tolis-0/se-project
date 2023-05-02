package myy803.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.project.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}