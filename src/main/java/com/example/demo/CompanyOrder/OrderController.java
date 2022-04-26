package com.example.demo.CompanyOrder;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.demo.Product.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class OrderController {


    @Autowired CompanyService companyService;

    @Autowired
    private OrderService orderService;

    @Autowired OrderRepository orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired ProductRepository productRepo;

    @GetMapping("/orders")
    public String listOrders(Model model){
    List<Company> listCompanies = companyService.listAll();
    List<Order> listOrders = orderService.listOrders();
    
    model.addAttribute("listCompanies", listCompanies);
    model.addAttribute("listOrders", listOrders);
    model.addAttribute("count", orderRepo.count());

    return "orders";
    }

    @GetMapping("/orders/createOrder")
    public String createOrder(Model model){
    List<Company> listCompanies = companyService.listAll();
    List<Product> listProducts = productService.listProducts();
    model.addAttribute("order", new Order());
    model.addAttribute("listProducts", listProducts);
    model.addAttribute("listCompanies", listCompanies);

    return "order_create";
    }  

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
    Order order = orderService.getOrder(id);
    List<Product> listProducts = productService.listProducts();
    List<Order> listOrders = orderService.listOrders();
    model.addAttribute("order", order);
    model.addAttribute("listOrders", listOrders);
    model.addAttribute("listProducts", listProducts);

    return "order_form";
    } 

    @PostMapping("/order/save")
    public String saveOrder(Order order) {
    orderService.save(order);

    return "redirect:/orders";
    }

    @PostMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
    Order order = orderService.getOrder(id);
    orderService.deleteOrder(order);

    return "redirect:/orders";
    }
    
}
