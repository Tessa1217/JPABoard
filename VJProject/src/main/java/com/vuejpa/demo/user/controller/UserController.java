package com.vuejpa.demo.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuejpa.demo.jwt.JwtFilter;
import com.vuejpa.demo.jwt.TokenProvider;
import com.vuejpa.demo.user.entity.LoginDTO;
import com.vuejpa.demo.user.entity.SignUpDTO;
import com.vuejpa.demo.user.entity.TokenDTO;
import com.vuejpa.demo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserService userService;
	
	@RequestMapping(value="/users/signUp.do")
	@ResponseBody
	public String signUp(@RequestBody SignUpDTO signUpDTO) {
		String result = "";
		Long id = userService.signUp(signUpDTO);
		if(id == null) {
			result = "failed";
		} else {
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value="/users/login.do")
	@ResponseBody
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken authenticationToken =
				             new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword());
		System.out.println(authenticationToken.getCredentials());
		// AuthenticationToken을 이용하여 authenticate가 실행될 때 UserService의 loadUserByUsername 실행
		System.out.println("===========");
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		System.out.println("===========");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.createToken(authentication);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		
		return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
	}
	

}
