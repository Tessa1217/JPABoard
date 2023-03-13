package com.vuejpa.demo.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.user.entity.AuthUserDetails;
import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public AuthUserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userRepository.findByName(name)
				             .map(user -> createUser(user))
				             .orElseThrow(() -> new UsernameNotFoundException(name + "를 찾을 수 없습니다."));
	}
	
	private AuthUserDetails createUser(User user) {
		// 권한정보와 유저 정보 가져와서 spring security userdetails의 user객체 리턴 
		return new AuthUserDetails(user.getId(), user.getPassword(), user.getName(), user.getRole().getValue());
	}

}
