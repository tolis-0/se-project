package myy803.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import myy803.project.model.User;
import myy803.project.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDAO.findByUsername(username).orElseThrow(
			()-> new UsernameNotFoundException(
				String.format("USER_NOT_FOUND", username)
			));
	}

	@Override
	public User saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDAO.save(user);
	}

	@Override
	public boolean isUserPresent(User user) {
		Optional<User> storedUser = userDAO.findByUsername(user.getUsername());
		return storedUser.isPresent();
	}

	@Override
	public User changePassword(User user, String password) {
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		user.setPassword(encodedPassword);
		return userDAO.save(user);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.findById(id).orElse(null);
	}

}
