package com.vuejpa.demo.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.user.entity.Role;
import com.vuejpa.demo.user.entity.SignUpDTO;
import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Long signUp(SignUpDTO signUpDTO) {
		if(userRepository.findByName(signUpDTO.getName()).isPresent()) {
			return null;
		}
		User user = signUpDTO.toEntity();
		user.encodedPassword(passwordEncoder.encode(user.getPassword()));
		user.addRole(Role.USER); // 일단 유저만 지정
		return userRepository.save(user).getId();
	}

}
