package com.example.demo.Country;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CountryService {

    @Autowired CountryRepository countryRepo;
    
    public List<Country> listCountries() {
		return countryRepo.findAll();
	}
    
}
