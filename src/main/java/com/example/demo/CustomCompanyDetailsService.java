package com.example.demo;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomCompanyDetailsService implements UserDetailsService {
 
    @Autowired
	private CompanyRepository companyRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Company company = companyRepo.findByEmail(username);
		if (company == null) {
			throw new UsernameNotFoundException("Company not found");
		}
		return new CustomCompanyDetails(company);
	}  
 
}