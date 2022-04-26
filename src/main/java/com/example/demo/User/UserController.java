package com.example.demo.User;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.example.demo.Company.Company;
import com.example.demo.Company.CompanyService;
import com.example.demo.Roles.Role;
import com.example.demo.Roles.RoleService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    @Autowired 
    private UserService service;

    @Autowired
    private RoleService roleService;

    @Autowired CompanyService companyService;

    @GetMapping("/users")
    public String listUsers(Model model) {
    List<User> listUsers = service.listAll();
    model.addAttribute("listUsers", listUsers);      

    return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.get(id);
        List<Role> listRoles = roleService.listRoles();
        List<Company> listCompanies = companyService.listAll();
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pass", user.getPassword());    

        return "user_form";
    }    

    @GetMapping("/users/createUser")
    public String createUser(Model model){
    List<Role> listRoles = roleService.listRoles();
    List<Company> listCompanies = companyService.listAll();
    model.addAttribute("listCompanies", listCompanies);
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
    
}
