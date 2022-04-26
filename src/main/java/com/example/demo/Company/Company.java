package com.example.demo.Company;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.CompanyOrder.Order;
import com.example.demo.Country.Country;
import com.example.demo.Roles.Role;
import com.example.demo.User.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "companies")
public class Company {

    //Person to contact in Company (= Company Admin)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 50)
    private String phoneNumber;    

    //Company Details

    @Column(name = "company_Name", nullable = false, length = 50)
    private String name;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "postCode", nullable = false, length = 20)
    private String postCode;

    @Column(name = "location", nullable = false, length = 50)
    private String location;    

    @OneToMany(mappedBy = "company")
    private Set<Country> countries;

    @Column(name = "UID", nullable = false, length = 20)
    private String uid;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "companies_roles",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")	
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<User> users;

    

    //getters Setters

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getPassword() {
    return password;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public String getFirstName() {
    return firstName;
    }

    public void setFirstName(String firstName) {
    this.firstName = firstName;
    }

    public String getLastName() {
    return lastName;
    }

    public void setLastName(String lastName) {
    this.lastName = lastName;
    }

    public String getPhoneNumber() {
    return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRoles() {
    return roles;
    }

    public void setRoles(Set<Role> roles) {
    this.roles = roles;
    }

    public void addRole(Role role) {
    this.roles.add(role);
    }

    public Set<Order> getOrders(){
        return orders;
    }
    public void setOrders(Set<Order> orders){
        this.orders = orders;
    }

    public Set<User> getUsers(){
        return users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStreet() {
    return street;
    }

    public void setStreet(String street) {
    this.street = street;
    }

    public String getPostCode() {
    return postCode;
    }

    public void setPostCode(String postCode) {
    this.postCode = postCode;
    }

    public String getLocation() {
    return location;
    }

    public void setLocation(String location) {
    this.location = location;
    }

    public String getUid() {
    return uid;
    }

    public void setUid(String uid) {
    this.uid = uid;
    }

    public Set<Country> getCountries() {
        return countries;
    }
    
    public void setCountries(Set<Country> countries) {
    this.countries = countries;
    }

    
    
    
}
