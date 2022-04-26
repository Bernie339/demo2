package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
 
import java.util.List;

import com.example.demo.Country.Country;
import com.example.demo.Country.CountryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CountryRepositoryTests {

    @Autowired private CountryRepository repo;
     
    @Test
    public void testCreateCountries() {
        Country aut = new Country("Österreich");
        Country de = new Country("Deutschland");
        
         
        repo.saveAll(List.of(aut, de));
         
        List<Country> listCountries = repo.findAll();
         
        assertThat(listCountries.size()).isEqualTo(2);
    }
    
}
