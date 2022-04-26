package com.example.demo.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	@Query("SELECT r FROM Country r WHERE r.name = ?1")
	public Country findByName(String name);
}
