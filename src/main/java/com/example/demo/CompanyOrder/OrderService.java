package com.example.demo.CompanyOrder;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;




@Service
public class OrderService {

    @Autowired OrderRepository orderRepo;


    public Order getOrder(Long id){
		return orderRepo.findById(id);
	}
    
    public List<Order> listOrders(){
		return orderRepo.findAll();
	}

    public void save(Order order){
		orderRepo.save(order);
	}

    public void deleteOrder(Order order){
		orderRepo.delete(order);
	}
}
