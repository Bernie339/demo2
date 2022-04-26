package com.example.demo.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT u FROM Company u WHERE u.email = ?1")
	public Company findByEmail(String email);
    
}
