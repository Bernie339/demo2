package com.example.demo;

import java.util.List;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyRepository;
import com.example.demo.Company.CompanyService;
import com.example.demo.CompanyOrder.OrderRepository;
import com.example.demo.Product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

  @Autowired
  private UserRepository userRepo;

  @Autowired 
  private UserService service;

  @Autowired
  private CompanyService compService;

  @Autowired CompanyRepository compRepo;

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

  @GetMapping("/registerUser")
    public String showRegistrationForm(Model model){
    model.addAttribute("user", new User());

    return "signup_form";
  }

  @GetMapping("/registerCompany")
  public String showRegistrationFormCompany(Model model){
    model.addAttribute("company", new Company());

    return "signup_Company";
  }
  
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, String error, String logout) {
      if (error != null)
          model.addAttribute("errorMsg", "Your username and password does not match.");

      if (logout != null)
          model.addAttribute("msg", "You have been logged out successfully.");

      return "login";
  }

  @GetMapping("/mySite")
  public String mysite(
    @AuthenticationPrincipal CustomUserDetails userDetails, Model model){
      if(userDetails == null){
        return "login";
      }
    String userEmail = userDetails.getUsername();
    User user = userRepo.findByEmail(userEmail);

    model.addAttribute("user", user);
    model.addAttribute("pageTitle", "Account Details");  

    return "mySite";
  }

  @GetMapping("/myCompany")
  public String myCompany(
    @AuthenticationPrincipal CustomCompanyDetails companyDetails, Model model){
      if(companyDetails == null){
        return "login";
      }
    String companyEmail = companyDetails.getUsername();
    Company company = compRepo.findByEmail(companyEmail);

    model.addAttribute("company", company);
    model.addAttribute("pageTitle", "Account Details");  

    return "myCompany";
  }  

  @PostMapping("/process_register")
  public String processRegister(User user) {
    service.registerDefaultUser(user);
    
    return "register_success";
  }

  @PostMapping("/process_register_company")
  public String processRegisterCompany(Company company){
    compService.registerDefaultCompany(company);

    return "register_success_company";
  }  

  @GetMapping("/dashboard")
  public String showUsers(Model model){
    List<User> listUsers = service.listAll();
      model.addAttribute("listUsers", listUsers);
      model.addAttribute("count", userRepo.count());
      model.addAttribute("countOrders", orderRepo.count());
      model.addAttribute("countProducts", productRepo.count());

    return "dashboard";
  }  

  

  
}
