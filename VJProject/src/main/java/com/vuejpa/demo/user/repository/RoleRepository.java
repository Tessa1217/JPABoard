package com.vuejpa.demo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuejpa.demo.user.entity.Role;
import com.vuejpa.demo.user.entity.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(RoleEnum name);
	
}
