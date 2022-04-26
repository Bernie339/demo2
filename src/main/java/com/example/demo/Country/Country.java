package com.example.demo.Country;

import javax.persistence.*;
import javax.persistence.Entity;

import com.example.demo.Company.Company;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 45)
	private String name;    

	@ManyToOne
	private Company company;

    public Country() { }

	
	public Country(String name) {
		this.name = name;
	}
	
	public Country(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Country(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany(){
		return company;
	}

	public void setCompany(Company company){
		this.company = company;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
