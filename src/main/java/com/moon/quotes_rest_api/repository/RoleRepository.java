package com.moon.quotes_rest_api.repository;

import com.moon.quotes_rest_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
