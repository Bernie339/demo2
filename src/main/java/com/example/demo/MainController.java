package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller // This means that this class is a Controller
public class MainController {

  @Autowired
  private UserRepository userRepo;

  @Autowired 
  private UserService service;

  @Autowired OrderRepository orderRepo;

  @Autowired ProductRepository productRepo;

  @GetMapping("/")
  public String viewHomePage(){
    return "home";
  }  

  @GetMapping("/home")
  public String home() {
    return "home";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model){
    model.addAttribute("user", new User());

    return "signup_form";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
      service.registerDefaultUser(user);
      
      return "register_success";
    }

  @GetMapping("/users")
  public String listUsers(Model model) {
      List<User> listUsers = service.listAll();
      model.addAttribute("listUsers", listUsers);      
      
      return "users";
  }

  @GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
    model.addAttribute("pass", user.getPassword());    
    
		return "user_form";
	}    

  @GetMapping("/users/createUser")
  public String createUser(Model model){
    List<Role> listRoles = service.listRoles();
    model.addAttribute("user", new User());
    model.addAttribute("listRoles", listRoles);

    return "user_create";
  }
  
  @PostMapping("/users/save")
  public String saveUser(User user) {
      service.save(user);
      
      return "redirect:/users";
  }

  @PostMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable("id") Long id){
    User user = service.get(id);
    service.deleteUser(user);

    return "redirect:/users";
  }

  @GetMapping("/dashboard")
  public String showUsers(Model model){
    List<User> listUsers = service.listAll();
      model.addAttribute("listUsers", listUsers);
      model.addAttribute("count", userRepo.count());

    return "dashboard";
  }

  @GetMapping("/orders")
  public String listOrders(Model model){
    List<User> listUsers = service.listAll();
    List<Order> listOrders = service.listOrders();

    model.addAttribute("listUsers", listUsers);
    model.addAttribute("listOrders", listOrders);
    model.addAttribute("count", orderRepo.count());

    return "orders";
  }

  @GetMapping("/orders/createOrder")
  public String createOrder(Model model){
    List<User> listUsers = service.listAll();
    List<Product> listProducts = service.listProducts();
    model.addAttribute("order", new Order());
    model.addAttribute("listUsers", listUsers);
    model.addAttribute("listProducts", listProducts);

    return "order_create";
  }  

  @PostMapping("/order/save")
  public String saveOrder(Order order) {
      service.save(order);
      
      return "redirect:/orders";
  }

  @GetMapping("/products")
  public String listProducts(Model model){
    List<Product> listProducts = service.listProducts();    

    model.addAttribute("listProducts", listProducts);
    model.addAttribute("count", productRepo.count());

    return "products";
  }

  @GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product product = service.get(id);
		model.addAttribute("product", product);
    
		return "product_edit";
	}

  @PostMapping("/product/save")
  public String saveProduct(Product product) {
      service.save(product);
      
      return "redirect:/products";
  }

  @GetMapping("/product/createProduct")
  public String createProduct(Model model){
    model.addAttribute("product", new Product());

    return "product_create";
  }

  
}
