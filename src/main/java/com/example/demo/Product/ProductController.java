package com.example.demo.Product;

import java.util.List;

import com.example.demo.CompanyOrder.OrderRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {    

    @Autowired OrderRepository orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired ProductRepository productRepo;

    @GetMapping("/products")
    public String listProducts(Model model){
    List<Product> listProducts = productService.listProducts();    

    model.addAttribute("listProducts", listProducts);
    model.addAttribute("count", productRepo.count());

    return "products";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
    Product product = productService.get(id);
    model.addAttribute("product", product);

    return "product_edit";
    }

    @PostMapping("/product/save")
    public String saveProduct(Product product) {
    productService.save(product);

    return "redirect:/products";
    }

    @GetMapping("/product/createProduct")
    public String createProduct(Model model){
    model.addAttribute("product", new Product());

    return "product_create";
    }
    
}
