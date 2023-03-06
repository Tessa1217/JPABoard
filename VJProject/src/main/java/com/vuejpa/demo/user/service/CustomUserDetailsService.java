package com.vuejpa.demo.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userRepository.findByName(name)
				             .map(user -> createUser(user))
				             .orElseThrow(() -> new UsernameNotFoundException(name + "를 찾을 수 없습니다."));
	}
	
	private org.springframework.security.core.userdetails.User createUser(User user) {
		// 권한정보와 유저 정보 가져와서 spring security userdetails의 user객체 리턴
		// 추후에 그냥 별도로 클래스 만드는 걸로 변경 예정
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}
	
	
	
	
	

}
