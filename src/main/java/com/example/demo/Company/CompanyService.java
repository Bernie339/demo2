package com.example.demo.Company;

import java.util.List;

import com.example.demo.Roles.Role;
import com.example.demo.Roles.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
	private CompanyRepository companyRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired PasswordEncoder passwordEncoder;

	
	public void registerDefaultCompany(Company company) {
		Role roleCompany = roleRepo.findByName("Company");
		company.addRole(roleCompany);
		encodePassword(company);
		companyRepo.save(company);
	}		
	
	public List<Company> listAll() {
		return companyRepo.findAll();
	}	

	public Company get(Long id) {
		return companyRepo.findById(id).get();
	}

	public Company getByEmail(String email){
		return companyRepo.findByEmail(email);
	}		
	
	public void save(Company company) {
		encodePassword(company);		
		companyRepo.save(company);
	}  

	public void deleteCompany(Company company){
		companyRepo.delete(company);
	}		
	
	private void encodePassword(Company company) {
		String encodedPassword = passwordEncoder.encode(company.getPassword());
		company.setPassword(encodedPassword);		
	}
    
}
