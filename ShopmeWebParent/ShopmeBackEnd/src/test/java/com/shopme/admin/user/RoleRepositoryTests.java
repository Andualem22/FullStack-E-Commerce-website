package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shopme.common.entity.Role;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository repo;
	
	
	@Test
	public void testCreatFirstRole() {
		Role roleAdmin = new Role("Admin", "manage everything");
		Role savedRole = repo.save(roleAdmin);
		
		assertThat(savedRole.getId()).isGreaterThan(0);
		
	}

	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("salesperson", "manage product price, " + "customers, shipping, orders and sales report");
		Role roleEditor = new Role("Editor", "manage categories, brands, " + "products, articles and menus");
		Role roleShipper = new Role("Shipper", "view products, view orders " + "and update orders status");
		Role roleAssistant = new Role("Assistant", "manage questions and reviews");

		repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}

}
