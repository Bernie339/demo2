package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired PasswordEncoder passwordEncoder;

	@Autowired OrderRepository orderRepo;

	@Autowired ProductRepository productRepo;
	
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

	public Product get(Integer id) {
		return productRepo.findById(id).get();
	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}

	public List<Order> listOrders(){
		return orderRepo.findAll();
	}		

	public List<Product> listProducts(){
		return productRepo.findAll();
	}
	
	public void save(User user) {
		encodePassword(user);		
		userRepo.save(user);
	}  

	public void deleteUser(User user){
		userRepo.delete(user);
	}

	public void save(Product product) {
		productRepo.save(product);
	} 

	public void save(Order order){
		orderRepo.save(order);
	}
	
	public void createProduct(String name, String description, Float price){
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);

		productRepo.save(product);
	}	
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
}
