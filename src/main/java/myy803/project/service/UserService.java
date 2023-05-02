package myy803.project.service;

import org.springframework.stereotype.Service;

import myy803.project.model.User;

@Service
public interface UserService {

	public void saveUser(User user);
    public boolean isUserPresent(User user);

}
