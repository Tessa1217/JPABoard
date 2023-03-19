package com.vuejpa.demo.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.user.entity.Role;
import com.vuejpa.demo.user.entity.RoleEnum;
import com.vuejpa.demo.user.entity.SignUpDTO;
import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.RoleRepository;
import com.vuejpa.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final RoleRepository roleRepository;
	
	// 회원가입
	@Transactional
	public Long signUp(SignUpDTO signUpDTO) {
		if(userRepository.findByName(signUpDTO.getName()).isPresent()) {
			return null;
		}
		User user = signUpDTO.toEntity();
		
		// 현재는 유저 역할만 추가 진행 
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER).get();
		roles.add(userRole);
		
		user.encodedPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(roles);
		
		return userRepository.save(user).getId();
	}

}
