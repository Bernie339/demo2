package com.example.demo;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderNumber;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "orders_products",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> products = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }   

    @Override
    public String toString(){
        return "" + getOrderNumber();
    }

}
