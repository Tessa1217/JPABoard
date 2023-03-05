package com.vuejpa.demo.user.service;

import org.springframework.stereotype.Service;

import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.entity.UserRequestDTO;
import com.vuejpa.demo.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	// 사용자 회원가입
	public Long insertUser(final UserRequestDTO request) {
		User entity = userRepository.save(request.toEntity());
		return entity.getNo();
	}
	
	

}
