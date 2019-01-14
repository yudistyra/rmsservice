package com.yudis.rmsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yudis.rmsservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(String name);
}
