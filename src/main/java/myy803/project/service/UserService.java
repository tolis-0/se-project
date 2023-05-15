package myy803.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import myy803.project.model.User;

@Service
public interface UserService {

	public User saveUser(User user);
    public boolean isUserPresent(User user);
    public User changePassword(User user, String password);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public void deleteUserById(int id);
    
}
