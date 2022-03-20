package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
     
    @Autowired
    private UserRepository userRepo;
     
    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");
        
        User user = new User();
        user.setEmail("admin@admin.at");
        user.setPassword("admin");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.addRole(roleAdmin);       
        
        User savedUser = userRepo.save(user);
        
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
}
