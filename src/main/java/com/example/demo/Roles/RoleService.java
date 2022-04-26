package com.example.demo.Roles;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoleService {

    @Autowired RoleRepository roleRepo;
    
    public List<Role> listRoles() {
		return roleRepo.findAll();
	}
}
