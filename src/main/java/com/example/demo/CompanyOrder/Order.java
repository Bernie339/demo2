package com.example.demo.CompanyOrder;

import java.util.*;

import javax.persistence.*;

import com.example.demo.Company.Company;
import com.example.demo.Product.Product;

@Entity
@Table(name = "company_orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderNumber;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "company_orders_products",
			joinColumns = @JoinColumn(name = "company_order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> products = new HashSet<>();
    
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getOrderNumber(){
        return orderNumber;
    }
    public void setOrderNumber(Long orderNumber){
        this.orderNumber = orderNumber;
    }

    public Set<Product> getProducts(){
        return products;
    }
    public void setProducts(Set<Product> products){
        this.products = products;
    }      

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    @Override
    public String toString(){
        return "" + getOrderNumber();
    }

}
