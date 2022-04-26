package com.example.demo.User;

import java.util.List;

import com.example.demo.Roles.Role;
import com.example.demo.Roles.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired PasswordEncoder passwordEncoder;

	
	public void registerDefaultUser(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		encodePassword(user);
		userRepo.save(user);
	}		
	
	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}

	public User getByEmail(String email){
		return userRepo.findByEmail(email);
	}		
	
	public void save(User user) {
		encodePassword(user);		
		userRepo.save(user);
	}  

	public void deleteUser(User user){
		userRepo.delete(user);
	}		
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
}
