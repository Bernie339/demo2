package com.example.demo.Company;

import java.util.List;

import org.springframework.stereotype.Controller;
import com.example.demo.Country.Country;
import com.example.demo.Country.CountryService;
import com.example.demo.Roles.Role;
import com.example.demo.Roles.RoleService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyController {

    @Autowired 
    private CompanyService companyService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CountryService countryService;

    

    @GetMapping("/companies")
    public String listCompanies(Model model) {
    List<Company> listCompanies = companyService.listAll();
    model.addAttribute("listCompanies", listCompanies);      

    return "companies";
    }

    @GetMapping("/companies/edit/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.get(id);
        List<Role> listRoles = roleService.listRoles();
        List<Country> listCountries = countryService.listCountries();
        model.addAttribute("company", company);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("listCountries", listCountries);
        model.addAttribute("pass", company.getPassword());    

        return "company_form";
    }    

    @GetMapping("/companies/createCompany")
    public String createCompany(Model model){
    List<Role> listRoles = roleService.listRoles();
    List<Country> listCountries = countryService.listCountries();
    model.addAttribute("company", new Company());
    model.addAttribute("listRoles", listRoles);
    model.addAttribute("listCountries", listCountries);

    return "company_Create";
    }

    @PostMapping("/companies/save")
    public String saveCompany(Company company) {
        companyService.save(company);

    return "redirect:/companies";
    }

    @PostMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id){
    Company company = companyService.get(id);
    companyService.deleteCompany(company);

    return "redirect:/companies";
    }
    
}
