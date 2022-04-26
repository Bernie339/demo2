package com.example.demo.Product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProductService {

    @Autowired ProductRepository productRepo;


    public Product get(Integer id) {
		return productRepo.findById(id).get();
	}
    
    public List<Product> listProducts(){
		return productRepo.findAll();
	}

    public void save(Product product) {
		productRepo.save(product);
	} 	
	
	public void createProduct(String name, String description, Float price){
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);

		productRepo.save(product);
	}
}
