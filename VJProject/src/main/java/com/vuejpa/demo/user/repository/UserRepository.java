package com.vuejpa.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuejpa.demo.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
