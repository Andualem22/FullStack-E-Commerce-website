package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User Andy = new User("aandy@gmail.com", "password123", "Andy", "Teshome");
        Andy.addRole(roleAdmin);

        User savedUser = repo.save(Andy);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User andualem = new User("anduuualem@gmail.com", "andy1234", "Andualem", "Derbe");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        andualem.addRole(roleEditor);
        andualem.addRole(roleAssistant);

        User savedUser = repo.save(andualem);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User Andy = repo.findById(1).get();
        System.out.println(Andy);
        assertThat(Andy).isNotNull();
    }

    @Test
    public void testUpdatedDetails() {
        User Andy = repo.findById(1).get();
        Andy.setEnabled(true);
        Andy.setEmail("yeteshomelij@gmail.com");

        repo.save(Andy);
    }
    @Test
    public void testUpdateUserRoles() {
        User Andy = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        Andy.getRoles().remove(roleEditor);
        Andy.addRole(roleSalesPerson);

        repo.save(Andy);
    }
//    @Test
//    public void testDeleteUser() {
//        Integer userId = 2;
//        repo.deleteById(userId);
//    }
    @Test
    public void testGetUserByEmail() {
        String email = "ethiopia@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

}
